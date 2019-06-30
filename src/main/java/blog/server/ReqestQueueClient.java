package blog.server;

import java.net.Socket;

/**
 * 问题：jdk1.8下实际运行跟例子结果不一致
 * 描述：模拟请求队列-客户端
 * 介绍：http://www.blogjava.net/nokiaguy/archive/2009/07/12/286472.html
 * 时间：2017/10/11 15:56
 * 码者: Administrator
 */
public class ReqestQueueClient {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 30; i++) {
            Socket socket = new Socket("127.0.0.1", 1025);
            socket.getOutputStream().write(i);
            System.out.println("成功创建第" + i + "个客户端。");
        }
    }
}