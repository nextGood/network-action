package net.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 基于通道的UDP Echo 服务端
 *
 * @author nextGood
 * @date 2019/7/22
 */
public class UdpEchoServerWithChannel {
    private static final Integer PORT = 7;
    private static final Integer MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();
            InetSocketAddress address = new InetSocketAddress(PORT);
            socket.bind(address);
            ByteBuffer buffer = ByteBuffer.allocate(MAX_PACKET_SIZE);
            while (true) {
                SocketAddress client = channel.receive(buffer);
                buffer.flip();
                channel.send(buffer, client);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}