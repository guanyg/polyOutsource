package me.yung.poly;

import me.yung.poly.jo.Tree;

import java.math.BigInteger;
import java.util.Stack;
import java.util.StringJoiner;

public class TreeEvaluator {
    public static final byte ANODE = 0x01;
    public static final byte CNODE = 0x02;
    private Tree.XPowerProvider powers;
    private BigInteger modulus;

    public BigInteger evalTree(Tree.Node globalTerms, Tree.XPowerProvider powers, BigInteger modulus) {
        this.powers = powers;
        this.modulus = modulus;
        Stack<StackEl> stack = new Stack<>();
        stack.push(new TermEl(globalTerms));
        while (!stack.empty()) {
            StackEl top = stack.pop();
            if (top instanceof TermEl) {
                TermEl topTerms = (TermEl) top;
                Tree.Node terms = topTerms.terms;
                if (terms == null) {
                    if (topTerms.parent >= 0) {
                        ((ParentEl) stack.get(topTerms.parent)).setNode(topTerms.type, null);
                    }
                    continue;
                }

                stack.push(new ParentEl(top.parent, top.type, topTerms.terms, terms.maxX, terms.minP));
                int parentIdx = stack.size() - 1;

                stack.push(new TermEl(parentIdx, ANODE, terms.aNode));
                stack.push(new TermEl(parentIdx, CNODE, terms.cNode));
            } else if (top instanceof ParentEl) {

                ParentEl top1 = (ParentEl) top;
                BigInteger val = top1.getVal();
                if (top.parent >= 0) {
                    ((ParentEl) stack.get(top.parent)).setNode(top.type, val);
                } else {
                    return val;
                }
            }
        }
        return null;
    }

    static class StackEl {
        int parent = -1;
        byte type;
    }

    static class TermEl extends StackEl {
        Tree.Node terms;

        public TermEl(Tree.Node terms) {
            this.terms = terms;
        }

        public TermEl(int parent, byte type, Tree.Node terms) {
            this.parent = parent;
            this.type = type;
            this.terms = terms;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", TermEl.class.getSimpleName() + "[", "]")
                    .add("parent=" + parent)
                    .add("type=" + type)
                    .add("terms=" + terms)
                    .toString();
        }
    }

    class ParentEl extends StackEl {
        private final Tree.Node terms;
        int x;
        int p;
        BigInteger anode;
        BigInteger cnode;

        public ParentEl(int parent, byte type, Tree.Node terms, int maxX, int minP) {
            this.terms = terms;
            this.parent = parent;
            this.type = type;
            x = maxX;
            p = minP;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", ParentEl.class.getSimpleName() + "[", "]")
                    .add("parent=" + parent)
                    .add("type=" + type)
                    .add("terms=" + terms)
                    .add("x=" + x)
                    .add("p=" + p)
                    .add("anode=" + anode)
                    .add("cnode=" + cnode)
                    .toString();
        }

        public void setNode(byte type, BigInteger node) {
            if (type == ANODE) {
                this.anode = node;
            } else {
                this.cnode = node;
            }

        }

        public BigInteger getVal() {
            if (this.terms.val != null) {
                return this.terms.val.mod(modulus);
            }
            if (anode == null) {
                anode = BigInteger.ZERO;
            } else {
                anode = anode.multiply(powers.get(x, p)).mod(modulus);
            }
            if (cnode != null) {
                anode = anode.add(cnode).mod(modulus);
            }
            this.terms.val = anode;
//            System.out.println(anode);
            return anode;
        }
    }

}
