package action.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 服务端Socket
 *
 * @author nextGood
 * @date 2019/7/31
 */
public class GetServer {
    private static final Integer PORT = 8710;

    public static void main(String[] args) {
        AtomicInteger number = new AtomicInteger(0);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务端套接字已经创建成功。");
            while (true) {
                System.out.println("等待客户端的连接...");
                serverSocket.accept();
                number.incrementAndGet();
                System.out.println("第" + number + "个客户端连接成功");
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