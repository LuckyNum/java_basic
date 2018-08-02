在JDK的java.util包中提供了Timer类,可以定时执行特定的任务.
TimerTask类表示定时器执行的某一项任务.
常用方法:
schedule(TimerTask task,long delay,long period):
schedule(TimerTask task,long delay):

ThreadGroup类表示线程组,可以对一组线程进行集中管理.
用户在创建线程对象时,可以通过构造器指定其所属的线程组.
Thread(ThreadGroup group,String name);
如果A线程创建了B线程,如果没有设置B线程的分组,那么B线程加入到A线程的线程组.
一旦线程加入某个线程组,该线程就一直存在于该线程组中直到线程死亡,不能在中途修改线程的分组.
------------------------
当Java程序运行时,JVM会创建名为main的线程组,在默认情况下,所有的线程都该改线程组下.


*线程相关的知识点:
     1):线程安全的集合对象(留到集合框架中去).
     2):ThreadLocal:本地线程变量,ThreadLocal模式.
     3):线程池,Java8堆线程池新增的功能.
     4):Java5新增的线程并发操作.
