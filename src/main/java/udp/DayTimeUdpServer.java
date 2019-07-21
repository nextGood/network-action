package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;

/**
 * dayTime协议服务端
 *
 * @author nextGood
 * @date 2019/7/21
 */
public class DayTimeUdpServer {
    private static final Integer PORT = 1688;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            while (true) {
                DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                socket.receive(request);
                String dateTime = new Date().toString();
                byte[] data = dateTime.getBytes();
                DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                socket.send(response);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}