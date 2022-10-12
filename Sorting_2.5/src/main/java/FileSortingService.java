import java.util.Collections;
import java.util.List;

public class FileSortingService {
    public static void sortFileNames(List<String> files, boolean ascSortingOrder) {
        if (ascSortingOrder) {
            files.sort(String.CASE_INSENSITIVE_ORDER);
        } else {
            files.sort(String.CASE_INSENSITIVE_ORDER.reversed());
        }
    }
}
