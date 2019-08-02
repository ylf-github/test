import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: ylf
 * @Time: 2019/6/20 上午10:48
 * @Description:
 */
public class Mathpix {


    public static void main(String[] args) throws Exception{
        post();
    }

    public static void post()throws Exception{
        //1.创建客户端访问服务器的httpclient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //2.定义请求的url
        String url = "https://api.mathpix.com/v3/latex";
        //3.以请求的连接地址创建get/post请求对象
        HttpPost post = new HttpPost(url);
        //如果有header请求，添加header请求
        post.addHeader("appId:", "tal_100");
        post.addHeader("appKey", "I3RGJECDN0BRFG2");
        try {
            //4.向服务器发送请求，并获取返回数据
            CloseableHttpResponse response = httpClient.execute(post);
            //获取返回的状态
            int status = response.getStatusLine().getStatusCode();
            System.out.println(status);
            //获取HttpEntity消息载体对象
            HttpEntity entity = response.getEntity();
            // EntityUtils中的toString()方法转换服务器的响应数据
            String str = EntityUtils.toString(entity, "utf-8");
            System.out.println("服务器的响应是:" + str);
            //5.关闭连接
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
