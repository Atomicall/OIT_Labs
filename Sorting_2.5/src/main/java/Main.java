import java.io.File;
import java.util.ArrayList;
import java.util.List;
// 2.5.28 Сортировка файлов по именам
// add [path] to Program arguments
//using [path] [-a || -d]

public class Main {
    private static final List<String> directories = new ArrayList<>();
    private static final List<String> files = new ArrayList<>();
    private static boolean ascendingSort;
    private static String path;

    private static void readArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("there is < 2 args");
            System.exit(-1);
        }
        path = args[0];
        String sortingOrder = args[1];
        switch (sortingOrder) {
            case "-a" -> ascendingSort = true;
            case "-d" -> ascendingSort = false;
            default -> {
                System.out.println("Unknown sorting order arg, using default: asc");
                ascendingSort = true;
            }
        }
    }

    private static void getContentFromDirectory() {
        File folder = new File(path);
        if (!folder.exists()) {
            System.out.println("Failed to open path");
            return;
        }
        File[] folderContent = folder.listFiles();
        if (folderContent == null || folderContent.length == 0) {
            System.out.println("Target directory is empty OR not a directory");
            return;
        }
        for (File f : folderContent) {
            if (f.isDirectory()) {
                directories.add(f.getName());

            } else {
                files.add(f.getName());
            }
        }
    }

    public static void main(String[] args) {
        readArgs(args);
        System.out.println("Path is \"" + path + "\" ");
        getContentFromDirectory();
        FileSortingService.sortFileNames(directories, ascendingSort);
        FileSortingService.sortFileNames(files, ascendingSort);
        System.out.println("Sorted:\nDirectories{");
        directories.forEach(System.out::println);
        System.out.println("} \nFiles {");
        files.forEach(System.out::println);
        System.out.println("}");
    }
}
