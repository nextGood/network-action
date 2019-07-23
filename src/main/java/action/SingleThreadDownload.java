package action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 网络资源的单线程下载
 *
 * @author nextGood
 * @date 2019/7/18
 */
public class SingleThreadDownload {
    public static void main(String[] args) {
        try {
            URL url = new URL("C:\\Windows\\Web\\Wallpaper\\Windows\\life path.jpg");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            String filePath = url.getFile();
            int pos = filePath.lastIndexOf("/");
            String fileName = filePath.substring(pos + 1);
            FileOutputStream out = new FileOutputStream("D:/" + fileName);
            byte[] bytes = new byte[urlConnection.getContentLength()];
            int len = in.read(bytes);
            while (len != -1) {
                out.write(bytes, 0, len);
                in.read(bytes);
            }
            out.close();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}