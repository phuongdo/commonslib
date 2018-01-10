package utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadfileTest {

    @Test
    public void testUploadFile() throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://api2.dev.netobjex.com/api/cw-services/d188fbf9-748d-4f5a-b51b-1fff446fd097/upload?access_token=GsPkHTkjkPHEuM27FJfwII00MHUG7i7sA1d8PHoEn00iEgx1K7zZycObKGiNwl45");

        httppost.addHeader("content-type", "application/x-www-form-urlencoded;charset=utf-8");
        File file = new File("data/carWash__20180110_091317136.jpg");
        FileEntity entity = new FileEntity(file);
        httppost.setEntity(entity);

        System.out.println("executing request " + httppost.getRequestLine() + httppost.getConfig());
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();

        System.out.println(response.getStatusLine());
        if (resEntity != null) {;
            System.out.println(EntityUtils.toString(resEntity));
        }

        httpclient.close();
    }

}
