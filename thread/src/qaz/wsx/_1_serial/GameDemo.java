package qaz.wsx._1_serial;

/**
 * 边运行游戏,边播放歌的功能
 * @author lch
 * @since 2018年07月28日 21:21:19
 */
public class GameDemo {

    public static void main(String[] args) {
        System.out.println("begin ......");

        playGame();
        playMusic();

        System.out.println("end ......");
    }

    private static void playMusic() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Play Music " + i);
        }
    }

    private static void playGame() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Play Game " + i);
        }
    }
}
