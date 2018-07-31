package qaz.wsx._3_thread;

/**
 * 匿名内部类，创建线程
 * 两种方式生成的匿名内部类有所区别
 * 类：继承方式
 * 接口：实现方式
 *
 * @author lch
 * @since 2018年07月30日 19:40:01
 */
public class AnonymousInnerClassDemo {
    public static void main(String[] args) {
        classCreate();
//        interfaceCreate();
    }

    /**
     * 类创建
     */
    public static void classCreate() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Game play" + i);
            if (i == 10) {
                //创建线程<类Thread>
                new Thread() {
                    @Override
                    public void run() {
                        for (int j = 0; j < 50; j++) {
                            System.out.println("Music Play" + j);
                        }
                    }
                }.start();
            }
        }
    }

    /**
     * 接口创建
     */
    public static void interfaceCreate() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Game play" + i);
            if (i == 10) {
                //创建线程<接口Runnable>
                //new Thread(new Runnable() {
                //                    @Override
                //                    public void run() {
                //lambda
                new Thread(() -> {
                    for (int j = 0; j < 50; j++) {
                        System.out.println("Music Play" + j);
                    }
                }).start();
            }
        }
    }
}
