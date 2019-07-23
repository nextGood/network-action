package action;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通过主机名获取IP
 *
 * @author nextGood
 * @date 2019/7/10
 */
public class ByDomainGainIp {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("Administrator");
            System.out.println("通过主机名获取IP:" + address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}