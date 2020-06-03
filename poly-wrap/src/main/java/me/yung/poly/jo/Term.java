package me.yung.poly.jo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Term {
    private final BigInteger a;
    private final List<XPowered> x;

    public Term(BigInteger a, XPowered... x) {
        this.a = a;
        this.x = new ArrayList<>(Arrays.asList(x));
    }

    public Term(BigInteger a, List<XPowered> x) {
        this.a = a;
        this.x = x;
    }

    public BigInteger getA() {
        return a;
    }

    public List<XPowered> getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Term{" +
                "a=" + a +
                ", x=" + x +
                '}';
    }

    public static class XPowered {
        int x;
        int p;

        public XPowered(int x, int p) {
            this.x = x;
            this.p = p;
        }

        public int getX() {
            return x;
        }

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }
    }
}
