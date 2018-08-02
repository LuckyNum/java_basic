package qaz.wsx._9_thread_method;

class DaemomThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 123; i++) {
            System.out.println(super.getName() + " " + i + " daemon:" + Thread.currentThread().isDaemon());
        }
    }
}

/**
 * 后台线程
 * @author lch
 * @since 2018年08月02日 10:41:34
 */
public class DaemomDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().isDaemon());
        for (int i = 0; i < 33; i++) {
            System.out.println("main " + i);
            if (i == 5) {
                DaemomThread deamomThread = new DaemomThread();
                //设置为后台线程,并且在调用start之前设置
                deamomThread.setDaemon(true);
                deamomThread.start();
            }
            //当前台线程结束之后.后台线程也会相应的自动结束
        }
    }
}
