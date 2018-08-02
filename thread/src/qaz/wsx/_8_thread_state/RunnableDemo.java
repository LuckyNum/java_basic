package qaz.wsx._8_thread_state;

/**
 * @author lch
 * @since 2018年08月02日 10:13:34
 */
class Other extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("other" + i);
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) {

    }
}
