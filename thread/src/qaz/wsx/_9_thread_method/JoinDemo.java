package qaz.wsx._9_thread_method;

/**
 * 联合线程
 * @author lch
 * @since 2018年08月02日 10:50:22
 */
class Join extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Join" + i);
        }
    }
}

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("begin....");
        Join j = new Join();
        for (int i = 0; i < 33; i++) {
            System.out.println("main " + i);
            if (i == 10) {
                j.start();
            }
            if (i == 20) {
                j.join();
            }
        }
        System.out.println("end...");
    }
}
