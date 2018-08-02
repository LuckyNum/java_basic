package qaz.wsx._5_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author lch
 * @since 2018年08月02日 15:25:44
 */
public class Receive {
    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket(10086);
        byte[] buf = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buf, 1024);
        socket.receive(packet);

        String string = new String(packet.getData(), 0, packet.getLength());
        System.out.println("receive: " + string);
    }
}
