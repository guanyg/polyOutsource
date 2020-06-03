package me.yung.poly;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.yung.poly.generated.Poly;
import me.yung.poly.generated.TestGas;
import me.yung.poly.jo.Polynomial;
import me.yung.poly.jo.Task;
import me.yung.poly.jo.Term;
import me.yung.poly.jo.TestCase;
import org.jetbrains.annotations.NotNull;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class User {
    private final Credentials credentials;
    SharedStorage sharedStorage = SharedStorage.getInstance();
    private TestCase tc;
    private Poly contract;

    public User(String key, TestCase tc) {
        this(key);
        this.tc = tc;
    }

    public User(String key) {
        this.credentials = Credentials.create(key);
    }

    public TestGas deployTestGas() throws Exception {
        return TestGas.deploy(Main.web3j, credentials, new DefaultGasProvider(), BigInteger.valueOf(1L)).send();
    }

    @NotNull
    private static Polynomial buildPolynomial(TestCase tc) {

        return new Polynomial(IntStream.range(0, tc.getNumTerms()).filter(_i -> Math.random() > 0.5).mapToObj(_i -> {
            Random r = new Random();
            int k = tc.getDegree();

            int x = tc.getX();
            int offset = r.nextInt(x);
            List<Term.XPowered> xp = new ArrayList<>();
            for (int i = 1; i < x && k > 0; i++) {
                int p = r.nextInt(k + 1);
                if (p != 0) {
                    xp.add(new Term.XPowered((i + offset) % x, p));
                    k -= p;
                }
            }
            if (k > 0) {
                xp.add(new Term.XPowered(offset, k));
            }
            return new Term(BigInteger.valueOf(5), xp);
        }).collect(Collectors.toList())
        );
    }

    public Task buildTask() throws IOException {
        Gson gson = new GsonBuilder().create();
        long t0 = System.nanoTime();
        tc.setT0(t0);
        String poly = gson.toJson(buildPolynomial(tc));
        Random r = new Random();
        String x = gson.toJson(
                IntStream.range(0, tc.getX()).mapToObj(_i -> BigInteger.valueOf(r.nextInt(1 << 16))).collect(Collectors.toList()));
        long t1 = System.nanoTime();
        tc.setBuildTask(t1 - t0);
        Task task = new Task(sharedStorage.put(poly), sharedStorage.put(x));
        tc.setPublishTaskIPFS(System.nanoTime() - t1);
        return task;
    }

    public void verify(int... i) throws Exception {
        long t0 = System.nanoTime();
        contract.verify(IntStream.of(i).mapToObj(BigInteger::valueOf).collect(Collectors.toList())).send();
        tc.setUserVerify(System.nanoTime() - t0);
    }

    public String publishTask(Task t) throws Exception {
        long t0 = System.nanoTime();
        this.contract = Poly.deploy(Main.web3j, credentials, new DefaultGasProvider(), BigInteger.valueOf(1000), t.getA_addr(), t.getX_addr(), BigInteger.valueOf(1000), BigInteger.valueOf(1592069599), BigInteger.valueOf(1592069599)).send();
        tc.setPublishTaskETH(System.nanoTime() - t0);
//        this.contract = Poly.deploy(Main.web3j, credentials, new DefaultGasProvider(), BigInteger.valueOf(1000), "test", "test2", BigInteger.valueOf(1000), BigInteger.valueOf(1592069599), BigInteger.valueOf(1592069599)).send();
        return contract.getContractAddress();
    }

    public void setTc(TestCase tc) {
        this.tc = tc;
    }
}
