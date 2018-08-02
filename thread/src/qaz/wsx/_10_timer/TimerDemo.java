package qaz.wsx._10_timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 * @author lch
 * @since 2018年08月02日 11:17:49
 */
class PrintDemo extends TimerTask {
    @Override
    public void run(){
        //在3秒之后打印内容10次
        System.out.println("六一节快乐~");
    }
}

public class TimerDemo {
    public static void main(String[] args) {
        System.out.println("begin...");
        for (int i = 0; i < 10; i++) {
            //此处Timer必须设置为前台线程,否则看不到效果
            new Timer().schedule(new PrintDemo(), 3000);
        }
        System.out.println("end...");
    }
}