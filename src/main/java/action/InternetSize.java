package action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取网络资源大小
 *
 * @author nextGood
 * @date 2019/7/15
 */
public class InternetSize {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.yahoo.com/");
            URLConnection urlConnection = url.openConnection();
            int contentLength = urlConnection.getContentLength();
            System.out.println("网站大小：" + contentLength + "字节");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}