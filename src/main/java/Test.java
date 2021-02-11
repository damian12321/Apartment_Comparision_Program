import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String url = "https://grzegorzkipark.pl/grzegorzki-park-budynek-8";
        List<String> tempList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            int i = 0;
            for (Element row : document.select("table.tbl-apartments.sticky tr")) {
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
                    if (reserved.isEmpty()) {
                        sb.append(reserved + "\t");
                    } else {
                        sb.append("Dodaj do notatnika\t");
                    }
                    tempList.add(sb.toString());
                    System.out.println(sb.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
