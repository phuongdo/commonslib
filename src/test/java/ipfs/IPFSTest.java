package ipfs;

import io.ipfs.api.IPFS;
import io.ipfs.api.KeyInfo;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.cbor.CborObject;
import io.ipfs.cid.Cid;
import io.ipfs.multihash.Multihash;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class IPFSTest {
    IPFS ipfs = new IPFS("/ip4/ipfs/tcp/5001");


    private final Random r = new Random(33550336); // perfect

    @Test
    public void dag() throws IOException {
        byte[] object = " { \"name\":\"John\", \"age\":30, \"car\":\"Maz3\" }".getBytes();
        MerkleNode put = ipfs.dag.put("json", object);

        //Cid expected = Cid.decode("zdpuB2CbdLrUK5AgZusm4hraisDDDC135ugdmZWvMHhhsSYTb");

        Multihash result = put.hash;
//        Assert.assertTrue("Correct cid returned", result.equals(expected));


        System.out.println(result.toString());
//        byte[] get = ipfs.dag.get(expected);
//        Assert.assertTrue("Raw data equal", Arrays.equals(object, get));
    }

    @Test
    public void getDAG() throws Exception {
        Cid expected = Cid.decode("zdpuAo9mLvXzJqqo45fTrHPGkSG99fsZZLy5KepR9gkwnUVbT");
        byte[] get = ipfs.dag.get(expected);

        System.out.println(new String(get));

    }

    @Test
    public void dagCbor() throws IOException {
        Map<String, CborObject> tmp = new TreeMap<>();
        tmp.put("data", new CborObject.CborByteArray("G'day mate!".getBytes()));
        tmp.put("name", new CborObject.CborByteArray("{ \"name\":\"John\", \"age\":30, \"car\":\"Maz3\" }".getBytes()));
        CborObject original = CborObject.CborMap.build(tmp);

        byte[] object = original.toByteArray();
        MerkleNode put = ipfs.dag.put("cbor", object);

        Cid cid = (Cid) put.hash;
        byte[] get = ipfs.dag.get(cid);
        CborObject.CborMap cborMap = (CborObject.CborMap) CborObject.fromByteArray(get);

        System.out.println(new String(cborMap.values.get( new CborObject.CborString("name") ).toByteArray()));

//        Assert.assertTrue("Raw data equal", Arrays.equals(object, get));
//        Cid expected = Cid.decode("zdpuB2RwxeC5eC7oxiyzzhuZwAPd26YNRxXHvcTvgm4MbXwsC");
//        Assert.assertTrue("Correct cid returned", cid.equals(expected));
    }

    @Test
    public void keys() throws IOException {
        List<KeyInfo> existing = ipfs.key.list();
        String name = "mykey" + System.nanoTime();
        KeyInfo gen = ipfs.key.gen(name, Optional.of("rsa"), Optional.of("2048"));
        String newName = "bob" + System.nanoTime();
        Object rename = ipfs.key.rename(name, newName);
        List<KeyInfo> rm = ipfs.key.rm(newName);
        List<KeyInfo> remaining = ipfs.key.list();
        Assert.assertTrue("removed key", remaining.equals(existing));
    }


}
