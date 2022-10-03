// 3.2.14
// write non-recursive methods min(), max(), floor(), ceiling(), rank(), select()
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> textStrings = List.of(
            "Big",
            "Orange",
            "Fox",
            "Jumps",
            "Over",
            "The",
            "Big",
            "Lazy",
            "Dog",
            "Z",
            "A"
    );
    public static List<Integer> indexes = new ArrayList<>();

    public static void randomizeArray(List<Integer> array) {
        SecureRandom rand = new SecureRandom();
        array.replaceAll((o) -> {
            return rand.nextInt(array.size());
        });
    }

    public static void main(String[] args) {
        BSTNonRecursive<Integer, String> bstNR = new BSTNonRecursive<>();
        BSTBookExample<Integer, String> bst = new BSTBookExample<>();
        for (int i = 0; i < textStrings.size(); i++) {
            indexes.add(i);
        }
        randomizeArray(indexes);
        for (int i = 0; i < indexes.size(); i++) {
            bst.put(indexes.get(i), textStrings.get(i));
            bstNR.put(indexes.get(i), textStrings.get(indexes.get(i)));
        }
        System.out.printf("%-19s | NonRecursive\n", "Recursive");
        System.out.printf("%-19s | %s \n", "Size: " + bst.size(), bstNR.size());
        System.out.printf("%-19s | %s \n", "Min: " + bst.min(), bstNR.min());
        System.out.printf("%-19s | %s \n", "Max: " + bst.max(), bstNR.max());
        System.out.printf("%-19s | %s \n", "Floor: " + bst.floor(4), bstNR.floor(4));
        System.out.printf("%-19s | %s \n", "Floor(null): " + bst.floor(-1), bstNR.floor(-1));
        System.out.printf("%-19s | %s \n", "Ceiling: " + bst.ceiling(6), bstNR.ceiling(6));
        System.out.printf("%-19s | %s \n", "Ceiling(null): " + bst.ceiling(15), bstNR.ceiling(15));
        System.out.printf("%-19s | %s \n", "Select: " + bst.select(4), bstNR.select(4));
        System.out.printf("%s\n%-19s | %s \n", "Rank(for max value)", "(expect size-1): " + bst.rank(bst.max()), bstNR.rank(bstNR.max()));
        System.out.printf("%-19s | %s \n", "Rank(for min value)(expect 0): " + bst.rank(bst.min()), bstNR.rank(bstNR.min()));
        bst.printByLevel();
        System.out.println();
        bstNR.printByLevel();
    }
}