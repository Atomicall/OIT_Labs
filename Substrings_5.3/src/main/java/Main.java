// add M(stringLength), N(substring length) to program args
//usage [M] [N]
//example 12 2

import java.security.SecureRandom;

public class Main {
    private static int randomBinaryStringLengthArgument;
    private static int lastBitsSubstringLengthArgument;
    private static String randomBinaryString;
    private static String lastBitsSubstring;

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
                randomBinaryStringLengthArgument <=0 || lastBitsSubstringLengthArgument <= 0) {
            System.out.println("Substring length is bigger than string length OR " +
                    "one of the args <= 0 ");
            System.exit(-1);
        }
    }

    //private static int count
    private static String generateRandomBinaryString(int length) {
        StringBuilder builder = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomInt = random.nextInt(100);
            builder.append(randomInt > 50 ? 1 : 0);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        readArgs(args);
        System.out.printf("Random binary string length: %d, length of lastBits substring: %d\n",
                randomBinaryStringLengthArgument, lastBitsSubstringLengthArgument);
        randomBinaryString = generateRandomBinaryString(randomBinaryStringLengthArgument);
        lastBitsSubstring = randomBinaryString.substring(randomBinaryString.length() -
                lastBitsSubstringLengthArgument);
        System.out.println("Generated binary string: " + randomBinaryString);
        System.out.println("LastBits string: " + lastBitsSubstring);

    }
}
