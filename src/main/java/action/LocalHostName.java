package action;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取本地主机的域名和主机名
 *
 * @author nextGood
 * @date 2019/7/9
 */
public class LocalHostName {
    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("本地主机主机名：" + localHost.getHostName());
            System.out.println("本地主机域名：" + localHost.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}