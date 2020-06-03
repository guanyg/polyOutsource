package me.yung.poly;

import me.yung.poly.generated.TestGas;
import me.yung.poly.jo.TestCase;
import me.yung.poly.jo.Tree;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.exceptions.TransactionException;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static me.yung.poly.Util.padding;

public class TestGasOracle {
    private final Credentials credentials;
    private TestCase tc;
    private TestGas contract;

    public TestGasOracle(String key, TestGas testGas) {
        this.credentials = Credentials.create(key);
        this.contract = testGas;
    }


    public void verify(List<Tree.ArgNode> nodes) throws Exception {
        Random random = new Random();
        Tree.ArgNode node = nodes.get(random.nextInt(nodes.size()));

        if (!SimTestGas.verify_node(node)) {
            System.exit(-1);
        }

//        Tree.ArgNode node = nodes.get(i.getVal().intValue());
        List<byte[]> vals = Collections.singletonList(padding(node.getVal().toByteArray()));
        List<byte[]> avals = Collections.singletonList(padding(node.getAval().toByteArray()));
        List<byte[]> cvals = Collections.singletonList(padding(node.getCval().toByteArray()));
        List<byte[]> x = Collections.singletonList(padding(node.getXval().toByteArray()));
        List<BigInteger> p = Collections.singletonList(BigInteger.valueOf(node.getP()));
        List<byte[]> modulus = Collections.singletonList(padding(node.getModulus().toByteArray()));
        try {
            BigInteger gas = contract.feed_proof_path(vals, avals, cvals, x, p, modulus).send().getGasUsed();
            tc.setGasUsage(gas.longValue());
        } catch (TransactionException e) {
            System.out.println(node);
            System.exit(-1);
        }

    }

    public void setTc(TestCase tc) {
        this.tc = tc;
    }
}
