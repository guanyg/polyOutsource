package me.yung.poly.jo;

import java.util.List;

public class Polynomial {
    private final List<Term> terms;

    public Polynomial(List<Term> terms) {
        this.terms = terms;
    }

    public List<Term> getTerms() {
        return terms;
    }

    @Override
    public String toString() {
        return "Polynomial{" +
                "terms=" + terms +
                '}';
    }
}
