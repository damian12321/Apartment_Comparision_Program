import java.io.*;
import java.util.*;

public class ApartmentComparator {
    private File firstFileName;
    private File secondFileName;
    private List<String> list1;
    private List<Apartment> oldApartmentsList;
    private List<String> list2;
    private List<Apartment> newApartmentsList;
    private int maxApartmentNumber;
    private Map<Integer, String> differencesInApartments;

    public ApartmentComparator(File firstFileName, File secondFileName) {
        this.firstFileName = firstFileName;
        this.secondFileName = secondFileName;
    }

    public void compare(File destinationFile) {
        List<String> list1 = getAllLinesFromFile(firstFileName);
        List<Apartment> oldApartmentsList = getApartmentsFromList(list1);
        List<String> list2 = getAllLinesFromFile(secondFileName);
        List<Apartment> newApartmentsList = getApartmentsFromList(list2);
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
                String modifiedString = modifyString(string);
                String[] apartmentsInfo = trimArray(modifiedString.split("\t"));
                int number = Integer.parseInt(apartmentsInfo[0]);
                int level = Integer.parseInt(apartmentsInfo[1]);
                double dimension = Double.parseDouble(apartmentsInfo[2]);
                int numberOfRooms = Integer.parseInt(apartmentsInfo[3]);
                int priceForM2 = Integer.parseInt(apartmentsInfo[5]);
                int totalprice = Integer.parseInt(apartmentsInfo[6]);
                boolean availability = apartmentsInfo[7].equals("Zarezerwowane");
                Apartment apartment = new Apartment(number, level, dimension, numberOfRooms, priceForM2, totalprice, availability);
                apartmentsList.add(apartment);
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
                {" ", ""},
                {"z�", ""}
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
        if (apartment1.getDimension() != apartment2.getDimension()) {
            sb.append("Dimension changed from ");
            sb.append(apartment1.getDimension());
            sb.append(" to ");
            sb.append(apartment2.getDimension());
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
            if(apartment1.isAvailability()){
                sb.append("Available");
            }else
            {
                sb.append("Reserved");
            }
            sb.append(" to ");
            if(apartment2.isAvailability()){
                sb.append("Available");
            }else
            {
                sb.append("Reserved");
            }
            sb.append(".");
        }
        return sb.toString();
    }

    private void saveToFile(Map<Integer, String> map, File destionationFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destionationFile))) {
            for (Map.Entry<Integer, String> tempMap : map.entrySet()) {
                bufferedWriter.write("Apartment number " + tempMap.getKey() + ":");
                bufferedWriter.write(tempMap.getValue());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


