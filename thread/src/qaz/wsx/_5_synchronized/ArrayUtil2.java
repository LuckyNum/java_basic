package qaz.wsx._5_synchronized;

/**
 * @author lch
 * @since 2018年07月31日 11:34:33
 */
public class ArrayUtil2 {
    private ArrayUtil2(){
    }

    private static ArrayUtil2 instance = null;

    /**
     * synchronized的作用域太大了,损耗性能------->尽量减小synchronized的作用域
     * @return
     */
    synchronized public static ArrayUtil2 getInstance() {
        if (instance == null) {
            instance = new ArrayUtil2();
        }
        return instance;
    }

    /**
     * 同步锁的是ArrayUtil2
     * @return
     */
    public static ArrayUtil2 getInstance2() {
        if (instance == null) {
            synchronized (ArrayUtil2.class) {
                if (instance == null) {
                    instance = new ArrayUtil2();
                }
            }
        }
        return instance;
    }

    public void sort(int[] arr) {
        //do something...
    }
}
