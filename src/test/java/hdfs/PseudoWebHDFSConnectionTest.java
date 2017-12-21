package hdfs;

import commons.hdfs.PseudoWebHDFSConnection;
import org.apache.hadoop.security.authentication.client.AuthenticationException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

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
        String path = "user/vora/data.log";
        FileInputStream is = new FileInputStream(new File("C://data//data.log"));
        pConn.create(path, is);
        // logging


    }

    @Test
    public void append() throws MalformedURLException, IOException, AuthenticationException {
        String path = "user/vora/data.log";
        FileInputStream is = new FileInputStream(new File("C://data//data.log"));
        System.out.println(pConn.append(path, is));
        // logging


    }


}
