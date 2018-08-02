package qaz.wsx._6_producer_consumer;

/**
 * @author lch
 * @since 2018年07月31日 14:29:46
 */
public class App {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();

        new Thread(new Producer(resource)).start();

        new Thread(new Consumer(resource)).start();
    }
}
