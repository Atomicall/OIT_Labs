import java.io.File;
import java.util.ArrayList;
import java.util.List;
// 2.5.28 Сортировка файлов по именам
// add [path] to Program arguments
//using [path] [-a || -d]

public class Main {
    private static final List<String> directoryNames = new ArrayList<>();
    private static final List<String> fileNames = new ArrayList<>();
    private static boolean isSortingArgumentAscending;
    private static String pathArgument;

    private static void readArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("there is < 2 args");
            System.exit(-1);
        }
        pathArgument = args[0];
        String sortingOrder = args[1];
        switch (sortingOrder) {
            case "-a" -> isSortingArgumentAscending = true;
            case "-d" -> isSortingArgumentAscending = false;
            default -> {
                System.out.println("Unknown sorting order arg, using default: asc");
                isSortingArgumentAscending = true;
            }
        }
    }

    private static void getContentFromDirectory() {
        File folder = new File(pathArgument);
        if (!folder.exists()) {
            System.out.println("Failed to open path");
            System.exit(-1);
            ;
        }
        File[] folderContent = folder.listFiles();
        if (folderContent == null || folderContent.length == 0) {
            System.out.println("Target directory is empty OR not a directory");
            System.exit(-1);
            ;
        }
        for (File f : folderContent) {
            if (f.isDirectory()) {
                directoryNames.add(f.getName());

            } else {
                fileNames.add(f.getName());
            }
        }
    }

    public static void main(String[] args) {
        readArgs(args);
        System.out.println("Path is \"" + pathArgument + "\" ");
        getContentFromDirectory();
        FileSortingService.sortFileNames(directoryNames, isSortingArgumentAscending);
        FileSortingService.sortFileNames(fileNames, isSortingArgumentAscending);
        System.out.println("Sorted:\nDirectories{");
        directoryNames.forEach(System.out::println);
        System.out.println("} \nFiles {");
        fileNames.forEach(System.out::println);
        System.out.println("}");
    }
}
