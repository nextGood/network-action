package net.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 随机端口
 *
 * @author nextGood
 * @date 2019/7/26
 */
public class RandomPort {
    public static void main(String[] args) {
        try {
            System.out.println("run on port : " + new ServerSocket(0).getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}