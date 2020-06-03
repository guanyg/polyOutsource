package me.yung.poly;

import me.yung.poly.generated.TestGas;
import me.yung.poly.jo.Polynomial;
import me.yung.poly.jo.Term;
import me.yung.poly.jo.TestCase;
import me.yung.poly.jo.TestCase.Type;
import me.yung.poly.jo.Tree;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static me.yung.poly.Main.getKeys;
import static me.yung.poly.Util.padding;
import static me.yung.poly.Worker.buildTree;

public class TestGasMain {
    public static final int N = 50;
    public static final int NUM_BITS = 16;
    final static Object lock = new Object();
    static Web3j web3j;
    static Random random = new Random();
    private static BlockingQueue<BigInteger> r1Queue;
    private static BlockingQueue<BigInteger> r2Queue;

    static {
        try {
            System.setOut(new PrintStream("./out.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        WebSocketService web3jService = new WebSocketService("wss://rinkeby.infura.io/ws/v3/<Infura Token>", false);
//        HttpService web3jService = new HttpService("http://localhost:9660", false);
//        HttpService web3jService = new HttpService("http://localhost:9661", false);

        System.out.println(">>> >>> >>> msg,type,x,degree,numTerms,blMod,t0,buildTask,publishTaskIPFS,publishTaskETH,workerPullTask,workerProcessTask,workerSubmitResultIPFS,workerSubmitResultETH,userTriggerVerify,userVerify,oracleVerify,invokeVerification,gasUsed");
        try {
            web3jService.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        web3j = Web3j.build(web3jService);
    }

    public static void main(String[] args) {
        String[] key = getKeys();
        ContractPool contractPool = new ContractPool(Credentials.create(key[0]));

        ExecutorService executorService = Executors.newCachedThreadPool();

        r1Queue = new LinkedBlockingQueue<BigInteger>(500);
        r2Queue = new LinkedBlockingQueue<BigInteger>(5);

        executorService.submit(() -> {
            Thread.currentThread().setName("r1QueueGen");
            Random random = new Random();
            while (!Thread.interrupted()) {
                System.err.println("r1Queue Gen");
                try {
                    r1Queue.put(new BigInteger(NUM_BITS, random));
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        String msg = "test";

        for (int blMod = 1024; blMod < 5000; blMod *= 2) {

            int numThreadR2 = 6;
            CountDownLatch latch = new CountDownLatch(numThreadR2);
            int finalBlMod = blMod;
            Future<?>[] r2 = new Future[numThreadR2];
            for (int i = 0; i < numThreadR2; i++) {
                r2[i] = executorService.submit(() -> {
                    Thread.currentThread().setName("r2QueueGen");
                    Random random = new Random();
                    while (!Thread.interrupted()) {
                        System.err.println("r2Queue Gen");
                        try {
                            r2Queue.put(BigInteger.probablePrime(finalBlMod, random));
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    latch.countDown();
                });
            }

            System.out.println(">>> Normal");
            for (int degree = 2048; degree < 9000; degree *= 2) {
                runTask(contractPool, executorService, Type.NORMAL, msg, 1, degree, 0, blMod);
            }

            System.out.println(">>> Sparse");
            for (int degree : new int[]{100, 1000, 10000}) {
                for (int p = 1; p < 10; p++) {
                    runTask(contractPool, executorService, Type.SPARSE, msg, 1, degree, degree * p / 10, blMod);
                }
            }

            System.out.println(">>> Multivariate");
            for (int x : new int[]{2, 4}) {
                for (int degree = 64; degree < 5000; degree *= 2) {
                    for (int p = 7; p < 8; p++) {
                        runTask(contractPool, executorService, Type.MULTIVAR, msg, x, degree, degree * p / 10, blMod);
                    }
                }
            }

            for (int i = 0; i < numThreadR2; i++) {
                r2[i].cancel(true);
            }
            try {
                latch.await();
                r2Queue.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void runTask(ContractPool contractPool, ExecutorService executorService, Type type, String msg, int x, int degree, int numTerms, int blMod) {
        System.out.println(">>> >>> runTask(" + msg + ", " + x + ", " + degree + ", " + numTerms + ", " + blMod + ")");
        AtomicInteger probResult = new AtomicInteger(0);
        AtomicInteger finished = new AtomicInteger(0);

        do {
            probResult.incrementAndGet();
            try {
                executorService.submit(() -> {
                    TestCase tc = new TestCase(type, msg, x, degree, numTerms, blMod);
                    Thread.currentThread().setName("runTask");
                    TestGas contract = null;
                    try {
                        contract = contractPool.checkOut();
                        runTestCase(contract, tc);
                        finished.incrementAndGet();
                        System.out.println(">>> >>> >>> DATA - " + tc);
                    } catch (Exception e) {
                        probResult.decrementAndGet();
                        e.printStackTrace();
                    } finally {
                        if (contract != null)
                            contractPool.checkIn(contract);
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                }).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            while (probResult.get() >= N && finished.get() < N) {
                synchronized (lock) {
                    try {
                        lock.wait(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        } while (finished.get() < N);
    }

    public static void runTestCase(TestGas testGas, TestCase testCase) throws Exception {
        // build polynomial
        long t0 = System.nanoTime();
        Polynomial polynomial = buildPolynomial(testCase);
//        BigInteger modulus = new BigInteger(testCase.getBlMod(), random).nextProbablePrime();
        BigInteger modulus = r2Queue.take();
        List<BigInteger> x = new ArrayList<>();
        int bound = testCase.getX();
        for (int _i = 0; _i < bound; _i++) {
            BigInteger bigInteger = new BigInteger(NUM_BITS, random);
            x.add(bigInteger);
        }
        long t1 = System.nanoTime();
        testCase.setBuildTask(t1 - t0);

        testCase.setT0(t0);
        // worker work
        t0 = System.nanoTime();
        Tree tree;
        if (testCase.getType() == Type.MULTIVAR) {
            tree = buildTree(polynomial, x);
            tree.setModulus(modulus);
            tree.compute();
        } else {
            Tree.Node root = null;
            Term prevTerm = null;
            tree = new Tree(root, x, modulus);
            Tree.XPowerProvider xPowerProvider = new Tree.XPowerProvider(x, modulus);
            for (Term term : polynomial.getTerms()) {
                if (prevTerm == null) {
                    prevTerm = term;
                    root = new Tree.Node(term.getA());
                    continue;
                }

                int prevPower = 0;
                for (Term.XPowered xPowered : prevTerm.getX()) {
                    prevPower = xPowered.getP();
                    break;
                }

                for (Term.XPowered xPowered : term.getX()) {
                    prevPower -= xPowered.getP();
                    break;
                }

                root = new Tree.Node(root, new Tree.Node(term.getA()), 0, prevPower);
                root.getVal(xPowerProvider);

                prevTerm = term;
            }
            if (prevTerm != null && prevTerm.getX().size() > 0) {
                root = new Tree.Node(root, null, 0, prevTerm.getX().get(0).getP());
                root.getVal(xPowerProvider);
            }
            tree = new Tree(root, x, modulus);
        }
        t1 = System.nanoTime();

        testCase.setWorkerProcessTask(t1 - t0);

        t0 = System.nanoTime();
        List<Tree.ArgNode> nodes = tree.flatten();
        t1 = System.nanoTime();
        testCase.setWorkerSubmitResultIPFS(t1 - t0);

        // call contract
        Tree.ArgNode node = nodes.get(random.nextInt(nodes.size()));

        t0 = System.nanoTime();
        if (!SimTestGas.verify_node(node)) {
            System.out.println(node);
            System.exit(-1);
        }
        t1 = System.nanoTime();
        testCase.setOracleVerify(t1 - t0);

//        Tree.ArgNode node = nodes.get(i.getVal().intValue());
        List<byte[]> vals = Collections.singletonList(padding(node.getVal().toByteArray()));
        List<byte[]> avals = Collections.singletonList(padding(node.getAval().toByteArray()));
        List<byte[]> cvals = Collections.singletonList(padding(node.getCval().toByteArray()));
        List<byte[]> xvals = Collections.singletonList(padding(node.getXval().toByteArray()));
        List<BigInteger> p = Collections.singletonList(BigInteger.valueOf(node.getP()));
        List<byte[]> moduli = Collections.singletonList(padding(node.getModulus().toByteArray()));

        try {
            synchronized (TestGasMain.class) {
                t0 = System.nanoTime();
                TransactionReceipt receipt = testGas.feed_proof_path(vals, avals, cvals, xvals, p, moduli).send();
                t1 = System.nanoTime();
                testCase.setInvokeVerification(t1 - t0);
//                testGas.getDebugBoolEvents(receipt).forEach(bool -> {
////                    System.out.println("result".equals(bool.msg));
//                    if ("result".equals(bool.msg) && !bool.val) {
//                        System.out.println(
//                                ">>> >>> >>> DEBUG - " + testGas.getDebugEvents(receipt)
//                                        .stream()
//                                        .map(debug ->
//                                                     debug.msg + ": " + new BigInteger(1, debug.val)
//                                        ).collect(Collectors.joining(", ")));
//                        throw new RuntimeException("verification failed");
//                    }
//                });
                BigInteger gas = receipt.getGasUsed();
                testCase.setGasUsage(gas.longValue());
            }
        } catch (TransactionException | RuntimeException e) {
//            e.printStackTrace();
            throw e;
//        } catch (RuntimeException e) {
//            System.out.println(node);
//            e.printStackTrace();
//            System.exit(-1);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
        }

    }

    private static Polynomial buildPolynomial(TestCase testCase) throws InterruptedException {
        int numTerms = testCase.getNumTerms();
        int x = testCase.getX();
        int degree = testCase.getDegree();
        List<Term> terms = new ArrayList<>();
        switch (testCase.getType()) {
            case NORMAL:
                for (int i = degree; i > 0; i--) {

                    terms.add(new Term(
                            // new BigInteger(NUM_BITS, random),
                            r1Queue.take(),
                            new Term.XPowered(0, i)
                    ));
                }
//                terms.add(new Term(new BigInteger(NUM_BITS, random)));
                terms.add(new Term(r1Queue.take()));
                break;
            case SPARSE:
                int rnd = 0;
                int rg = degree;
                for (int i = 1; i < numTerms; i++) {
                    rg -= rnd;
                    terms.add(new Term(
                            r1Queue.take(),
//                            new BigInteger(NUM_BITS, random),
                            new Term.XPowered(0, rg)
                    ));
                    rnd = 1 + random.nextInt(rg - numTerms + i + 1);
                }
                Term lastTerm = new Term(r1Queue.take());
//                Term lastTerm = new Term(new BigInteger(NUM_BITS, random));
                if (rg > 0) {
                    lastTerm.getX().add(new Term.XPowered(0, rg));
                }
                break;
            case MULTIVAR:
                for (int i = 0; i < numTerms; i++) {
                    List<Term.XPowered> ip = new ArrayList<>();
                    int offset = random.nextInt(x);
                    int k = degree;
                    for (int j = 0; j < x && k > 0; j++) {
                        int p = random.nextInt(k + 1);
                        if (p != 0) {
                            ip.add(new Term.XPowered((j + offset) % x, p));
                        }
                        k -= p;
                    }
                    if (k > 0) {
                        ip.add(new Term.XPowered(offset, k));
                    }
                    terms.add(new Term(r1Queue.take(), ip));
//                    terms.add(new Term(new BigInteger(NUM_BITS, random), ip));
                }
                break;
        }
        return new Polynomial(terms);
    }

    public static class ContractPool extends ObjectPool<TestGas> {

        //        private final Credentials credentials;
        private final FastRawTransactionManager txMgr;

        public ContractPool(Credentials credentials) {
//            this.credentials = credentials;
            this.txMgr = new FastRawTransactionManager(web3j, credentials);
        }

        @Override
        protected TestGas create() throws Exception {
            synchronized (TestGasMain.class) {
                return TestGas.deploy(web3j, txMgr, new DefaultGasProvider().getGasPrice(), BigInteger.valueOf(10000000L), BigInteger.ZERO).send();
            }
        }

        @Override
        public boolean validate(TestGas o) throws IOException {
            return o.isValid();
        }

        @Override
        public void expire(TestGas o) {

        }
    }
}
