package qaz.wsx._4_eatapple;

/**
 * Q:
 * 存在50个苹果,现在有请三个童鞋(小A,小B,小C)上台表演吃苹果.
 * 因为A,B,C三个人可以同时吃苹果,此时得使用多线程技术来实现这个案例.
 * @author lch
 * @since 2018年07月30日 20:06:06
 */
class Person extends Thread {
    /**
     * Apple number
     */
    private int num = 50;

    public Person(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (num > 0) {
                System.out.println(super.getName() + "吃了编号为：" + num-- + " 的苹果");
            }
        }
    }
}

public class ExtendsDemo {

    public static void main(String[] args) {
        new Person("AAA").start();
        new Person("BBB").start();
        new Person("CCC").start();
    }
}
