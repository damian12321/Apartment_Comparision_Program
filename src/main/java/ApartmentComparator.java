import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.*;

public class ApartmentComparator {
    private File firstFileName;
    private File secondFileName;
    private boolean comparisionCompleted;
    private List<Apartment> newApartmentsList;
    private int mode = 0;//0-comparing two files,1-comparing first file to online values from table

    public ApartmentComparator(File firstFileName, File secondFileName, File destinationFile) { //Comparing two files with values
        this.firstFileName = firstFileName;
        this.secondFileName = secondFileName;
        this.mode = 0;
        this.comparisionCompleted = false;
        compare(destinationFile);
    }

    public ApartmentComparator(File firstFileName, File destinationFile) //Compare values from disk to online values
    {
        this.firstFileName = firstFileName;
        this.mode = 1;
        this.comparisionCompleted = false;
        getDataFromUrl();
        compare(destinationFile);
    }

    public void compare(File destinationFile) {
        List<String> list1 = getAllLinesFromFile(firstFileName);
        List<Apartment> oldApartmentsList = getApartmentsFromList(list1);
        List<String> list2;
        if (mode == 0) {
            list2 = getAllLinesFromFile(secondFileName);
        } else {
            list2 = getDataFromUrl();
        }
        newApartmentsList = getApartmentsFromList(list2);
        int maxApartmentNumber = findMaxApartmentNumber(oldApartmentsList, newApartmentsList);
        Map<Integer, String> differencesInApartments = compareApartments(oldApartmentsList, newApartmentsList, maxApartmentNumber);
        saveToFile(differencesInApartments, destinationFile);
    }


