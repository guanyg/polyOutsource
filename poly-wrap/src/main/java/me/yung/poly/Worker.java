package me.yung.poly;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.yung.poly.generated.Poly;
import me.yung.poly.jo.Polynomial;
import me.yung.poly.jo.TestCase;
import me.yung.poly.jo.Tree;
import org.jetbrains.annotations.NotNull;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class Worker {
    private final Credentials credentials;
    SharedStorage sharedStorage = SharedStorage.getInstance();
    private TestCase tc;

    public Worker(String key, TestCase tc) {
        this(key);
        this.tc = tc;
    }

    public Worker(String key) {
        this.credentials = Credentials.create(key);
    }

    public static Tree buildTree(Polynomial polynomial, List<BigInteger> x) {
        return new Tree(TreeBuilder.buildTree(polynomial.getTerms()), x);
    }

//    public static Tree.Node buildTreeStack(List<Term> allTerms) {
//        Stack<NodeStackElem> stack = new Stack<>();
//        stack.push(new NodeStackElem(allTerms));
//
//        while (!stack.empty()) {
//            NodeStackElem top = stack.peek();
//
//            if (top.s == 0x00) {
//                if (top.parent == null) {
//                    return top.node;
//                }
//                stack.pop();
//                continue;
//            }
//
//            if (top.terms.size() != 0) {
//                List<Term> terms = top.terms;
//
//                List<Term> c = new ArrayList<>();
//                List<Term> a = new ArrayList<>();
//
//                Map<Integer, Integer> map = new TreeMap<>();
//                Map<Integer, Integer> minMap = new TreeMap<>();
//                for (Term t :
//                        terms) {
//                    if (t.getX().size() == 0) {
//                        c.add(t);
//                        continue;
//                    }
//                    a.add(t);
//                    for (Term.XPowered xp :
//                            t.getX()) {
//                        map.compute(xp.getX(), (k, v) ->
//                                v == null ? 1 : v + 1
//                        );
//                        minMap.compute(xp.getX(), (k, v) ->
//                                v == null ? xp.getP() : Math.min(xp.getP(), v)
//                        );
//                    }
//                }
//
//                if (a.size() == 0) {
//                    top.setNode(NodeStackElem.ANODE, NodeStackElem.EMPTY);
//                    return new Tree.Node(c.stream().map(Term::getA).reduce(BigInteger::add).orElse(BigInteger.ZERO));
//                }
//                stack.get()
//
//            }
//
//
//        }
//    }

//    private static Tree.Node buildTree(List<Term> terms) {
//        if (terms.size() == 0) {
//            return null;
//        }
//
//        List<Term> c = new ArrayList<>();
//        List<Term> a = new ArrayList<>();
//
//        Map<Integer, Integer> map = new TreeMap<>();
//        Map<Integer, Integer> minMap = new TreeMap<>();
//        for (Term t :
//                terms) {
//            if (t.getX().size() == 0) {
//                c.add(t);
//                continue;
//            }
//            a.add(t);
//            for (Term.XPowered xp :
//                    t.getX()) {
//                map.compute(xp.getX(), (k, v) ->
//                        v == null ? 1 : v + 1
//                );
//                minMap.compute(xp.getX(), (k, v) ->
//                        v == null ? xp.getP() : Math.min(xp.getP(), v)
//                );
//            }
//        }
//
//        if (a.size() == 0) {
//            return new Tree.Node(c.stream().map(Term::getA).reduce(BigInteger::add).orElse(BigInteger.ZERO));
//        }
//
//        int maxX = map.entrySet().parallelStream()
//                .max(Comparator.comparingInt(Map.Entry::getValue))
//                .map(Map.Entry::getKey)
//                .orElse(-1);
//
//        int minP = minMap.get(maxX);
//        List<Term> newA = new ArrayList<>();
//
//        outer:
//        for (Term t :
//                a) {
//            for (Term.XPowered x :
//                    t.getX()) {
//                if (x.getX() == maxX) {
//                    x.setP(x.getP() - minP);
//                    newA.add(t);
//                    continue outer;
//                }
//            }
//            c.add(t);
//        }
//
//        for (Term t :
//                newA) {
//            t.getX().removeIf(xPowered -> xPowered.getP() == 0);
//        }
//
//        return new Tree.Node(buildTree(newA), buildTree(c), maxX, minP);
//    }

    public void process(String taskAddr) throws Exception {
        long t0 = System.nanoTime();
        Poly contract = Poly.load(taskAddr, Main.web3j, credentials, new DefaultGasProvider());
        Gson gson = new Gson();
        Polynomial polynomial = gson.fromJson(sharedStorage.get(contract.a_addr().send()), Polynomial.class);
        List<BigInteger> x = gson.fromJson(sharedStorage.get(contract.x_addr().send()), new TypeToken<List<BigInteger>>() {
        }.getType());
        long t1 = System.nanoTime();

        Tree tree = computeAndProof(contract.a_addr().send(), contract.x_addr().send());
        long t2 = System.nanoTime();
        String result = sharedStorage.put(new Gson().toJson(tree.flatten()));
        long t3 = System.nanoTime();
        contract.submit_result(result, "", BigInteger.valueOf(1000)).send();
        long t4 = System.nanoTime();

        tc.setWorkerPullTask(t1 - t0);
        tc.setWorkerProcessTask(t2 - t1);
        tc.setWorkerSubmitResultIPFS(t3 - t2);
        tc.setWorkerSubmitResultETH(t4 - t3);

    }

    @NotNull
    public Tree computeAndProof(String a_addr, String x_addr) throws IOException {
        Gson gson = new Gson();
        Polynomial polynomial = gson.fromJson(sharedStorage.get(a_addr), Polynomial.class);
        List<BigInteger> x = gson.fromJson(sharedStorage.get(x_addr), new TypeToken<List<BigInteger>>() {
        }.getType());
        Tree tree = buildTree(polynomial, x);
        tree.compute();
        return tree;
    }

//    static class NodeStackElem {
//        private static final NodeStackElem EMPTY = new NodeStackElem(Collections.emptyList());
//
//        public static final byte ANODE = 0x01;
//        public static final byte CNODE = 0x02;
//
//        NodeStackElem parent;
//        byte nodeType;
//        List<Term> terms;
//        Tree.Node node;
//
//        private int x;
//        private int p;
//        byte s = 0x3;
//        private Tree.Node anode;
//        private Tree.Node cnode;
//
//        public NodeStackElem(List<Term> terms) {
//            this.terms = terms;
//        }
//
//        void setNode(byte node, NodeStackElem stackElem) {
//            if (node == ANODE) {
//                this.anode = stackElem.node;
//            } else {
//                this.cnode = stackElem.node;
//            }
//            s ^= node;
//            if (s == 0) {
//                this.node = new Tree.Node(anode, cnode, x, p);
//                if (this.parent != null){
//                    parent.setNode();
//                }
//            }
//        }
//
//    }
}
