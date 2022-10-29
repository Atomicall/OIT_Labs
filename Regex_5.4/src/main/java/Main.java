import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PHONE_NUMBER_REGEXP = "^\\([0-9]{3}\\)\\ [0-9]{3}-[0-9]{4}$";
    private static final String SOCIAL_NUMBER_REGEXP = "^[0-9]{3}-[0-9]{2}-[0-9]{4}$";
    private static final String DATE_REGEXP = "^(January|February|March|April|May|June|July|August|September|October|November|December)\\ (0[1-9]|(1|2)[0-9]|3(0|1)),\\ [0-9]{4}$";
    private static final String IP_ADDRESS_REGEXP = "^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])(\\.(?!$)|$)){4}$";
    private static final String CAR_NUMBER_REGEXP = "^[0-9]{4}[A-ZА-Я5]{2}$";

    private static void printMenu() {
        System.out.println("Check input string with pattern:");
        System.out.println("1.Phone number: \"(609) 555-1234\"");
        System.out.println("2.Social insurance number: \"123-45-6798\"");
        System.out.println("3.Date: \"December 31, 2012\"");
        System.out.println("4.Ip address: \"196.26.155.241\"");
        System.out.println("5.Car number: \"9812AX\"");
        System.out.println("0.Exit");
        System.out.print(">");
    }

    private static void checkRegex(String regex) {
        System.out.println("0.Return\nEnter line:");
        while (true) {
            System.out.print(">");
            String inputString = scanner.nextLine();
            if (inputString.equals("0")) break;
            System.out.println("Is valid string? " + (checkMatch(inputString.trim(), regex) ? "TRUE" : "FALSE"));
        }
    }

    private static boolean checkMatch(String inputString, String regex) {
        if (inputString.isEmpty()) {
            System.out.println("Input string is empty");
            return false;
        }
        boolean hasMatch = false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find()) {
            hasMatch = true;
            System.out.printf("Found match: %s\n", matcher.group());
        }
        return hasMatch;
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String inputString = scanner.nextLine();
            switch (inputString) {
                case "1" -> {
                    checkRegex(PHONE_NUMBER_REGEXP);
                }
                case "2" -> {
                    checkRegex(SOCIAL_NUMBER_REGEXP);
                }
                case "3" -> {
                    checkRegex(DATE_REGEXP);
                }
                case "4" -> {
                    checkRegex(IP_ADDRESS_REGEXP);
                }
                case "5" -> {
                    checkRegex(CAR_NUMBER_REGEXP);
                }
                case "0" -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Wrong input");
                }
            }
        }
    }
}
