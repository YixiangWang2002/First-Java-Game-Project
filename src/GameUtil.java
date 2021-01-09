import java.util.Random;

public class GameUtil {
    /**
     * Generates a number with in range [start, end]
     * @param start
     * @param end
     * @return
     */
    public static int generateNumber(int start, int end) {
        Random r = new Random();
        return r.nextInt(end - start) + start;
    }
}