    private List<String> getAllLinesFromFile(File file) {
        List<String> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            while (br.ready()) {
                String line = br.readLine();
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Apartment> getApartmentsFromList(List<String> list) {
        List<Apartment> apartmentsList = new ArrayList<>();
        for (String string : list) {
            if (isImportantLine(string)) {
                try {
                    String modifiedString = modifyString(string);
                    String[] apartmentsInfo = trimArray(modifiedString.split("\t"));
                    int number = Integer.parseInt(apartmentsInfo[0]);
                    int level = Integer.parseInt(apartmentsInfo[1]);
                    double surface = Double.parseDouble(apartmentsInfo[2]);
                    int numberOfRooms = Integer.parseInt(apartmentsInfo[3]);
                    int priceForM2 = Integer.parseInt(apartmentsInfo[5].replace(" ", ""));
                    int totalprice = Integer.parseInt(apartmentsInfo[6].replace(" ", ""));
                    boolean availability = apartmentsInfo[7].equals("Zarezerwowane");
                    Apartment apartment = new Apartment(number, level, surface, numberOfRooms, priceForM2, totalprice, availability);
                    apartmentsList.add(apartment);
                } catch (Exception e) {

                    System.out.println("Incorrect data type from source");
                    System.exit(1);
                }
            }

        }
        return apartmentsList;
    }

    private String[] trimArray(String[] stringArray) {
        String[] tempArray = new String[stringArray.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = stringArray[i].trim();
        }
        return tempArray;
    }

    private boolean isImportantLine(String string) {
        return string.length() > 10;//Filter date and short length data
    }

    private String modifyString(String string) {
        String[][] replacements = {{",", "."},
                {"m2", ""},
                {"z�", ""},
                {" ", ""},
                {"-", ""},
                {"zł", ""}
        };
        String strOutput = string;
        for (String[] replacement : replacements) {
            strOutput = strOutput.replace(replacement[0], replacement[1]);
        }
        ;
        return strOutput;
    }

    private int findMaxApartmentNumber(List<Apartment> list1, List<Apartment> list2) {
        int maxApartmentNumber = 0;
        for (Apartment apartment : list1) {
            if (apartment.getNumber() > maxApartmentNumber) {
                maxApartmentNumber = apartment.getNumber();
            }
        }
        for (Apartment apartment : list2) {
            if (apartment.getNumber() > maxApartmentNumber) {
                maxApartmentNumber = apartment.getNumber();
            }
        }
        return maxApartmentNumber;
    }

    private Map<Integer, String> compareApartments(List<Apartment> list1, List<Apartment> list2, int maxApartmentNumber) {
        Map<Integer, String> map = new LinkedHashMap<>();

        for (int i = 1; i <= maxApartmentNumber; i++) {
            Apartment apartment1 = null;
            Apartment apartment2 = null;
            for (Apartment apartment : list1) {
                if (apartment.getNumber() == i) {
                    apartment1 = apartment;
                    break;
                }
            }
            for (Apartment apartment : list2) {
                if (apartment.getNumber() == i) {
                    apartment2 = apartment;
                    break;
                }
            }
            if (apartment1 != null && apartment2 != null) {
                if (apartment1.equals(apartment2)) {
                    map.put(i, "Nothing changed.");
                } else {
                    String diff = findDifferences(apartment1, apartment2);
                    map.put(i, diff);
                }
            } else if (apartment1 != null) {
                map.put(i, "The apartment has been bought.");
            } else if (apartment2 != null) {
                map.put(i, "The apartment has been released.");
            }
        }
        return map;
    }

    private String findDifferences(Apartment apartment1, Apartment apartment2) {
        StringBuilder sb = new StringBuilder();
        if (apartment1.getSurface() != apartment2.getSurface()) {
            sb.append("Surface changed from ");
            sb.append(apartment1.getSurface());
            sb.append(" to ");
            sb.append(apartment2.getSurface());
            sb.append(".");
        }
        if (apartment1.getPricePerSquare() != apartment2.getPricePerSquare()) {
            sb.append("Price per square changed from ");
            sb.append(apartment1.getPricePerSquare());
            sb.append(" to ");
            sb.append(apartment2.getPricePerSquare());
            sb.append(".");
        }
        if (apartment1.getTotalprice() != apartment2.getTotalprice()) {
            sb.append("Total price changed from ");
            sb.append(apartment1.getTotalprice());
            sb.append(" to ");
            sb.append(apartment2.getTotalprice());
            sb.append(".");
        }
        if (apartment1.getNumberOfRooms() != apartment2.getNumberOfRooms()) {
            sb.append("Number of rooms changed from ");
            sb.append(apartment1.getNumberOfRooms());
            sb.append(" to ");
            sb.append(apartment2.getNumberOfRooms());
            sb.append(".");
        }
        if (apartment1.isAvailability() != apartment2.isAvailability()) {
            sb.append("Availability changed from ");
            if (apartment1.isAvailability()) {
                sb.append("Available");
            } else {
                sb.append("Reserved");
            }
            sb.append(" to ");
            if (apartment2.isAvailability()) {
                sb.append("Available");
            } else {
                sb.append("Reserved");
            }
            sb.append(".");
        }
        return sb.toString();
    }

    private List<String> getDataFromUrl() { //Create file from url
        String url = "https://grzegorzkipark.pl/grzegorzki-park-budynek-8";
        List<String> tempList = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();
            int i = 0;
            for (Element row : document.select("table.tbl-apartments.sticky tr")) {
                if (i == 0) {//skip first line
                    i++;
                    continue;
                }
                if (!row.select("td.nr").text().equals("")) {
                    StringBuilder sb = new StringBuilder();
                    String number = row.select("td.nr").text();
                    String level = row.select("td.floor").text();
                    String surface = row.select("td.surface").text();
                    String numberOfRooms = row.select("td.rooms").text();
                    String balcony = row.select("td.balcony").text();
                    String pricePerSquare = row.select("td.price").text().split("zł")[0];
                    String totalprice = row.select("td.price").text().split("zł")[1];
                    String reserved = row.select("td.notepad").text();
                    sb.append(number + "\t");
                    sb.append(level + "\t");
                    sb.append(surface + "\t");
                    sb.append(numberOfRooms + "\t");
                    sb.append(balcony + "\t");
                    sb.append(pricePerSquare + "\t");
                    sb.append(totalprice + "\t");
                    if (!reserved.equals("")) {
                        sb.append(reserved + "\t");
                    } else {
                        sb.append("Dodaj do notatnika\t");
                    }
                    tempList.add(sb.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    public boolean isComparisionCompleted() {
        return comparisionCompleted;
    }

    public List<Apartment> getNewApartmentsList() {
        return newApartmentsList;
    }

    private void saveToFile(Map<Integer, String> map, File destionationFile) {

        if (!destionationFile.getAbsolutePath().endsWith(".txt")) {
            destionationFile = new File(destionationFile.getAbsolutePath() + ".txt");
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destionationFile))) {
            for (Map.Entry<Integer, String> tempMap : map.entrySet()) {
                bufferedWriter.write("Apartment number " + tempMap.getKey() + ":");
                bufferedWriter.write(tempMap.getValue());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        comparisionCompleted = true;
    }
}


