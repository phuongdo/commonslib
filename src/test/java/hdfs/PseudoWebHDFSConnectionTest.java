package hdfs;

import commons.hdfs.PseudoWebHDFSConnection;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.security.authentication.client.AuthenticationException;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

public class PseudoWebHDFSConnectionTest {

    static PseudoWebHDFSConnection pConn = null;

//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//        pConn = new PseudoWebHDFSConnection("http://ec2-34-204-179-200.compute-1.amazonaws.com:50070", "vora", "");
//    }

    @Before
    public void setUp() throws Exception {
        System.out.println(" setUp... per Test ...");
        //pConn = new PseudoWebHDFSConnection("http://api.0.efoxconn.com:14000", "wesley", "anything");
        pConn = new PseudoWebHDFSConnection("http://master.i-0eb83c3478911ed5d.cluster:50070", "vora", "");
    }

    @Test
    public void getHomeDirectory() throws MalformedURLException, IOException, AuthenticationException {
        pConn.getHomeDirectory();
    }


    @Test
    public void listStatus() throws MalformedURLException, IOException, AuthenticationException {
        String path = "user/vora";
        System.out.println(pConn.listStatus(path));
    }

    @Test
    public void create() throws MalformedURLException, IOException, AuthenticationException {
        String path = "user/vora/data2.log";
        FileInputStream is = new FileInputStream(new File("C://data//data.log"));
        pConn.create(path, is);

        // logging


    }

    @Test
    public void append() throws MalformedURLException, IOException, AuthenticationException {
        String path = "user/vora/data2.log";
        String newLine = System.getProperty("line.separator");
        String initialString = "\"1514283799\",\"0b8d8f58-9a98-49d5-8c0e-3ae40ca485cc,\",\"ced1a569-000f-f010-9c53-2bb27fb3b000,\",\"KRDMMXDOMTSE35P0,\",\"{\"queueName\":\"videochannel\",\"payloadType\":\"json\",\"fee\":10,\"customerID\":\"ced1a569-000f-f010-9c53-2bb27fb3b000\",\"correlationId\":\"0b8d8f58-9a98-49d5-8c0e-3ae40ca485cc\",\"userName\":\"hung@netobjex.com\",\"deviceId\":\"KRDMMXDOMTSE35P0\",\"payload_timestamp\":\"1514283799 \",\"fileId\":\"0b8d8f58-9a98-49d5-8c0e-3ae40ca485cc\"},\",\"json,\",\"videochannel\"" + newLine;
        InputStream in = IOUtils.toInputStream(initialString, StandardCharsets.UTF_8);
        try {
            System.out.println(pConn.append(path, in));
        } catch (FileNotFoundException ex) {

            pConn.create(path, in);

        }
        // logging


    }

    @Test
    public void getstatus() throws Exception {

        String path = "tmp/logs";
        System.out.println(pConn.mkdirs(path));
    }


}
