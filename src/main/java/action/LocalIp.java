package action;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取本地主机IP地址
 *
 * @author nextGood
 * @date 2019/7/1
 */
public class LocalIp {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        System.out.println(hostAddress);
        byte[] address = localHost.getAddress();
        System.out.println(address);
    }
}