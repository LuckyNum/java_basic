package qaz.wsx._6_IO.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @author lch
 * @since 2018年08月03日 17:45:20
 */
public class AsyncClientHandler implements CompletionHandler<Void, AsyncClientHandler>, Runnable{

    private AsynchronousSocketChannel clientChannel;
    private String host;
    private int port;
    private CountDownLatch latch;

    public AsyncClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            //创建异步的客户端通道
            clientChannel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        //创建CountDownLatch等待
        latch = new CountDownLatch(1);
        //发起异步连接操作，回调参数就是这个类本身，如果连接成功会回调completed方法
        clientChannel.connect(new InetSocketAddress(host, port), this, this);
        try {
            latch.await();
            clientChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接服务器成功
     * 意味着TCP三次握手完成
     * @param result
     * @param attachment
     */
    @Override
    public void completed(Void result, AsyncClientHandler attachment) {
        System.out.println("客户端成功连接到服务器...");
    }

    /**
     * 连接服务器失败
     * @param exc
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AsyncClientHandler attachment) {
        System.err.println("连接服务器失败...");
        exc.printStackTrace();
        try {
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    /**
     * 向服务器发送消息
     * @param msg
     */
    public void sendMsg(String msg){
        byte[] req = msg.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        //异步写
        clientChannel.write(writeBuffer, writeBuffer,new WriteHandler(clientChannel, latch));
    }
}
