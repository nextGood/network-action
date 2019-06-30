package blog.server;

import java.net.ServerSocket;

/**
 * 描述：获取本机端口使用情况
 * 介绍：http://www.blogjava.net/nokiaguy/archive/2009/07/12/286472.html
 * 时间：2017/10/11 15:20
 * 码者: Administrator
 */
public class ScanPort {
    public static void main(String[] args) {
        int minport = 1, maxport = 65535;
        if (args.length == 0 || args.length > 2) return;
        if (args.length == 1) {
            maxport = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            minport = Integer.parseInt(args[0]);
            maxport = Integer.parseInt(args[1]);
        }
        for (int i = minport; i <= maxport; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(i);
                serverSocket.close();
            } catch (Exception e) {
                System.out.println("端口：" + i + "已使用中。");
            }
        }
    }
}