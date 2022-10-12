import java.security.SecureRandom;

public class StringGenerator {
    private static final SecureRandom random = new SecureRandom();
    // first alphabet upper case symbol in utf-8 table - 65
    private static final int firstUpperCaseCharSymbol = 'A';
    // last alphabet upper case symbol in utf-8 table - 90
    private static final int lastUpperCaseCharSymbol = 'Z';
    // first alphabet lower case symbol in utf-8 table - 97
    private static final int firstLowerCaseCharSymbol = 'a';
    // last alphabet lower case symbol in utf-8 table - 122
    private static final int lastLowerCaseCharSymbol = 'z';

    public static String[] randomFixedLengthWords(int amountOfStrings, int stringLength) {
        String[] strings = new String[amountOfStrings];
        StringBuilder builder = new StringBuilder();
        for (int counter = 0; counter < amountOfStrings; counter++) {
            builder.setLength(0);
            for (int i = 0; i < stringLength; i++) {
                int offset;
                char charValue;
                // charValue is in range firstUpperCaseCharSymbol - lastLowerCaseCharSymbol except {90..97} symbols
                do {
                    offset = random.nextInt(lastLowerCaseCharSymbol - firstUpperCaseCharSymbol + 1);
                    charValue = (char) (firstUpperCaseCharSymbol + offset);
                } while (charValue > lastUpperCaseCharSymbol && charValue < firstLowerCaseCharSymbol);
                builder.append(charValue);
            }
            strings[counter] = builder.toString();
        }
        return strings;
    }
}
