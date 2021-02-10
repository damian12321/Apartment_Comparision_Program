import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file1 = new File("oldValues.txt");//File with old values
        File file2 = new File("newValues.txt");//File with new values
        File file3 = new File("differences.txt");//Destination file with compared apartments
//        ApartmentComparator apartmentComparator = new ApartmentComparator(file1, file2);
        ApartmentComparator apartmentComparator = new ApartmentComparator(file1);
        apartmentComparator.compare(file3);
    }
}