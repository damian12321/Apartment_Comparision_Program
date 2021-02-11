import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class InformationJFrame extends JFrame {
    private Apartment apartment;
    private static final int NUMBER = 4806;

    public InformationJFrame(Apartment apartment) {
        this.apartment = apartment;
        initComponents(this);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - this.getWidth()) / 2;
        int iCoordY = (objDimension.height - this.getHeight()) / 2;
        this.setLocation(iCoordX, iCoordY);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void initComponents(JFrame jFrame) {
        JLabel jLabel = new JLabel();
        this.add(jLabel);
        String urlToIcon = findUrlToImage(apartment);
        ImageIcon imageIcon = new ImageIcon(getImage(urlToIcon));
        jLabel.setIcon(imageIcon);
        jLabel.setText(apartment.toString());
        jLabel.setVerticalTextPosition(jLabel.BOTTOM);
        jLabel.setHorizontalTextPosition(jLabel.CENTER);
        jLabel.setIconTextGap(-40);
        jLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        jLabel.setForeground(new Color(5, 5, 94));
        jFrame.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight() + jLabel.getFont().getSize());
    }

    private Image getImage(String url) {
        Image image = null;

        try {
            URL tempUrl = new URL(url);
            image = ImageIO.read(tempUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private String findUrlToImage(Apartment apartment) {
        String s = null;
        try {
            URL url = new URL("https://grzegorzkipark.pl/szczegoly/mieszkanie/" + (NUMBER + apartment.getNumber()));
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                if (inputLine.contains("<img alt=\"\" class=\"schema\" src=")) {
                    int startPos = inputLine.indexOf("https");
                    int endPos = inputLine.indexOf("\" />");
                    s = inputLine.substring(startPos, endPos);
                    break;
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}


