package me.yung.poly.jo;

import me.yung.poly.TreeEvaluator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Tree {
    private final Node root;
    private final List<BigInteger> x;
    private BigInteger modulus;

    public Tree(Node root, List<BigInteger> x, BigInteger modulus) {
        this(root, x);
        this.modulus = modulus;
    }

    public Tree(Node root, List<BigInteger> x) {

        this.root = root;
        this.x = x;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }

    public BigInteger compute() {
        return new TreeEvaluator().evalTree(root, new XPowerProvider(x, modulus), this.modulus);
//        return root.getVal(new XPowerProvider(x));
    }

    public List<ArgNode> flatten() {
        List<ArgNode> queue = new ArrayList<>();
        flattenNode(root, queue);
        return queue;
    }

    private void flattenNode(Node node, List<ArgNode> queue) {
        if (node == null || node.val == null) {
            return;
        }
        if (node.aNode == null && node.cNode == null) {
            return;
        }
        ArgNode r = new ArgNode();
        r.val = node.getVal(null);
        if (node.aNode != null) {
            r.aval = node.aNode.getVal(null);
            flattenNode(node.aNode, queue);
        } else {
            r.aval = BigInteger.ZERO;
        }
        if (node.cNode != null) {
            r.cval = node.cNode.getVal(null);
            flattenNode(node.cNode, queue);
        } else {
            r.cval = BigInteger.ZERO;
        }
        r.xval = x.get(node.maxX);
        r.p = node.minP;
        r.modulus = modulus;
        queue.add(r);
    }

    public void setModulus(BigInteger modulus) {
        this.modulus = modulus;
    }

    public static class Node {
        public final Node aNode;
        public final Node cNode;
        public final int maxX;
        public final int minP;
        public BigInteger val;

        public Node(Node aNode, Node cNode, int maxX, int minP) {

            this.aNode = aNode;
            this.cNode = cNode;
            this.maxX = maxX;
            this.minP = minP;
        }

        public Node(BigInteger c) {
            this.aNode = null;
            this.cNode = null;
            this.maxX = -1;
            this.minP = -1;
            this.val = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    (aNode == null ? "" : "aNode = " + aNode + ", ") +
                    (cNode == null ? "" : "cNode = " + cNode + ", ") +
                    (maxX == -1 ? "" : "maxX = " + maxX + ", ") +
                    (minP == -1 ? "" : "minP = " + minP + ", ") +
                    (val == null ? "" : "val = " + val) +
                    '}';
        }

        public BigInteger getVal(XPowerProvider xPowerProvider) {
            if (val == null) {
                val = BigInteger.ZERO;
                if (cNode != null)
                    val = cNode.getVal(xPowerProvider);

                if (maxX >= 0 && aNode != null) {
                    val = val.add(xPowerProvider.get(maxX, minP).multiply(aNode.getVal(xPowerProvider))).mod(xPowerProvider.modulus);
                }
            }
            return val;
        }


    }

    public static class XPowerProvider {
        private final BigInteger modulus;
        List<TreeMap<Integer, BigInteger>> cachedPower = new ArrayList<>();

        public XPowerProvider(List<BigInteger> x, BigInteger modulus) {
            this.modulus = modulus;
            for (BigInteger bigInteger : x) {
                TreeMap<Integer, BigInteger> e = new TreeMap<>();
                e.put(1, bigInteger);
                cachedPower.add(e);
            }
        }

        public BigInteger get(int maxX, int minP) {
            Map<Integer, BigInteger> integerBigIntegerMap = cachedPower.get(maxX);
            BigInteger ret = BigInteger.ONE;
            int power = 0;
            while (minP > 0) {
                if (integerBigIntegerMap.containsKey(minP)) {
                    ret = ret.multiply(integerBigIntegerMap.get(minP)).mod(modulus);
                    power += minP;
                    integerBigIntegerMap.put(power, ret);
                    return ret;
                } else {
                    int finalMinP = minP;
                    int max = integerBigIntegerMap.keySet().parallelStream().mapToInt(i -> i).filter(i -> i < finalMinP).max().orElse(1);
                    ret = ret.multiply(integerBigIntegerMap.get(max)).mod(modulus);
                    minP -= max;
                    power += max;
                    integerBigIntegerMap.put(power, ret);
                }
            }
            return BigInteger.ONE;
        }

        public BigInteger getBak(int maxX, int minP) {
            if (minP == 0) {
                return BigInteger.ONE;
            }
            Map<Integer, BigInteger> integerBigIntegerMap = cachedPower.get(maxX);
            if (integerBigIntegerMap.containsKey(minP)) {
                return integerBigIntegerMap.get(minP);
            }
            int max = integerBigIntegerMap.keySet().parallelStream().mapToInt(i -> i).filter(i -> i < minP).max().orElse(1);
            return integerBigIntegerMap.get(max).multiply(get(maxX, minP - max));
        }

        private static class stackEl {
            int maxX;
            int minP;
            BigInteger val;

            public stackEl(int maxX, int minP) {

                this.maxX = maxX;
                this.minP = minP;
            }
        }
    }

    public static class ArgNode {
        BigInteger val;
        BigInteger aval;
        BigInteger cval;
        BigInteger xval;
        int p;
        private BigInteger modulus;

        public BigInteger getVal() {
            return val;
        }

        public BigInteger getAval() {
            return aval;
        }

        public BigInteger getCval() {
            return cval;
        }

        public BigInteger getXval() {
            return xval;
        }

        public int getP() {
            return p;
        }

        @Override
        public String toString() {
            return String.join(",",
                               new String[]{
                                       String.valueOf(val),
                                       String.valueOf(aval),
                                       String.valueOf(cval),
                                       String.valueOf(xval),
                                       String.valueOf(p),
                                       String.valueOf(modulus),
                               });
        }

        public BigInteger getModulus() {
            return modulus;
        }
    }
}
