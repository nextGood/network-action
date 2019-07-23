package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP Discard 服务端
 *
 * @author nextGood
 * @date 2019/7/22
 */
public class UdpDiscardServer {
    private static final Integer PORT = 9;
    private static final Integer MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket packet = null;
        byte[] bytes = new byte[MAX_PACKET_SIZE];
        try {
            socket = new DatagramSocket(PORT);
            packet = new DatagramPacket(bytes, bytes.length);
            while (true) {
                try {
                    socket.receive(packet);
                    String data = new String(packet.getData(), "UTF-8");
                    System.out.println(packet.getAddress() + " at port " + packet.getPort() + ":" + data);
                    // 在接收到各个数据报时，packet的长度就会设置为这个数据报中数据的长度。
                    // 在循环的最后一步，包的长度要重置为最大的可能值，否则入站包的大小会限制为前面所有包的最小值。
                    packet.setLength(bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                socket.close();
            }
        }
    }
}