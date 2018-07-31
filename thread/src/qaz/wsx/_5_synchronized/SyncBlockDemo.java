package qaz.wsx._5_synchronized;

/**
 * Q:
 * 存在50个苹果,现在有请三个童鞋(小A,小B,小C)上台表演吃苹果.
 * 因为A,B,C三个人可以同时吃苹果,此时得使用多线程技术来实现这个案例.
 * @author lch
 * @since 2018年07月30日 20:10:57
 */
class AppleBlock implements Runnable {

    private int num = 50;

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            // 同步代码块
            synchronized (this) {
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
            }
        }
    }
}

public class SyncBlockDemo {
    public static void main(String[] args) {
        AppleBlock apple = new AppleBlock();
        new Thread(apple, "AAA").start();
        new Thread(apple, "BBB").start();
        new Thread(apple, "CCC").start();
    }
}
