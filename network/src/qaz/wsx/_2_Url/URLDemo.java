package qaz.wsx._2_Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author lch
 * @since 2018年08月02日 14:24:25
 */
public class URLDemo {
    public static void main(String[] args) throws IOException {
        String suffix = ".jpg";

        //创建URL对象(协议,地址,端口,文件),注意文件前有/
        URL url = new URL("http", "gss0.baidu.com", 80,
                "/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D600%2C800/sign=761814cd1c3853438c9a8f27a3239c46" +
                        "/4d086e061d950a7b413aa96506d162d9f2d3c900" + suffix);

        //创建URLConnection对象,打开URL连接对象
        URLConnection connection = url.openConnection();

        //InputStream getInputStream 返回从此打开连接读取的输入流
        //网络中数据--输入-->程序中
        InputStream inputStream = connection.getInputStream();

//        Scanner sc = new Scanner(inputStream);
//        while(sc.hasNextLine()){
//            System.out.println(sc.nextLine());
//        }
//        sc.close();

        Files.copy(inputStream, Paths.get("download" + suffix));

    }
}
