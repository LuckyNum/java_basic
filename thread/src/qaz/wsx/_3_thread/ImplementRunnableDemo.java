package qaz.wsx._3_thread;

/**
 * @author lch
 * @since 2018年07月28日 21:47:45
 */
class MusicRunnableImpl implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Play Music " + i);
        }
    }
}
public class ImplementRunnableDemo {
    public static void main(String[] args) {
        // 主线程
        for (int i = 0; i < 50; i++) {
            System.out.println("Play Game " + i);
            if (i == 10) {
                // 创建线程对象，并启动 (这里Debug)
                MusicRunnableImpl musicRunnable = new MusicRunnableImpl();
                Thread thread = new Thread(musicRunnable);
                thread.start();
            }
        }
    }
}
