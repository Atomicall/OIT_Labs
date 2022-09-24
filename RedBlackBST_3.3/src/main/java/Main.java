import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        RedBlackBSTBookExample<String, Integer> st = new RedBlackBSTBookExample<>();
        String str= "";
        for (int i = 0; !str.equals("zz"); i++) {
            str = s.nextLine();
            st.put(str, i);
        }
        for (String w  : st.keys())
            System.out.println(w + " " + st.get(w));
    }
}
