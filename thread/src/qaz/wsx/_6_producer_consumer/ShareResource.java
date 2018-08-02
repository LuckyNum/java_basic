package qaz.wsx._6_producer_consumer;

/**
 * 共享资源
 * @author lch
 * @since 2018年07月31日 14:24:01
 */
public class ShareResource {

    private String name;
    private String gender;
    private boolean isEmpty = true;

    /**
     * 生产者 - 存入
     * @param name
     * @param gender
     */
    synchronized public void push(String name, String gender) {
        try {
            // 当资源不为空时
            while (!isEmpty) {
                // 使用同步锁对象调用，表示当前线程池释放同步锁，进入等待池，只能被其他线程(消费线程)唤起
                this.wait();
            }

            this.name = name;
            Thread.sleep(10);
            this.gender = gender;

            //设置共享资源不为空，唤醒一个消费者
            isEmpty = false;
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 消费者 - 取出
     */
    synchronized public void popup() {
        try {
            // 当资源为空时
            while (isEmpty) {
                // 使用同步锁对象调用，表示当前线程池释放同步锁，进入等待池，只能被其他线程(生产线程)唤起
                this.wait();
            }

            Thread.sleep(10);
            System.out.println(this.name + "--" + this.gender);

            isEmpty = true;
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}