import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileUtil {
    static final String USER_DATA_FILE_PATH = "users.txt";
    static final String SETTING_DATA_FILE_PATH = "settings.txt";
    static final String SCORE_DATA_FILE_PATH = "scores.txt";
    static final String DELIMITER = " ";

    public static List<User> loadUserDataFromFile() {
        List<User> users = new ArrayList<User>();
        try {
            File file = new File(USER_DATA_FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] dataArray = data.split(DELIMITER);
                users.add(new User(dataArray[0], UserType.getTypeFromString(dataArray[1])));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading from: " + USER_DATA_FILE_PATH);
            e.printStackTrace();
        }
        return users;
    }

    public static int[] loadSettingDataFromFile() {
        int[] dataRange = new int[2];
        try {
            File file = new File(SETTING_DATA_FILE_PATH);
            Scanner scanner = new Scanner(file);
            String data = scanner.nextLine();
            String[] dataArray = data.split(DELIMITER);
            dataRange[0] = Integer.parseInt(dataArray[0]);
            dataRange[1] = Integer.parseInt(dataArray[1]);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading from: " + SETTING_DATA_FILE_PATH);
            e.printStackTrace();
        }
        return dataRange;
    }

    public static List<Score> loadScoreDataFromFile() {
        Map<String, User> playerMap = loadUserDataFromFile().stream().filter(u -> u.getUserType().equals(UserType.PLAYER)).collect(Collectors.toMap(User::getUserId, u->u));
        List<Score> scores = new ArrayList<Score>();
        try {
            File file = new File(SCORE_DATA_FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] dataArray = data.split(DELIMITER);
                scores.add(new Score(playerMap.get(dataArray[0]), Integer.parseInt(dataArray[1])));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading from: " + SCORE_DATA_FILE_PATH);
            e.printStackTrace();
        }
        return scores;
    }

    public static void updateUserData(Collection<User> users) {
        try {
            File file = new File(USER_DATA_FILE_PATH);
            FileWriter fileWriter = new FileWriter(file, false);
            for(User user : users) {
                fileWriter.write(user.getUserId() + DELIMITER + user.getUserType().getType() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateScoreData(Collection<Score> scores) {
        try {
            File file = new File(SCORE_DATA_FILE_PATH);
            FileWriter fileWriter = new FileWriter(file, false);
            for(Score score : scores) {
                fileWriter.write(score.getUser().getUserId() + DELIMITER + score.getScore() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateSettingData(int[] s) {
        try {
            File file = new File(SETTING_DATA_FILE_PATH);
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(s[0] + DELIMITER + s[1] + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
