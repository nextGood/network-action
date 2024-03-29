package net.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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
            Socket socket = serverSocket.accept();
            // 每一个连接生成一个新线程，大量几乎同时的入站连接可能导致它生成极大数量的线程
            // 最终，Java虚拟机会耗尽内存而崩溃
            new DayTimeThread(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class DayTimeThread extends Thread {
        private Socket socket;

        public DayTimeThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                writer.write(new Date().toString() + "\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != socket) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}