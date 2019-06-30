package blog.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述：服务端接收和发送数据
 * 介绍：http://www.blogjava.net/nokiaguy/archive/2009/nokiaguy/archive/2009/07/20/287462.html
 * 时间：2017/10/13 10:01
 * 码者: Administrator
 */
public class HttpEchoServer implements Runnable {
    private Socket socket;

    public HttpEchoServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String context;
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("HTTP/1.1 200 OK\r\n\r\n");
            while (!"".equals(context = bufferedReader.readLine())) {
                bufferedWriter.write("<html><body>" + context + "<br></body></html>");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
            //Thread.sleep(5000);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int number = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(8081, 5);
            System.out.println("服务器已经启动，端口：8081");
            while (true) {
                Socket socket = serverSocket.accept();
                number++;
                System.out.println("接收第" + number + "个请求，启动线程Thread" + number + "处理");
                new Thread(new HttpEchoServer(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}