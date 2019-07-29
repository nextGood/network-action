package net.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池时间服务器
 *
 * @author nextGood
 * @date 2019/7/25
 */
public class PooledDayTimeServer {
    private static final Integer PORT = 13;

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 50, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024));
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            poolExecutor.submit(new TimeServerThread(socket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class TimeServerThread implements Runnable {
        private Socket socket;

        public TimeServerThread(Socket socket) {
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