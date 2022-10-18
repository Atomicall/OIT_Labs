// 5.2.17
// аргументом ожидается путь к текстовому файлу, содержащему словарь

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final StringSetBookExample stringSet = new StringSetBookExample();
    private static final Scanner scanner = new Scanner(System.in);
    private static String filePath;

    private static void readArgs(String[] args) {
        if (args.length < 1) {
            System.out.println("There is < 1 args, exit");
            System.exit(-1);
        }
        filePath = args[0];
    }

    private static void readWordsFromDictionaryFile() {
        File dictionaryFile = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
            while (br.ready()) {
                try {
                    String s = br.readLine();
                    Arrays.stream(s.split("\\W")).forEach(stringSet::add);
                } catch (IOException e) {
                    System.out.println("Failed to read string from file:");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to open dictionary file:");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static void spellChecker() {
        System.out.println("}\nWords to search?");
        System.out.print(">");
        String userInputString = scanner.nextLine();
        String[] userInputWords = userInputString.split("\\W");
        if (userInputWords.length == 0) {
            System.out.println("There is no user words");
            System.exit(0);
        }
        System.out.println("Missing words:");
        for (String s : userInputWords) {
            if (!stringSet.contains(s)) {
                stringSet.add(s);
                System.out.println(s);
            }
        }

    }

    public static void main(String[] args) {
        readArgs(args);
        readWordsFromDictionaryFile();
        if (stringSet.isEmpty()) {
            System.out.println("Dictionary is empty!");
            System.exit(0);
        }
        System.out.println("Words in dictionary:{");
        for (String s : stringSet.keys()) {
            System.out.println(s);
        }
        spellChecker();
    }
}
