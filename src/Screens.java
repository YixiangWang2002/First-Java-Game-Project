import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Utility functions for print menus.
 */
public class Screens {

    /**
     * Print initial/first level menu.
     */
    public static void getInitialScreen()
    {
        System.out.println("**************Please Select your choice******************");
        System.out.println("1.System Admin");
        System.out.println("2.Game Player");
        System.out.println("3.Quit System");
        System.out.print("Choice->");
    }

    /**
     * Prompt for user id.
     */
    public static void getUserId()
    {
        System.out.print("please enter your userId to continue:");
    }

    /**
     * Print System Admin menu
     */
    public static void getSystemAdminScreen()
    {
        System.out.println("*****************System Admin Screen*********************");
        System.out.println("**************Please Select your choice******************");
        System.out.println("1.Show current number range");
        System.out.println("2.Show user list");
        System.out.println("3.Change number range");
        System.out.println("4.Go back to previous screen");
        System.out.println("5.Quit System");
        System.out.print("Choice->");
    }

    /**
     * Print first level Game Play menu.
     */
    public static void getPriorGamePlayScreen1()
    {
        getGamePlayScreen();
        System.out.println("**************Please Select your choice******************");
        System.out.println("1.Select a difficulty");
        System.out.println("2.Go back to previous screen");
        System.out.println("3.Quit System");
        System.out.print("Choice->");
    }

    /**
     * Print second level Game Play menu.
     */
    public static void getPriorGamePlayScreen2()
    {
        getGamePlayScreen();
        System.out.println("********Please Select difficulty before game start*******");
        System.out.println("1.EASY - Guess 8 rounds");
        System.out.println("2.MEDIUM - Guess 5 rounds");
        System.out.println("3.EXPERT - Guess 3 rounds");
        System.out.println("4.Go back to previous screen");
        System.out.println("5.Quit System");
        System.out.print("Choice->");
    }

    /**
     * Print common header for Game Play menu.
     */
    public static void getGamePlayScreen()
    {
        System.out.println("********************Game Play Screen*********************");
    }

    /**
     * Print user info data.
     */
    public static void printUserList(Collection<User> users) {
        System.out.println("************************User List************************");
        System.out.println("********User Id**************************User Type*******");
        for(User user : users) {
            System.out.println("        " + user.getUserId() + "                                " + user.getUserType().getType());
        }
        System.out.println("*********************************************************");
    }

    /**
     * Sort and Print scores.
     */
    public static void printGameScores(Collection<Score> scores) {
        List<Score> tmp = new ArrayList<Score>(scores);
        Collections.sort(tmp);
        System.out.println("************************Rankings*************************");
        System.out.println("********User Id**************************Score***********");
        Score currentScore = null;
        for (int i = 0; i < tmp.size(); i++ ) {
            if (!tmp.get(i).equals(currentScore)) { // never print duplicate data
                System.out.println("        " + tmp.get(i).getUser().getUserId() + "                                " + tmp.get(i).getScore());
                currentScore = tmp.get(i);
            }
        }
        System.out.println("*********************************************************");
    }
}
