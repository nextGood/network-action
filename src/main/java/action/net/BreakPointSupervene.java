package action.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 网络资源断点续传
 *
 * @author nextGood
 * @date 2019/7/19
 */
public class BreakPointSupervene {
    public static void main(String[] args) {
        int startPosition = Integer.parseInt(args[0]);
        int endPosition = Integer.parseInt(args[1]);
        try {
            // 本地路径是访问本机的磁盘文件，以file://开头，URL网络路径是以http://开头
            URL url = new URL("file:///E|/1 (5)-10寸金沙.jpg");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "NetFox");
            String rangeProperty = "bytes=" + startPosition + "-";
            if (endPosition > 0) {
                rangeProperty += endPosition;
            }
            connection.setRequestProperty("RANGE", rangeProperty);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String file = url.getFile();
            String name = file.substring(file.lastIndexOf("/") + 1);
            FileOutputStream outputStream = new FileOutputStream("D:/" + name, true);
            byte[] bytes = new byte[4096];
            int length = inputStream.read(bytes);
            while (length != -1) {
                outputStream.write(bytes, 0, length);
                length = inputStream.read(bytes);
            }
            System.out.println("网络资源下载完成");
            outputStream.close();
            inputStream.close();
            // HTTP专用方法
            //connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}