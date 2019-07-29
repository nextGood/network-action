package net.server;

import java.net.ServerSocket;

/**
 * 查找本地可用端口
 *
 * @author nextGood
 * @date 2019/7/26
 */
public class LocalPortScanner {
    public static void main(String[] args) {
        for (int i = 0; i < 65537; i++) {
            try {
                new ServerSocket(i);
                System.out.println("There is nothing on the port:" + i);
            } catch (Exception e) {
                //e.printStackTrace();
                //System.out.println("There is a server on port:" + i);
            }
        }
    }
}