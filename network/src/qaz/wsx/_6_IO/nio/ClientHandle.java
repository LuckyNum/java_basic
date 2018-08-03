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
 * @author lch
 * @since 2018年08月03日 14:51:29
 */
public class ClientHandle implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean started;

    public ClientHandle(String ip,int port) {
        this.host = ip;
        this.port = port;
        try {
            // 创建选择器
            selector = Selector.open();
            // 打开监听通道
            socketChannel = SocketChannel.open();
            // 如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
            socketChannel.configureBlocking(false);
            started = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.started = false;
    }

    @Override
    public void run() {
        try{
            doConnect();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        //循环遍历selector
        while (started) {
            try {
                //无论是否有读写事件发生，selector每隔1s被唤醒一次
                selector.select(1000);
                //阻塞,只有当至少一个注册的事件发生的时候才会继续.
                //selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while(it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleInput(key);
                    }catch(Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 链接
     * @throws IOException
     */
    private void doConnect() throws IOException{
        if(socketChannel.connect(new InetSocketAddress(host,port))) {
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 发送消息
     * @param msg
     * @throws Exception
     */
    public void sendMsg(String msg) throws Exception{
        socketChannel.register(selector, SelectionKey.OP_READ);
        NIOUtils.doWrite(socketChannel, msg);
    }

    /**
     * 处理输入
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect()) {
                    ;
                } else {
                    System.exit(1);
                }
            }
            //读消息
            if (key.isReadable()) {
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
                    System.out.println("客户端 - 收到消息：" + s);
                } else if (size < 0) {
                    //链路已经关闭，释放资源
                    key.cancel();
                    sc.close();
                }
            }
        }
    }
}
