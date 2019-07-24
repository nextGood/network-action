package net.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * daytime服务器
 *
 * @author nextGood
 * @date 2019/7/25
 */
public class DaytimeServer {
    private static final Integer PORT = 13;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                writer.write(new Date().toString() + "\r\n");
                writer.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}