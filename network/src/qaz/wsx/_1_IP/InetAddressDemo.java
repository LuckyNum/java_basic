package qaz.wsx._1_IP;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author lch
 * @since 2018年08月02日 11:55:00
 */
public class InetAddressDemo {
    public static void main(String[] args) throws IOException {
        InetAddress ip = InetAddress.getByName("www.baidu.com");
        System.out.println(ip.toString());
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());
        System.out.println(InetAddress.getLocalHost());
        System.out.println(InetAddress.getLocalHost().isReachable(10000));
    }
}
