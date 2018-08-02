package qaz.wsx._4_tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lch
 * @since 2018年08月02日 14:50:33
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建服务端并制定端口
        ServerSocket server = new ServerSocket(9999);
        System.out.println("服务端准备就绪");

        // 接受连接该服务端的客户端对象
        Socket client = server.accept();

        // 显示连接过来的客户端信息
        System.out.println("连接的客户端IP:" + client.getInetAddress());

        // 获取到客户端的输出流对象给客户端输出数据
        boolean accept = true;
        while (accept) {
            String data = "密码?";
            PrintStream out = new PrintStream(client.getOutputStream());
            out.print(data);
            out.close();
        }

        Thread.sleep(2000);
        server.close();
    }
}
