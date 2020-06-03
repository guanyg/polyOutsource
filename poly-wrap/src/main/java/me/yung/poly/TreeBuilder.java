package me.yung.poly;

import me.yung.poly.jo.Term;
import me.yung.poly.jo.Tree;

import java.math.BigInteger;
import java.util.*;

public class TreeBuilder {
    public static final byte ANODE = 0x01;
    public static final byte CNODE = 0x02;

    public static void main(String[] args) {
        Tree.Node node = buildTree(Arrays.asList(
                new Term(BigInteger.valueOf(5), new Term.XPowered(0, 5), new Term.XPowered(1, 3)),
                new Term(BigInteger.valueOf(15), new Term.XPowered(0, 2)),
                new Term(BigInteger.valueOf(2), new Term.XPowered(1, 4), new Term.XPowered(2, 4))
        ));
        Tree.Node node2 = buildTree(Arrays.asList(
                new Term(BigInteger.valueOf(5), new Term.XPowered(0, 5), new Term.XPowered(1, 3)),
                new Term(BigInteger.valueOf(15), new Term.XPowered(0, 2)),
                new Term(BigInteger.valueOf(2), new Term.XPowered(1, 4), new Term.XPowered(2, 4))
        ));
        /*
        Node{
            aNode = Node{
                aNode = Node{
                    aNode = Node{val = 5}, maxX = 1, minP = 3,
                },
                cNode = Node{val = 15}, maxX = 0, minP = 3,
            },
            cNode = Node{
                aNode = Node{
                    aNode = Node{val = 2}, maxX = 2, minP = 4,
                }, maxX = 1, minP = 4,
            }, maxX = 0, minP = 2,
        }

         */
//        System.out.println(node.getVal(new Tree.XPowerProvider(Arrays.asList(BigInteger.valueOf(15), BigInteger.valueOf(30), BigInteger.valueOf(2)))));
        BigInteger modulus = new BigInteger(1024, new Random());
        new TreeEvaluator().evalTree(node2, new Tree.XPowerProvider(Arrays.asList(BigInteger.valueOf(15), BigInteger.valueOf(30), BigInteger.valueOf(2)), modulus), modulus);
//        System.out.println(node2.val.equals(node.val));
    }

    public static Tree.Node buildTree(List<Term> globalTerms) {
        Stack<StackEl> stack = new Stack<>();
        stack.push(new TermEl(globalTerms));
        while (!stack.empty()) {
            StackEl top = stack.pop();
            if (top instanceof TermEl) {
                TermEl topTerms = (TermEl) top;
                List<Term> terms = topTerms.terms;
                if (terms.size() == 0) {
                    if (topTerms.parent >= 0) {
                        ((ParentEl) stack.get(topTerms.parent)).setNode(topTerms.type, null);
                    }
                    continue;
                }

                SplitTermList splitTermList = new SplitTermList(terms).invoke();
                List<Term> c = splitTermList.getC();
                List<Term> a = splitTermList.getA();
                int maxX = splitTermList.getMaxX();
                int minP = splitTermList.getMinP();

                if (a.size() == 0) {
                    Tree.Node cnode = new Tree.Node(c.stream().map(Term::getA).reduce(BigInteger::add).orElse(BigInteger.ZERO));
                    if (top.parent >= 0) {
                        ((ParentEl) stack.get(top.parent)).setNode(top.type, cnode);
                        continue;
                    }
                    return cnode;
                }
                stack.push(new ParentEl(top.parent, top.type, maxX, minP));
                int parentIdx = stack.size() - 1;
                List<Term> newA = new ArrayList<>();

                outer:
                for (Term t :
                        a) {
                    for (Term.XPowered x :
                            t.getX()) {
                        if (x.getX() == maxX) {
                            x.setP(x.getP() - minP);
                            newA.add(t);
                            continue outer;
                        }
                    }
                    c.add(t);
                }

                for (Term t :
                        newA) {
                    t.getX().removeIf(xPowered -> xPowered.getP() == 0);
                }
                stack.push(new TermEl(parentIdx, ANODE, newA));
                stack.push(new TermEl(parentIdx, CNODE, c));
            } else if (top instanceof ParentEl) {
                if (top.parent >= 0) {
                    ((ParentEl) stack.get(top.parent)).setNode(top.type, ((ParentEl) top).getNode());
                } else {
                    return ((ParentEl) top).getNode();
                }
            }
        }
        return null;
    }

//    Tree.Node buildTree(List<Term> terms) {
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

    static class StackEl {
        int parent = -1;
        byte type;
    }

    static class TermEl extends StackEl {
        List<Term> terms;

        public TermEl(List<Term> terms) {
            this.terms = terms;
        }

        public TermEl(int parent, byte type, List<Term> terms) {
            this.parent = parent;
            this.type = type;
            this.terms = terms;
        }
    }

    static class ParentEl extends StackEl {
        int x;
        int p;
        Tree.Node anode;
        Tree.Node cnode;

        public ParentEl(int parent, byte type, int maxX, int minP) {
            this.parent = parent;
            this.type = type;
            x = maxX;
            p = minP;
        }

        public void setNode(byte type, Tree.Node node) {
            if (type == ANODE) {
                this.anode = node;
            } else {
                this.cnode = node;
            }
        }

        public Tree.Node getNode() {
            return new Tree.Node(anode, cnode, x, p);
        }
    }

    private static class SplitTermList {
        private List<Term> terms;
        private List<Term> c;
        private List<Term> a;
        private int maxX;
        private int minP;

        public SplitTermList(List<Term> terms) {
            this.terms = terms;
        }

        public List<Term> getC() {
            return c;
        }

        public List<Term> getA() {
            return a;
        }

        public int getMaxX() {
            return maxX;
        }

        public int getMinP() {
            return minP;
        }

        public SplitTermList invoke() {
            c = new ArrayList<>();
            a = new ArrayList<>();

            Map<Integer, Integer> map = new TreeMap<>();
            Map<Integer, Integer> minMap = new TreeMap<>();
            for (Term t :
                    terms) {
                if (t.getX().size() == 0) {
                    c.add(t);
                    continue;
                }
                a.add(t);
                for (Term.XPowered xp :
                        t.getX()) {
                    map.compute(xp.getX(), (k, v) ->
                            v == null ? 1 : v + 1
                    );
                    minMap.compute(xp.getX(), (k, v) ->
                            v == null ? xp.getP() : Math.min(xp.getP(), v)
                    );
                }
            }

            maxX = map.entrySet().parallelStream()
                    .max(Comparator.comparingInt(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .orElse(-1);

            if (minMap.containsKey(maxX))
                minP = minMap.get(maxX);
            return this;
        }
    }

//    static class NodeStackElem {
//        static final byte ANODE = 0x01;
//        static final byte CNODE = 0x02;
//        int parent;
//        byte type;
//        byte s = 0x03;
//        List<Term> terms;
//        private Tree.Node anode;
//        private Tree.Node cnode;
//        private int x, p;
//
//        public NodeStackElem(List<Term> terms) {
//
//        }
//
//        public void setNode(byte type, NodeStackElem top) {
//            this.s ^= type;
//            Tree.Node node = top.getNode();
//            if (type == ANODE) {
//                this.anode = node;
//            } else {
//                this.cnode = node;
//            }
//        }
//
//        private Tree.Node getNode() {
//            if (anode != null || cnode != null)
//                return new Tree.Node(anode, cnode, x, p);
//            return null;
//        }
//    }
}
