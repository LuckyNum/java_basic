package qaz.wsx._5_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author lch
 * @since 2018年08月02日 15:15:26
 */
public class FeiQDemo {
    public static void main(String[] args){
        for (int i = 0; i < 20; i++) {
            //1.创建DatagramSocket发送端
            try(DatagramSocket socket = new DatagramSocket();) {
                //2.创建数据包
                byte[] message = getFeiQMessage("张大猪张大猪张大猪张大猪张大猪张大猪张大猪").getBytes();
                DatagramPacket packet = new DatagramPacket(message, message.length, InetAddress.getByName
                        ("192.168.62.180"), 2425);
                //3. 发送
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *创建一个方法来构建FeiQ的格式
     */
    public static String getFeiQMessage(String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("1.0:");
        sb.append(System.currentTimeMillis()).append(":");
        sb.append("乔布斯:");
        sb.append("天堂2.0:");
        sb.append("32:");
        sb.append(content);
        return sb.toString();
    }
}
