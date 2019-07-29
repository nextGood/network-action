package net.security;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

/**
 * HttpsClient
 *
 * @author nextGood
 * @date 2019/7/26
 */
public class HttpsClient {
    private static final Integer PORT = 443;
    private static final String HOST = "www.google.com";

    public static void main(String[] args) {
        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = null;
        try {
            sslSocket = (SSLSocket) socketFactory.createSocket(HOST, PORT);

            // 启动所有密码组
            String[] suites = sslSocket.getSupportedCipherSuites();
            sslSocket.setEnabledCipherSuites(suites);
            // https在GET行中需要完全URL
            Writer writer = new OutputStreamWriter(sslSocket.getOutputStream(), "UTF-8");
            writer.write("GET http://" + HOST + "/ HTTP/1.1\r\n");
            writer.write("Host: " + HOST + "\r\n");
            writer.write("\r\n");
            writer.flush();
            // 读取响应
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            // 读取首部
            String s;
            while (!(s = in.readLine()).equals("")) {
                System.out.println(s);
            }
            System.out.println();
            // 读取长度
            String contentLength = in.readLine();
            int length = Integer.MAX_VALUE;
            try {
                length = Integer.parseInt(contentLength.trim(), 16);
            } catch (Exception e) {
            }
            System.out.println(contentLength);
            int c;
            int i = 0;
            while ((c = in.read()) != -1 && i++ < length) {
                System.out.write(c);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != sslSocket) {
                try {
                    sslSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}