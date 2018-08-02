1.线程通信-使用Lock和Condition接口:
wait和notify方法,只能被同步监听锁对象来调用,否则报错IllegalMonitorStateException.
那么现在问题来了,Lock机制根本就没有同步锁了,也就没有自动获取锁和自动释放锁的概念.
因为没有同步锁,所以Lock机制不能调用wait和notify方法.

2.解决方案:Java5中提供了Lock机制的同时提供了处理Lock机制的通信控制的Condition接口.
从Java5开始,可以:
1):使用Lock机制取代synchronized 代码块和synchronized 方法.
2):使用Condition接口对象的await,signal,signalAll方法取代Object类中的wait,notify,notifyAll方法.

3.死锁
当A线程等待由B线程持有的锁,而B线程正在等待A线程持有的锁时,发生死锁现象,JVM不检测也不试图避免这种情况,所以程序员必须保证不导致死锁.

*避免死锁法则: 当多个线程都要访问共享的资源A,B,C时,保证每一个线程都按照相同的顺序去访问他们,比如都先访问A,接着B,最后C.*

Thread类中过时的方法:
suspend():使正在运行的线程放弃CPU,暂停运行.
resume():是暂停的线程恢复运行.
注意:因为容易导致死锁,所以已经被废弃了.
死锁情况:
A线程获得对象锁,正在执行一个同步方法,如果B线程调用A线程的suspend方法,此时A线程暂停运行,此时A线程放弃CPU,但是不会放弃占用的锁.
