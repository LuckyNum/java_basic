package qaz.wsx._9_thread_method;

/**
 * 线程休眠 ,多用于模拟网络延迟
 * @author lch
 * @since 2018年08月02日 10:39:39
 */
public class SleepDemo {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            Thread.sleep(100);
        }
        System.out.println("Surprise MF");
    }
}
