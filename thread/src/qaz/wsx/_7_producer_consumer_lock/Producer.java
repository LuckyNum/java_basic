package qaz.wsx._7_producer_consumer_lock;

/**
 * @author lch
 * @since 2018年07月31日 14:29:25
 */
public class Producer implements Runnable{
    private ShareResource resource = null;

    public Producer(ShareResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                resource.push("张三", "男" + i);
            } else {
                resource.push("王二麻子", "女" + i);
            }
        }
    }
}
