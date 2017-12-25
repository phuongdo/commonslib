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
        pConn = new PseudoWebHDFSConnection("http://ec2-34-204-179-200.compute-1.amazonaws.com:50070", "vora", "");
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
        String path = "tmp/logs/data2.log";
        FileInputStream is = new FileInputStream(new File("C://data//data.log"));
        pConn.create(path, is);

        // logging


    }

    @Test
    public void append() throws MalformedURLException, IOException, AuthenticationException {
        String path = "tmp/logs/data.log";
        String newLine = System.getProperty("line.separator");
        String initialString = "newline2,1999" + newLine;
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
