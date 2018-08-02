package qaz.wsx._4_tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * @author lch
 * @since 2018年08月02日 14:54:20
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建客户端,并制定接收的服务端IP地址和端口
        Socket client = new Socket("localhost",9999 );

        // 获取客户端的输入流对象
        Scanner sc = new Scanner(client.getInputStream());

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            System.out.println(str);
        }

        Thread.sleep(2000);
        sc.close();
        client.close();
    }
}
