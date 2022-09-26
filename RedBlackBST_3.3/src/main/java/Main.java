import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void randomizeArray(List<Integer> array) {
        SecureRandom rand = new SecureRandom();
        array.replaceAll((o) -> {
            return rand.nextInt(array.size());
        });
    }

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
    public static List<Integer> indexes= new ArrayList<>();

    public static void main(String[] args) {
        RedBlackBSTBookExample<Integer, String> bst = new RedBlackBSTWithCache<>();
        for (int i = 0; i < textStrings.size(); i++){
            indexes.add(i);
        }
        randomizeArray(indexes);
        for (int i = 0; i < indexes.size(); i++) {
            bst.put(indexes.get(i), textStrings.get(indexes.get(i)));
        }

        int randomIndex = new SecureRandom().nextInt(indexes.size());
        System.out.println("<-Searching then printing element by certain key 1st time->");
        if (bst.contains(indexes.get(randomIndex))){
            System.out.println(bst.get(indexes.get(randomIndex)));
        }

        System.out.println("<-Delete element by certain key->");
        bst.delete(indexes.get(randomIndex));

        System.out.println("<-Searching then printing element by certain key 2nd time->");
        if (bst.contains(indexes.get(3))){
            System.out.println(bst.get(indexes.get(randomIndex)));
        }
        System.out.println("<-Testing put cache->");
        System.out.println("<-Adding 1st time->");
        bst.put(indexes.size() + 2, "TestString");
        System.out.println("<-Adding 2nd time->");
        bst.put(indexes.size() + 2, "TestString");
        System.out.println("<-Searching then printing element->");
        if (bst.contains(indexes.size() + 2)){
            System.out.println(bst.get(indexes.size() + 2));
        }
    }
}
