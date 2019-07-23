package net.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 基于通道的UDP Discard 服务端
 *
 * @author nextGood
 * @date 2019/7/22
 */
public class UdpDiscardServerWithChannel {
    private static final Integer PORT = 19;
    private static final Integer MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();
            InetSocketAddress address = new InetSocketAddress(PORT);
            socket.bind(address);
            ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
            while (true) {
                SocketAddress client = channel.receive(buffer);
                buffer.flip();
                System.out.println(client + " says ");
                while (buffer.hasRemaining()) {
                    System.out.write(buffer.get());
                }
                System.out.println();
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}