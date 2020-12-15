import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainProgram {
    public static void main(String[] args) {
        while (true) {
            List<User> users = FileUtil.loadUserDataFromFile();
            Map<String, Score> scoreMap = FileUtil.loadScoreDataFromFile().stream().collect(Collectors.toMap(score -> score.getUser().getUserId(), score -> score));
            Scanner outerScanner = new Scanner(System.in);
            Screens.getInitialScreen();
            int outerChoice = outerScanner.nextInt();
            outerScanner.nextLine();
            if (outerChoice == 1) {
                while (true) {
                    Scanner c1Scanner = new Scanner(System.in);
                    Screens.getUserId();
                    String userId = c1Scanner.nextLine();
                    if (users.contains(new User(userId, UserType.ADMIN))) {
                        Scanner adminScanner = new Scanner(System.in);
                        Screens.getSystemAdminScreen();
                        int adminChoice = adminScanner.nextInt();
                        adminScanner.nextLine();
                        if (adminChoice == 1) {
                            int[] range = FileUtil.loadSettingDataFromFile();
                            System.out.println("Current Setting： " + range[0] + " to " + range[1]);
                        } else if (adminChoice == 2) {
                            Screens.printUserList(users);
                        } else if (adminChoice == 3) {
                            int[] newRange = new int[2];
                            System.out.println("Please enter [from]: ");
                            newRange[0] = adminScanner.nextInt();
                            adminScanner.nextLine();
                            System.out.println("Please enter [to]: ");
                            newRange[1] = adminScanner.nextInt();
                            adminScanner.nextLine();
                            FileUtil.updateSettingData(newRange);
                            System.out.println("Updated Setting： " + newRange[0] + " to " + newRange[1]);
                        } else if (adminChoice == 4) {
                            break;
                        } else if (adminChoice == 5) {
                            System.exit(0);
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    } else {
                        System.out.println("Your are not a system admin.");
                    }
                }

            } else if (outerChoice == 2) {
                while (true) {
                    Scanner c2Scanner = new Scanner(System.in);
                    Screens.printGameScores(scoreMap.values());
                    Screens.getPriorGamePlayScreen1();
                    int c2Choice = c2Scanner.nextInt();
                    c2Scanner.nextLine();
                    if (c2Choice == 1) {
                        Screens.printGameScores(scoreMap.values());
                        Screens.getPriorGamePlayScreen2();
                        int difficulty = c2Scanner.nextInt();
                        c2Scanner.nextLine();
                        if (difficulty >= 1 && difficulty <= 3) {
                            int maxTryTimes = 8;
                            if (difficulty == 2) {
                                maxTryTimes = 5;
                            }
                            if (difficulty == 3) {
                                maxTryTimes = 3;
                            }

                            Screens.getGamePlayScreen();
                            int counter = 0;
                            int[] range = FileUtil.loadSettingDataFromFile();
                            int start = range[0];
                            int end = range[1];
                            int toBeGuessed = GameUtil.generateNumber(start, end);
                            int userInput = 0;
                            System.out.println("Number is between " + start + " and " + end);
                            while (counter < maxTryTimes ){
                                System.out.print("Enter your guess: ");
                                userInput = c2Scanner.nextInt();
                                c2Scanner.nextLine();
                                if (userInput == toBeGuessed) {
                                    System.out.println("You got it !");
                                    System.out.println("Do you want to save your score?[Y/N]: ");
                                    String saveScore = c2Scanner.nextLine();
                                    if ("Yy".contains(saveScore)) {
                                        Screens.getUserId();
                                        String userId = c2Scanner.nextLine();
                                        if (scoreMap.containsKey(userId)) {
                                            int newScore = scoreMap.get(userId).getScore() + difficulty;
                                            scoreMap.get(userId).setScore(newScore);
                                            System.out.print("Welcome back ");
                                        } else {
                                            User newUser = new User(userId, UserType.PLAYER);
                                            users.add(newUser);
                                            Score newScore = new Score(newUser, difficulty);
                                            scoreMap.put(userId, newScore);
                                            System.out.print("Welcome onboard ");
                                        }
                                        FileUtil.updateUserData(users);
                                        FileUtil.updateScoreData(scoreMap.values());
                                        System.out.println(userId + ", your score is saved!");
                                        break;
                                    } else {
                                        break;
                                    }
                                }
                                if (toBeGuessed > userInput) {
                                    System.out.println("Target number is greater than " + userInput);
                                }
                                if (toBeGuessed < userInput) {
                                    System.out.println("Target number is less than " + userInput);
                                }
                                counter++;
                            }
                            System.out.println("You need some luck!");

                        }else if (difficulty == 4) {
                            break;
                        } else if (difficulty == 5) {
                            System.exit(0);
                        }

                    } else if (c2Choice == 2) {
                        break;
                    } else if (c2Choice == 3) {
                        System.exit(0);
                    } else {
                        System.out.println("Invalid choice!");
                    }
                }
            } else if (outerChoice == 3) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice!");
            }
        }


    }
}
