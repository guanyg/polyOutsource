package me.yung.poly;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SharedStorage {
    private final IPFS ipfs;

    private SharedStorage() {
        this.ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
    }

    public static SharedStorage getInstance() {
        return inst.storage;
    }

    public String put(String content) throws IOException {
        NamedStreamable file = new NamedStreamable.ByteArrayWrapper(content.getBytes(StandardCharsets.UTF_8));
        MerkleNode node = ipfs.add(file).get(0);

        return node.hash.toBase58();
    }

    public String get(String key) throws IOException {
        return new String(ipfs.cat(Multihash.fromBase58(key)), StandardCharsets.UTF_8);
    }

    private static final class inst {
        private static final SharedStorage storage = new SharedStorage();
    }
}
