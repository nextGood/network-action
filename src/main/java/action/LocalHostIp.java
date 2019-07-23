package action;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 显示域名对应地址
 *
 * @author nextGood
 * @date 2019/7/1
 */
public class LocalHostIp {
    public static void main(String[] args) {
        try {
            InetAddress address1 = InetAddress.getByName("www.oreilly.com");
            System.out.println(address1);
            InetAddress address2 = InetAddress.getByName("104.118.140.196");
            System.out.println(address2);
            System.out.println(InetAddress.getByName("163.177.151.110").getCanonicalHostName());
            InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
            for (InetAddress address : addresses) {
                System.out.println(address);
            }
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
            byte[] ipAddresses = new byte[]{(byte) 163, (byte) 177, (byte) 151, 109};
            System.out.println(InetAddress.getByAddress("www.baidu.com", ipAddresses));
            System.out.println(InetAddress.getLocalHost().getHostName());

            SecurityManager sm = new SecurityManager();
            sm.checkConnect("www.baidu.com", 8080);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}