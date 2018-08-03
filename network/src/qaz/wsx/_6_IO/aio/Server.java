package qaz.wsx._6_IO.aio;

import java.util.concurrent.atomic.AtomicInteger;

import qaz.wsx._6_IO.aio.server.AsyncServerHandler;

/**
 * @author lch
 * @since 2018年08月03日 16:30:15
 */
public class Server {
    private static int DEFAULT_PORT = 12345;
    private static AsyncServerHandler serverHandle;
    public volatile static AtomicInteger clientCount = new AtomicInteger(0);
    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null) {
            return;
        }
        serverHandle = new AsyncServerHandler(port);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        Server.start();
    }
}
