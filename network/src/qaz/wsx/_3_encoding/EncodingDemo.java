package qaz.wsx._3_encoding;

import com.sun.deploy.net.URLEncoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * URLEncoder和URLDecoder用于完成普通字符串和application/x-www-form-urlencoded MIME字符串之间的相互转换.
 *
 * 编码和解码:
 *   在Web的浏览器中,不同的浏览器的编码和解码规则是不一样的.
 *    对于W3C浏览器:遵循W3C组织规范的浏览器,(非IE).
 *                编码:byte[] data = “任小龙”.getByte(String charsetName);
 *                解码:String  str = new String(data, String charsetName);
 *    对于IE浏览器:
 *               编码使用的application/x-www-form-urlencoded MIME机制.
 * @author lch
 * @since 2018年08月02日 14:42:04
 */
public class EncodingDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //获取平台默认的字符集:GBK
        System.out.println(System.getProperty("file.encoding"));

        String msg = "中文english 123";

        //使用UTF-8编码
        String msg1 = URLEncoder.encode(msg,"UTF-8");
        System.out.println(msg1);


        //使用UTF-8解码
        String msg2 = URLDecoder.decode(msg1,"UTF-8");
        System.out.println(msg2);
    }
}
