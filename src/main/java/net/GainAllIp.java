package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取内网中所有IP地址
 *
 * @author nextGood
 * @date 2019/7/12
 */
public class GainAllIp {
    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            System.out.println("本地IP：" + hostAddress);
            int lastIndexOf = hostAddress.lastIndexOf(".");
            String substring = hostAddress.substring(0, lastIndexOf + 1);
            System.out.println("网段IP：" + substring);
            for (int i = 1; i <= 255; i++) {
                String newIp = substring + i;
                new IpThread(newIp).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static class IpThread extends Thread {
        private String newIp;

        public IpThread(String newIp) {
            this.newIp = newIp;
        }

        @Override
        public void run() {
            try {
                Process process = Runtime.getRuntime().exec("ping " + newIp + " -w 280 -n 1");
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String readLine = reader.readLine();
                System.out.println("ping ip：" + readLine);
                while (readLine != null) {
                    if (readLine != null && !"".equals(readLine)) {
                        if (readLine.substring(0, 2).equals("来自") || readLine.length() > 10 && "Reply from".equals(readLine.substring(0, 10))) {
                            System.out.println("网络IP：" + newIp);
                        }
                    }
                    readLine = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}