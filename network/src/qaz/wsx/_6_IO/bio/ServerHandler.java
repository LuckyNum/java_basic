package qaz.wsx._6_IO.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 建立连接的线程，处理一个客户端Socket链路
 * @author lch
 * @since 2018年08月03日 10:32:29
 */
public class ServerHandler implements Runnable{
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression;
            String result;
            while ((expression = in.readLine()) != null) {
                //通过BufferedReader读取一行
                //如果已经读到输入流尾部，返回null,退出循环
                //如果得到非空值，就尝试计算结果并返回
                System.err.println("服务器收到消息：" + expression);
                result = "服务器处理后：<" + expression + ">";
                out.println(result);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
