import java.util.Arrays;

public class Main {
    public static final int amountOfStrings = 50;
    public static final int length = 10;

    public static void main(String[] args) {
        String[] values = StringGenerator.randomFixedLengthWords(amountOfStrings, length);
        System.out.println("Generated strings: {");
        for (String s : values) {
            System.out.println(s + ",");
        }
        System.out.println("}");
    }
}
