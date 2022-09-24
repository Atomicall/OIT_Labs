import java.util.Collections;
import java.util.List;

public class FileSortingService {
    public static void sortFileNames(List<String> files, boolean ascSortingOrder){
        if (ascSortingOrder){
            files.sort(String::compareTo);
        }
        else {
            files.sort(Collections.reverseOrder(String::compareTo));
        }
    }
}
