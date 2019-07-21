package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * UDP Discard 客户端
 *
 * @author nextGood
 * @date 2019/7/22
 */
public class UdpDiscardClient {
    private static final int PORT = 19;
    private static final String HOST_NAME = "localhost";

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress server = InetAddress.getByName(HOST_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = reader.readLine();
                if (".".equals(input)) {
                    break;
                }
                byte[] bytes = input.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, server, PORT);
                socket.send(datagramPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}