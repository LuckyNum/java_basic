package qaz.wsx._5_synchronized;

import java.util.EnumMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Q:
 * 存在50个苹果,现在有请三个童鞋(小A,小B,小C)上台表演吃苹果.
 * 因为A,B,C三个人可以同时吃苹果,此时得使用多线程技术来实现这个案例.
 * @author lch
 * @since 2018年07月30日 20:10:57
 */
class AppleLock implements Runnable {

    private int num = 50;
    private final Lock lock = new ReentrantLock();
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            // 同步代码块
            eat();
        }
    }

    /**
     * lock方法
     */
    private void eat() {
        //进入方法立马加锁
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "吃了编号为：" + num + " 的苹果");
                //模拟网路延迟
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }

    }
}

public class LockDemo {
    public static void main(String[] args) {
        AppleLock apple = new AppleLock();
        new Thread(apple, "AAA").start();
        new Thread(apple, "BBB").start();
        new Thread(apple, "CCC").start();
    }
}
