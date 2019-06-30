package server;

import java.net.ServerSocket;

/**
 * 获取本机打开的端口
 * Created by nextGood on 2017/9/29.
 */
public class ScanPort {

    public static void main(String[] args) {
        if (args.length > 0 && null != args[0]) {
            String[] ports = args[0].split("-");
            int max, min = 1;
            if (ports.length > 1) {
                min = Integer.parseInt(ports[0]);
                max = Integer.parseInt(ports[1]);
            } else {
                max = Integer.parseInt(ports[0]);
            }
            for (int i = min; i < max; i++) {
                try {
                    new ServerSocket(i).close();
                } catch (Exception e) {
                    System.out.println("端口：" + i + "被绑定。");
                }
            }
        }
    }
}