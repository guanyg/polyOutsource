package me.yung.poly;

import me.yung.poly.generated.TestGas;
import me.yung.poly.jo.Task;
import me.yung.poly.jo.TestCase;
import me.yung.poly.jo.Tree;
import org.jetbrains.annotations.NotNull;
import org.web3j.protocol.Web3j;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final SharedStorage sharedStorage = SharedStorage.getInstance();
    static Web3j web3j;

    static {
        WebSocketService web3jService = new WebSocketService("wss://rinkeby.infura.io/ws/v3/<Infura Token>", false);
        System.out.println("DATA>>>>>>msg,type,x,degree,numTerms,t0,buildTask,publishTaskIPFS,publishTaskETH,workerPullTask,workerProcessTask,workerSubmitResultIPFS,workerSubmitResultETH,userTriggerVerify,userVerify,oracleVerify,gasUsed");
        try {
            web3jService.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        web3j = Web3j.build(web3jService);
    }

    static {
        System.out.println("Poly-outsource");
    }

    public static void main(String[] args) throws Exception {
        String[] key = getKeys();
        testGas(key[0], key[1], key[2]);
//        for (int m = 0; m < 5; m++) {
//            TestCase tc = new TestCase("warmup", 3, 32, (int) (32*0.9));
//            setup(key[0], key[1], key[2], tc);
//        }
//        for (int i = 2; i < 5; i++) {
//            for (int j = 16; j < 1000; j *= 2) {
//                for (int k = 1; k < 10; k++) {
//                    int l = j * k / 10;
//                    for (int m = 0; m < 5; m++) {
//                        TestCase tc = new TestCase("run", i, j, l);
//                        setup(key[0], key[1], key[2], tc);
//                    }
//                }
//            }
//        }
        web3j.shutdown();
    }

    private static void testGas(String userKey, String workerKey, String oracleKey) throws Exception {
        //
//        var block = web3j.
//        console.log("gasLimit: " + block.gasLimit);

        User u = new User(userKey);
        Worker w = new Worker(workerKey);
        TestGas testGas = u.deployTestGas();
        TestGasOracle o = new TestGasOracle(oracleKey, testGas);

        for (int i = 2; i < 5; i++) {
            for (int j = 512; j < 1000; j *= 2) {
                for (int k = 1; k < 10; k++) {
                    int l = j * k / 10;
                    for (int m = 0; m < 5; m++) {
                        TestCase tc = new TestCase(TestCase.Type.MULTIVAR, "run", i, j, l, 1024);
                        o.setTc(tc);
                        boolean fin = false;
                        do {
                            try {
                                u.setTc(tc);
                                Task t = u.buildTask();
                                Tree tree = w.computeAndProof(t.getA_addr(), t.getX_addr());
                                List<Tree.ArgNode> arr = tree.flatten();
                                o.verify(arr);
                                fin = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } while (!fin);

                        System.out.println("DATA>>>>>>" + tc);
                    }
                }
            }
        }


    }

    @NotNull
    static String[] getKeys() {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/secret.key"));
        return new String[]{
                scanner.nextLine().trim(),
                scanner.nextLine().trim(),
                scanner.nextLine().trim()};
    }

    private static void setup(String userKey, String workerKey, String oracleKey, TestCase tc) throws Exception {
        User u = new User(userKey, tc);
        Worker w = new Worker(workerKey, tc);
        Oracle o = new Oracle(oracleKey, tc);

        Task t = u.buildTask();
        String taskAddr = u.publishTask(t);

        o.listenTo(taskAddr);
        w.process(taskAddr);

        u.verify(1);

        synchronized (o) {
            if (!o.getVerified())
                o.wait(300_000);
        }
        System.out.println("DATA>>>>>>" + tc);
    }


}
