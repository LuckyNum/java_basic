package qaz.wsx._6_IO.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端
 * @author lch
 * @since 2018年08月03日 10:25:44
 */
public final class ServerNormal {
    /**
     * 默认的端口号
     */
    private static int DEFAULT_PORT = 12345;
    /**
     * 单例的ServerSocket
     */
    private static ServerSocket server;

    /**
     * 根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值
     * @throws IOException
     */
    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    /**
     * 这个方法不会被大量并发访问，不太需要考虑效率，直接进行方法同步就行了
     * @param port
     * @throws IOException
     */
    public synchronized static void start(int port) throws IOException{
        if (server != null) {
            return;
        }
        try {
            server = new ServerSocket(port);
            System.out.println("服务器已启动，端口号：" + port);
            while(true){
                Socket socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码
                //然后创建一个新的线程处理这条Socket链路
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("服务器已关闭。");
                server.close();
                server = null;
            }
        }
    }

}
