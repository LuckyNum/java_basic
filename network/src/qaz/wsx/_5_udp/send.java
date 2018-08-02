package qaz.wsx._5_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author lch
 * @since 2018年08月02日 15:29:37
 */
public class send {
    public static void main(String[] args) throws IOException {
        String msg = "hello world";

        DatagramSocket socket = new DatagramSocket(10010);
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
                InetAddress.getLocalHost(), 10086);
        socket.send(packet);
        socket.close();
    }
}
