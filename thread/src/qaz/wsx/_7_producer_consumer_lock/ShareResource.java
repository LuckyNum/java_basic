package qaz.wsx._7_producer_consumer_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 共享资源
 * @author lch
 * @since 2018年07月31日 14:24:01
 */
public class ShareResource {

    private String name;
    private String gender;
    private boolean isEmpty = true;

    private final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 生产者 - 存入
     * @param name
     * @param gender
     */
    public void push(String name, String gender) {
        lock.lock();
        try {
            while (!isEmpty) {
                condition.await();
            }

            this.name = name;
            Thread.sleep(10);
            this.gender = gender;

            //设置共享资源不为空，唤醒一个消费者
            isEmpty = false;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费者 - 取出
     */
    public void popup() {
        lock.lock();
        try {
            while (isEmpty) {
                condition.await();
            }

            Thread.sleep(10);
            System.out.println(this.name + "--" + this.gender);

            isEmpty = true;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}