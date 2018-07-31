package qaz.wsx._3_thread;

/**
 * @author lch
 * @since 2018年07月28日 21:37:00
 */
class MusicThread extends java.lang.Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Play Music " + i);
        }
    }
}

/**
 * Debug查看
 */
public class ExtendsThreadDemo {
    public static void main(String[] args) {
        // 主线程
        for (int i = 0; i < 50; i++) {
            System.out.println("Play Game " + i);
            if (i == 10) {
                // 创建线程对象，并启动 (这里Debug)
                MusicThread musicThread = new MusicThread();
                // 不可调用run(),相当于对象的方法调用
                musicThread.start();
            }
        }
    }
}
