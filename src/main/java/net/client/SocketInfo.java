package net.client;

import java.io.IOException;
import java.net.Socket;

/**
 * 获取Socket信息
 *
 * @author nextGood
 * @date 2019/7/24
 */
public class SocketInfo {
    public static void main(String[] args) {
        try {
            // 一旦Socket连接，就会设置这些属性，而且自此固定下来。
            Socket socket = new Socket("www.oreilly.com", 80);
            System.out.println("Connected to:" + socket.getInetAddress() +
                    " on port:" + socket.getPort() +
                    " from port:" + socket.getLocalPort() +
                    " of " + socket.getLocalAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}