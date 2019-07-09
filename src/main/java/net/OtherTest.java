package net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * desc
 *
 * @author nextGood
 * @date 2019/7/9
 */
public class OtherTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com");
            InputStream inputStream = url.openStream();
            InputStreamReader reader = new InputStreamReader(new BufferedInputStream(inputStream));
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}