package qaz.wsx._6_producer_consumer;

/**
 * @author lch
 * @since 2018年07月31日 14:29:34
 */
public class Consumer implements Runnable {
    private ShareResource resource = null;

    public Consumer(ShareResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            resource.popup();
        }
    }
}
