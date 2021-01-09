import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Main program for admin functions and game play.
 * Demo video link : https://github.com/YixiangWang2002/First-Java-Game-Project/blob/master/java%20game%20demo.mp4
 */
public class MainProgram {
    public static void main(String[] args) {
        while (true) {
            List<User> users = FileUtil.loadUserDataFromFile(); // load user data from file
            Map<String, Score> scoreMap = FileUtil.loadScoreDataFromFile().stream().collect(Collectors.toMap(score -> score.getUser().getUserId(), score -> score)); // load score data into a map <User Id, Score>
            Scanner outerScanner = new Scanner(System.in); // create an instance of java.util.Scanner
            Screens.getInitialScreen(); // print first level screen
            int outerChoice = outerScanner.nextInt(); // capture choice options from user
            outerScanner.nextLine(); // escape the dummy characters
            if (outerChoice == 1) { // Entering System Admin screen
                while (true) {
                    Scanner c1Scanner = new Scanner(System.in); // create an instance of java.util.Scanner
                    Screens.getUserId(); // prompt for user Id
                    String userId = c1Scanner.nextLine(); // capture userId input from user
                    if (users.contains(new User(userId, UserType.ADMIN))) { // check user is a valid system admin or not
                        Scanner adminScanner = new Scanner(System.in); // create an instance of java.util.Scanner
                        Screens.getSystemAdminScreen(); // print system admin screen
                        int adminChoice = adminScanner.nextInt(); // capture choice options from user
                        adminScanner.nextLine(); // escape the dummy characters
                        if (adminChoice == 1) {  // Enter setting view screen
                            int[] range = FileUtil.loadSettingDataFromFile(); // load setting data from file
                            System.out.println("Current Setting： " + range[0] + " to " + range[1]); // print number range
                        } else if (adminChoice == 2) { // Enter user data view screen
                            Screens.printUserList(users); // print user data
                        } else if (adminChoice == 3) { // Enter user data edit screen
                            int[] newRange = new int[2];
                            System.out.println("Please enter [from]: "); // prompt for "from"
                            newRange[0] = adminScanner.nextInt();
                            adminScanner.nextLine();
                            System.out.println("Please enter [to]: "); // prompt for "to"
                            newRange[1] = adminScanner.nextInt();
                            adminScanner.nextLine();
                            FileUtil.updateSettingData(newRange); // save setting data to file
                            System.out.println("Updated Setting： " + newRange[0] + " to " + newRange[1]);
                        } else if (adminChoice == 4) { // Go back to previous screen
                            break;
                        } else if (adminChoice == 5) { // exit program
                            System.exit(0);
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    } else {
                        System.out.println("Your are not a system admin.");
                    }
                }

            } else if (outerChoice == 2) { // Enter Game Play
                while (true) {
                    Scanner c2Scanner = new Scanner(System.in); // create an instance of java.util.Scanner
                    Screens.printGameScores(scoreMap.values()); // print sorted score data
                    Screens.getPriorGamePlayScreen1();          // print Game Play menu
                    int c2Choice = c2Scanner.nextInt(); // capture choice options from user
                    c2Scanner.nextLine(); // escape the dummy characters
                    if (c2Choice == 1) { // Enter first level game play screen
                        Screens.printGameScores(scoreMap.values()); // print sorted score data
                        Screens.getPriorGamePlayScreen2(); // Print first level game play menu
                        int difficulty = c2Scanner.nextInt(); // capture choice options from user
                        c2Scanner.nextLine(); // escape the dummy characters
                        if (difficulty >= 1 && difficulty <= 3) { // validate input difficulty
                            int maxTryTimes = 8;  // 1.EASY - Guess 8 rounds
                            if (difficulty == 2) {
                                maxTryTimes = 5; // 2.MEDIUM - Guess 5 rounds
                            }
                            if (difficulty == 3) {
                                maxTryTimes = 3; // 3.EXPERT - Guess 3 rounds
                            }

                            Screens.getGamePlayScreen(); // Print common header for Game Play menu.
                            int counter = 0; // counter for try times
                            int[] range = FileUtil.loadSettingDataFromFile(); // loading data range from setting file
                            int start = range[0];
                            int end = range[1];
                            int toBeGuessed = GameUtil.generateNumber(start, end); // get a random number with the range
                            int userInput = 0;
                            System.out.println("Number is between " + start + " and " + end);
                            while (counter < maxTryTimes ){
                                System.out.print("Enter your guess: "); // prompt for user input
                                userInput = c2Scanner.nextInt(); // capture guessed number from user
                                c2Scanner.nextLine(); // escape the dummy characters
                                if (userInput == toBeGuessed) { // check user got the number or not
                                    System.out.println("You got it !");
                                    System.out.println("Do you want to save your score?[Y/N]: "); // save score option
                                    String saveScore = c2Scanner.nextLine();
                                    if ("Yy".contains(saveScore)) {
                                        Screens.getUserId(); // let user input user id
                                        String userId = c2Scanner.nextLine();
                                        if (scoreMap.containsKey(userId)) { // when user exist in file
                                            int newScore = scoreMap.get(userId).getScore() + difficulty;
                                            scoreMap.get(userId).setScore(newScore);
                                            System.out.print("Welcome back ");
                                        } else { // when user is a new user
                                            User newUser = new User(userId, UserType.PLAYER);
                                            users.add(newUser);
                                            Score newScore = new Score(newUser, difficulty);
                                            scoreMap.put(userId, newScore);
                                            System.out.print("Welcome onboard ");
                                        }
                                        FileUtil.updateUserData(users); // update user data file
                                        FileUtil.updateScoreData(scoreMap.values()); // update score data file
                                        System.out.println(userId + ", your score is saved!");
                                        break;
                                    } else {
                                        break;
                                    }
                                }
                                if (toBeGuessed > userInput) {
                                    System.out.println("Target number is greater than " + userInput); // continue the game if user input < target number
                                }
                                if (toBeGuessed < userInput) {
                                    System.out.println("Target number is less than " + userInput); // continue the game if user input > target number
                                }
                                counter++; // increase player try times
                            }
                            System.out.println("You need some luck!"); // end game

                        }else if (difficulty == 4) { // Go back to previous screen
                            break;
                        } else if (difficulty == 5) { // Exit program
                            System.exit(0);
                        }

                    } else if (c2Choice == 2) { // Go back to previous screen
                        break;
                    } else if (c2Choice == 3) { // Exit program
                        System.exit(0);
                    } else {
                        System.out.println("Invalid choice!");
                    }
                }
            } else if (outerChoice == 3) { // Exit program
                System.exit(0);
            } else {
                System.out.println("Invalid choice!");
            }
        }


    }
}

// here's link : https://github.com/YixiangWang2002/First-Java-Game-Project/blob/master/java%20game%20demo.mp4