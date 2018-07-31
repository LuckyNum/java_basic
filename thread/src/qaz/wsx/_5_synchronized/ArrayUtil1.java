package qaz.wsx._5_synchronized;

/**
 * @author lch
 * @since 2018年07月31日 11:34:33
 */
public class ArrayUtil1 {
    private ArrayUtil1(){
    }

    private static ArrayUtil1 instance = new ArrayUtil1();

    public static ArrayUtil1 getInstance() {
        return instance;
    }

    public void sort(int[] arr) {
        //do something...
    }
}
