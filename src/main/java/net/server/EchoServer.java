package net.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * echo服务器
 * TODO 运行异常
 * @author nextGood
 * @date 2019/7/26
 */
public class EchoServer {
    public static final Integer PORT = 7;

    public static void main(String[] args) {
        ServerSocketChannel serverChannel = null;
        Selector selector = null;
        try {
            serverChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = serverChannel.socket();
            InetSocketAddress socketAddress = new InetSocketAddress(PORT);
            serverSocket.bind(socketAddress);
            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_CONNECT);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socket = socketChannel.accept();
                        System.out.println("Accepted connection from " + socket);
                        socket.configureBlocking(false);
                        SelectionKey clientKey = socket.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        clientKey.attach(buffer);
                    }
                    if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer attachment = (ByteBuffer) key.attachment();
                        socketChannel.read(attachment);
                    }
                    if (key.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer attachment = (ByteBuffer) key.attachment();
                        attachment.flip();
                        socketChannel.write(attachment);
                        attachment.compact();
                    }
                } catch (Exception e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}