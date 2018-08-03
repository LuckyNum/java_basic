1.基本概念
    NIO我们一般认为是New I/O,但民间跟多人称之为Non-block I/O，即非阻塞I/O，因为这样叫，更能体现它的特点
    NIO提供了与传统BIO模型中的Socket和ServerSocket相对应的SocketChannel和ServerSocketChannel两种不同的套接字通道实现
    新增的着两种通道都支持阻塞和非阻塞两种模式。
    阻塞模式使用就像传统中的支持一样，比较简单，但是性能和可靠性都不好；非阻塞模式正好与之相反

    缓冲区 Buffer:
        a.Buffer是一个对象，包含一些要写入或者读出的数据。
        b.在NIO库中，所有数据都是用缓冲区处理的。
        在读取数据时，它是直接读到缓冲区中的；在写入数据时，也是写入到缓冲区中。任何时候访问NIO中的数据，都是通过缓冲区进行操作。
        c.缓冲区实际上是一个数组，并提供了对数据结构化访问以及维护读写位置等信息。
        d.具体的缓存区有这些：
        ByteBuffer、CharBuffer、 ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer。
        他们实现了相同的接口：Buffer。
    通道 Channel:
        我们对数据的读取和写入要通过Channel，它就像水管一样，是一个通道。
        通道不同于流的地方就是通道是双向的，可以用于读、写和同时读写操作。
        底层的操作系统的通道一般都是全双工的，所以全双工的Channel比流能更好的映射底层操作系统的API。

        Channel主要分两大类：
        SelectableChannel：用户网络读写
        FileChannel：用于文件操作
    多路复用器 Selector:
         Selector提供选择已经就绪的任务的能力：Selector会不断轮询注册在其上的Channel，如果某个Channel上面发生读或者写事件，这个Channel就处于就绪状态，
         会被Selector轮询出来，然后通过SelectionKey可以获取就绪Channel的集合，进行后续的I/O操作。
         一个Selector可以同时轮询多个Channel，因为JDK使用了epoll()代替传统的select实现，
         所以没有最大连接句柄1024/2048的限制。所以，只需要一个线程负责Selector的轮询，就可以接入成千上万的客户端。
         关于epoll:https://www.cnblogs.com/linganxiong/p/5583415.html
