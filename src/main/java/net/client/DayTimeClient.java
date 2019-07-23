package net.client;

/**
 * DayTime协议客户端
 *
 * @author nextGood
 * @date 2019/7/24
 */
public class DayTimeClient {
    public static void main(String[] args) {
        // 创建对象的同时会在网络上建立连接
        new Socket(host,port);
        // 为 Socket 连接设置一个超时时间，这意味着对这个 socket 的每一个读/写都最多耗费一定的毫秒数。
        setSoTimeout();

    }
}