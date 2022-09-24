package org.example;
// 3.2.14
// write non-recursive methods min(), max(), floor(), ceiling(), rank(), select()

import java.util.*;


public class Main {
    public static void randomizeArray(List<Integer> array) {
        Random rand = new Random();
        for (int i = 0; i < array.size(); i++) {
            array.set(i, rand.nextInt(array.size()));
        }
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
    public static List<Integer> indexes= new ArrayList<Integer>();

    public static void main(String[] args) {

        BSTNonRecursive<Integer, String> bstNR = new BSTNonRecursive<Integer, String>();
        BSTBookExample<Integer, String> bst = new BSTBookExample<Integer, String>();

//
       /* List <String> copy = new ArrayList<String>(textStrings);
        copy.sort(String::compareTo);
        System.out.println("Sot:");
        copy.forEach(System.out::println);*/
//
        for (int i = 0; i < textStrings.size(); i++){
            indexes.add(i);
        }
        randomizeArray(indexes);

        for (int i = 0; i < indexes.size(); i++) {
            bst.put(indexes.get(i), textStrings.get(i));
            bstNR.put(indexes.get(i), textStrings.get(i));
        }

        /*System.out.println("Key | value");
        for (Integer i: bst.keys()
             ) {
            System.out.println(i + " " + bst.get(i));
        }*/
        System.out.printf("%-19s | NonRecursive\n", "Recursive");
        System.out.printf("%-19s | %s \n", "Min: " + bst.min(), bstNR.min());
        System.out.printf("%-19s | %s \n", "Max: " + bst.max(), bstNR.max());
        System.out.printf("%-19s | %s \n", "Floor: " + bst.floor(4), bstNR.floor(4));
        System.out.printf("%-19s | %s \n", "Floor(null): " + bst.floor(-1), bstNR.floor(-1));
        System.out.printf("%-19s | %s \n", "Ceiling: " + bst.ceiling(6), bstNR.ceiling(6));
        System.out.printf("%-19s | %s \n", "Ceiling(null): " + bst.ceiling(15), bstNR.ceiling(15));
        System.out.printf("%-19s | %s \n", "Select: " + bst.select(4), bstNR.select(4));
        System.out.printf("%-19s | %s \n", "Rank: " + bst.rank(bst.select(2)), bstNR.rank(bstNR.select(2)));
        bst.printByLevel();
        System.out.println();
        bstNR.printByLevel();
    }
}