import java.io.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file1 = new File("D:\\ApartmentComparator\\src\\main\\resources\\oldValues.txt");//File with old values
        File file2 = new File("D:\\ApartmentComparator\\src\\main\\resources\\newValues.txt");//File with new values
        File file3 = new File("D:\\ApartmentComparator\\src\\main\\resources\\differences.txt");//Destination file with compared apartments
        File file4 = new File("D:\\ApartmentComparator\\src\\main\\resources\\differencesOnline.txt");//Destination file with compared apartments
        ApartmentComparator apartmentComparator = new ApartmentComparator(file1, file2);
        apartmentComparator.compare(file3);
        ApartmentComparator apartmentComparator1 = new ApartmentComparator(file1);
        apartmentComparator1.compare(file4);
    }
}