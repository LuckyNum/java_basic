package qaz.wsx._6_IO.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import qaz.wsx._6_IO.aio.Server;

/**
 * 作为handler接收客户端连接
 * @author lch
 * @since 2018年08月03日 16:40:48
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncServerHandler serverHandler) {
        //继续接受其他客户端的请求
        Server.clientCount.getAndIncrement();
        System.out.println("连接的客户端数：" + Server.clientCount);
        serverHandler.channel.accept(serverHandler, this);
        //创建新的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //异步读  第三个参数为接收消息回调的业务Handler
        channel.read(buffer, buffer, new ReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AsyncServerHandler serverHandler) {
        exc.printStackTrace();
        // Decrements the count of the latch, releasing all waiting threads if the count reaches zero.
        serverHandler.latch.countDown();
    }
}
