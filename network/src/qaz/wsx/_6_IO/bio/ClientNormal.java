package qaz.wsx._6_IO.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 阻塞式I/O创建的客户端
 * @author lch
 * @since 2018年08月03日 10:43:43
 */
public class ClientNormal {
    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String expression){
        send(DEFAULT_SERVER_PORT,expression);
    }

    public static void send(int port,String expression){
        System.out.println("发送的是：" + expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            socket = new Socket(DEFAULT_SERVER_IP,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println(expression);
            Thread.sleep(2000);
            System.out.println("服务器返回的是：" + in.readLine());
            System.out.println("---------------------------");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
