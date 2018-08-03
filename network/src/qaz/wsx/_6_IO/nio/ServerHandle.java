package qaz.wsx._6_IO.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO服务端
 * @author lch
 * @since 2018年08月03日 12:15:48
 */
public class ServerHandle implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverChannel;

    private volatile boolean started;

    /**
     * @param port
     */
    public ServerHandle(int port) {
        try {
            // 创建选择器
            selector = Selector.open();
            // 打开监听通道
            serverChannel = ServerSocketChannel.open();
            // 如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式.
            // 开启非阻塞模式
            serverChannel.configureBlocking(false);
            //绑定端口 backlog设为1024
            serverChannel.socket().bind(new InetSocketAddress(port),1024);
            //监听客户端连接请求
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            //标记服务器已开启
            started = true;
            System.out.println("服务器已启动，端口号：" + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        started = false;
    }

    @Override
    public void run() {
        while (started) {
            try {
                //无论是否有读写事件发生，selector每隔1s被唤醒一次
                selector.select(1000);
                // 阻塞,只有当至少一个注册的事件发生的时候才会继续.
                // selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = keySet.iterator();
                SelectionKey key = null;
                while (keyIterator.hasNext()) {
                    key = keyIterator.next();
                    keyIterator.remove();
                    handleInput(key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理输入
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 处理新接入的请求消息
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                //通过ServerSocketChannel的accept创建SocketChannel实例
                //完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                SocketChannel channel = ssc.accept();
                //设置为非阻塞的
                channel.configureBlocking(false);
                //注册为读
                channel.register(selector, SelectionKey.OP_READ);
            }
            //读消息
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                // 创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                // 读取请求码流，返回读取到的字节数
                int size = sc.read(buffer);
                // 读取到字节，对字节进行编解码
                if (size > 0) {
                    //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                    buffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String s = new String(bytes, "UTF-8");
                    System.out.println("服务器 - 收到消息：" + s);

                    String result = "【服务器 - 处理后：<" + s + ">】";
                    NIOUtils.doWrite(sc,result);
                } else if (size < 0) {
                    //链路已经关闭，释放资源
                    key.cancel();
                    sc.close();
                }
            }
        }
    }
}
