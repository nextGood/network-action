package blog.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 问题：jdk1.8下实际运行跟例子结果不一致
 * 描述：模拟请求队列-服务端
 * 介绍：http://www.blogjava.net/nokiaguy/archive/2009/07/12/286472.html
 * 时间：2017/10/11 15:56
 * 码者: Administrator
 */
public class RequestQueueServer {
    public static void main(String[] args) throws Exception {
        int count = 0, queueLength = 5;
        ServerSocket serverSocket = new ServerSocket(1025, queueLength);
        System.out.println("绑定端口1025，按下回车键接收客户端请求");
        // 设置休眠3秒的作用是观察队列容量
        // Thread.sleep(3000);
        System.in.read();
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("接收第" + (count + socket.getInputStream().read()) + "个客户端请求");
        }
    }
}