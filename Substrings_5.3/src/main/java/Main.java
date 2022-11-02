// add M(stringLength), N(substring length) to program args
//usage [M] [N]
//example 12 2

import java.security.SecureRandom;

public class Main {
    private static int randomBinaryStringLengthArgument;
    private static int lastBitsSubstringLengthArgument;

    private static void readArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("args length < 2");
            System.exit(-1);
        }
        try {
            randomBinaryStringLengthArgument = Integer.parseInt(args[0]);
            lastBitsSubstringLengthArgument = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Exception in parsing input Int params!");
            System.exit(-1);
        }
        if (lastBitsSubstringLengthArgument > randomBinaryStringLengthArgument ||
                randomBinaryStringLengthArgument <= 0 || lastBitsSubstringLengthArgument <= 0) {
            System.out.println("Substring length is bigger than string length OR " + "one of the args <= 0 ");
            System.exit(-1);
        }
    }

    private static String generateRandomBinaryString(int length) {
        StringBuilder builder = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomInt = random.nextInt(100);
            builder.append(randomInt > 50 ? 1 : 0);
        }
        return builder.toString();
    }
    /*
    The Rabin-Karp algorithm is better when searching for a large text that is finding multiple pattern matches,
like detecting plagiarism.
    And Boyer-Moore is better when the pattern is relatively large with a moderately sized alphabet and with
a large vocabulary. And it does not work well with binary strings or very short patterns.
    Meanwhile, KMP is good for searching inside a smaller alphabet, like in bioinformatics or
searching in binary strings. And it does not run fast if the alphabet increases.
https://stackoverflow.com/questions/56418773/when-is-rabin-karp-more-effective-than-kmp-or-boyer-moore
    */

    /*
    Boyer Moore Algorithm : It works particularly well for long search patterns.
    In particular, it can be sub-linear, as we do not need to read every single character of our string.
    KMP Algorithm : It can work quite well, if your alphabet is small (e.g. DNA bases),
    as we get a higher chance that our search patterns contain reusable sub-patterns.
    https://iq.opengenus.org/kmp-vs-boyer-moore-algorithm/

    Это значит, что для применения алгоитма БМ нет смысла
    Для построк длиной меньше половины строки, используем KMP
    Для подстрок длиной больше или равно, используем RK
     */
    private static int countOccurrences(String string, String substring) {
        int occurrences;
        if (substring.length() < string.length() / 2) {
            KMP kmp = new KMP(substring);
            occurrences = kmp.count(string);
        } else {
            RabinKarp rabinKarp = new RabinKarp(substring, false);
            occurrences = rabinKarp.count(string);
        }
        return occurrences - 1;
    }

    public static void main(String[] args) {
        readArgs(args);
        System.out.printf("Random binary string length: %d, length of lastBits substring: %d\n",
                randomBinaryStringLengthArgument, lastBitsSubstringLengthArgument);
        String randomBinaryString = generateRandomBinaryString(randomBinaryStringLengthArgument);
        String lastBitsSubstring = randomBinaryString.substring(randomBinaryString.length() -
                lastBitsSubstringLengthArgument);
        System.out.println("Generated binary string: " + randomBinaryString);
        System.out.println("LastBits string: " + lastBitsSubstring);
        System.out.println("Amount of occurrences: " + countOccurrences(randomBinaryString, lastBitsSubstring));
    }
}
