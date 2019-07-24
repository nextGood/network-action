package net.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 多线程时间服务器
 *
 * @author nextGood
 * @date 2019/7/25
 */
public class MultithreaderDaytimeServer {
    private static final Integer PORT = 13;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}