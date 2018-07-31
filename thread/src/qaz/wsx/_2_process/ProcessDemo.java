package qaz.wsx._2_process;

import java.io.IOException;

/**
 * @author lch
 * @since 2018年07月28日 21:32:58
 */
public class ProcessDemo {

    public static void main(String[] args) throws IOException {
        // 1. Runtime.exec
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("notepad");
        // 2. ProcessBuilder.start
        ProcessBuilder builder = new ProcessBuilder("notepad");
        builder.start();
    }
}
