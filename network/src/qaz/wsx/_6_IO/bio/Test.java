package qaz.wsx._6_IO.bio;

import java.util.Random;

/**
 * @author lch
 * @since 2018年08月03日 10:46:26
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
//                ServerNormal.start();
                ServerBetter.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

        Thread.sleep(100);

        Random random = new Random(System.currentTimeMillis());
        new Thread(() -> {
            while (true) {
                ClientNormal.send(String.valueOf(random.nextInt()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
