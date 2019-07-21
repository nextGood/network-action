package udp;

import java.io.IOException;
import java.net.*;

/**
 * dayTime协议客户端
 *
 * @author nextGood
 * @date 2019/7/21
 */
public class DayTimeUDPClient {
    private static final Integer PORT = 80;
    private static final String HOST_NAME = "time.nist.gov";

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(0);
            socket.setSoTimeout(1000);
            InetAddress host = InetAddress.getByName(HOST_NAME);
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, PORT);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.send(request);
            socket.receive(response);
            String result = new String(response.getData(), 0, response.getLength(), "UTF-8");
            System.out.println(result);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}