import java.util.Random;

public class GameUtil {
    public static int generateNumber(int start, int end) {
        Random r = new Random();
        return r.nextInt(end - start) + start;
    }
}
