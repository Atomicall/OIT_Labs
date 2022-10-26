import java.security.SecureRandom;

public class StringGenerator {
    private static final SecureRandom random = new SecureRandom();
    // first alphabet upper case symbol in utf-8 table - 65
    private static final int FIRST_UPPER_CASE_CHAR_SYMBOL = 'A';
    // last alphabet upper case symbol in utf-8 table - 90
    private static final int LAST_UPPER_CASE_CHAR_SYMBOL = 'Z';
    // first alphabet lower case symbol in utf-8 table - 97
    private static final int FIRST_LOWER_CASE_CHAR_SYMBOL = 'a';
    // last alphabet lower case symbol in utf-8 table - 122
    private static final int LAST_LOWER_CASE_CHAR_SYMBOL = 'z';

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
                    offset = random.nextInt(LAST_LOWER_CASE_CHAR_SYMBOL - FIRST_UPPER_CASE_CHAR_SYMBOL + 1);
                    charValue = (char) (FIRST_UPPER_CASE_CHAR_SYMBOL + offset);
                } while (charValue > LAST_UPPER_CASE_CHAR_SYMBOL && charValue < FIRST_LOWER_CASE_CHAR_SYMBOL);
                builder.append(charValue);
            }
            strings[counter] = builder.toString();
        }
        return strings;
    }
}
