package action.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * 客户端Socket
 *
 * @author nextGood
 * @date 2019/7/31
 */
public class GetClient {
    private static final Integer PORT = 8710;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 5; i++) {
                new Socket(HOST, PORT);
                System.out.println("连接上服务端");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}