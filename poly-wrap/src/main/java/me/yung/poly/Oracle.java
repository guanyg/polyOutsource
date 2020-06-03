package me.yung.poly;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.disposables.Disposable;
import me.yung.poly.generated.Poly;
import me.yung.poly.jo.TestCase;
import me.yung.poly.jo.Tree;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static me.yung.poly.Util.padding;

public class Oracle {
    private final Credentials credentials;
    SharedStorage sharedStorage = SharedStorage.getInstance();
    private TestCase tc;
    private Poly contract;
    private Disposable subscribe;
    private boolean verified = false;

    public Oracle(String key, TestCase tc) {
        this(key);
        this.tc = tc;
    }

    public Oracle(String key) {
        this.credentials = Credentials.create(key);
    }

    public void stop() {
        subscribe.dispose();
    }

    public void listenTo(String taskAddr) {
        this.contract = Poly.load(taskAddr, Main.web3j, credentials, new DefaultGasProvider());
        this.subscribe = contract.getPathEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST).subscribe(log -> {
            long t0 = System.nanoTime();
            List<Tree.ArgNode> nodes = new Gson().fromJson(sharedStorage.get(log.result_addr), new TypeToken<List<Tree.ArgNode>>() {
            }.getType());
            List<byte[]> vals = new ArrayList<>(log._path_spec.size());
            List<byte[]> avals = new ArrayList<>(log._path_spec.size());
            List<byte[]> cvals = new ArrayList<>(log._path_spec.size());
            List<byte[]> x = new ArrayList<>(log._path_spec.size());
            List<BigInteger> p = new ArrayList<>(log._path_spec.size());

            for (Uint256 i :
                    log._path_spec) {
                Tree.ArgNode node = nodes.get(i.getValue().intValue());
                vals.add(padding(node.getVal().toByteArray()));
                avals.add(padding(node.getAval().toByteArray()));
                cvals.add(padding(node.getCval().toByteArray()));
                x.add(padding(node.getXval().toByteArray()));
                p.add(BigInteger.valueOf(node.getP()));
            }
            contract.feed_proof_path(vals, avals, cvals, x, p).send();
            long t1 = System.nanoTime();
            tc.setOracleVerify(t1 - t0);

            synchronized (this) {
                verified = true;
                this.notifyAll();
            }

            this.stop();
        });
    }

    public boolean getVerified() {
        return verified;
    }
}
