package qaz.wsx._6_IO.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author lch
 * @since 2018年08月03日 16:46:58
 */
public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    /**
     * 用于读取半包消息和发送应答
     */
    private AsynchronousSocketChannel channel;
    public ReadHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    /**
     * 读取到消息后的处理
     * @param result
     * @param attachment
     */
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        //flip操作
        attachment.flip();

        byte[] message = new byte[attachment.remaining()];
        attachment.get(message);

        try {
            String expression = new String(message, "UTF-8");
            System.out.println("服务器收到消息: " + expression);
            String res = "【服务器 - 处理后：<" + expression + ">】";
            //向客户端发送消息
            doWrite(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @param res
     */
    private void doWrite(String res) {
        byte[] bytes = res.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        // 异步写数据 参数与前面的read一样
        channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                //如果没有发送完，就继续发送直到完成
                if (buffer.hasRemaining()) {
                    channel.write(buffer, buffer, this);
                } else {
                    //创建新的Buffer
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    //异步读  第三个参数为接收消息回调的业务Handler
                    channel.read(readBuffer, readBuffer, new ReadHandler(channel));
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
