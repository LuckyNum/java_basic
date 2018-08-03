package qaz.wsx._6_IO.nio;

import java.util.Scanner;

/**
 * @author lch
 * @since 2018年08月03日 15:43:46
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Server.start();
        Thread.sleep(100);
        Client.start();
        while(Client.sendMsg(new Scanner(System.in).nextLine()));
    }
}
