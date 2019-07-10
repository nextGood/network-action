package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通过IP获取主机名
 *
 * @author nextGood
 * @date 2019/7/11
 */
public class ByIpGainDomain {
    public static void main(String[] args) {
        String ip = "255.255.245.1873";
        String[] ipSplit = ip.split("[.]");
        byte[] ipBytes = new byte[4];
        for (int i = 0; i < ipSplit.length; i++) {
            int m = Integer.parseInt(ipSplit[i]);
            byte b = (byte) (m & 0xff);
            ipBytes[i] = b;
        }
        try {
            InetAddress address = InetAddress.getByAddress(ipBytes);
            System.out.println("通过IP获取域名:" + address.getCanonicalHostName());
            System.out.println("通过IP获取主机名:" + address.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}