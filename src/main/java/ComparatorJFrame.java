import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class ComparatorJFrame extends JFrame implements ActionListener {
    private static final Color COLOR = new Color(227, 227, 227);
    private ApartmentComparator apartmentComparator;
    private JRadioButton twoFilesButton;
    private JRadioButton oneFileButton;
    private JLabel optionLabel;
    private JLabel oneFileLabel;
    private JLabel twoFilesLabel;
    private JLabel saveFileLabel;
    private JLabel textLabel1;
    private JLabel textLabel2;
    private JLabel textLabel3;
    private JLabel completedLabel;
    private JLabel tableLabel;
    private JLabel apartmentChooserLabel;
    private JButton readFileButton1;
    private JButton readFileButton2;
    private JButton readFileButton3;
    private JButton saveFile;
    private JButton saveSubmitButton;
    private JButton openSubmitButton;
    private JComboBox jComboBox;
    private File file1;
    private File file2;
    private File outputFile;
    private JTextField file1PathText;
    private JTextField file2PathText;
    private JTextField outputPathText;
    private boolean wasSubmitted=false;


    public ComparatorJFrame() {
        initComponents(this);
        this.setLayout(null);
        this.setTitle("Apartment Comparator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(930, 730));
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - this.getWidth()) / 2;
        int iCoordY = (objDimension.height - this.getHeight()) / 2;
        this.setLocation(iCoordX, iCoordY);
        this.getContentPane().setBackground(new Color(227, 227, 227));
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initComponents(JFrame jFrame) {
        initFirstButton();
        initSecondButton();
        initOptionLabel();
        initFileButtons();
        initTextFields();
        initFileChooser1();
        initFileChooser2();
        initSaveLabel();
        initTextLabel1();
        initTextLabel2();
        initTextLabel3();
        initComparisionCompletedLabel();
        jFrame.add(optionLabel);
        jFrame.add(oneFileLabel);
        jFrame.add(twoFilesLabel);
        jFrame.add(saveFileLabel);
        jFrame.add(completedLabel);
        jFrame.add(textLabel1);
        jFrame.add(textLabel2);
        jFrame.add(textLabel3);
    }

    private void initOptionLabel() {
        optionLabel = new JLabel("Choose comparing options");
        optionLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        optionLabel.setBounds(new Rectangle(10, 10, 400, 200));
        optionLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 60));
        optionLabel.setBackground(COLOR);
        optionLabel.setOpaque(true);
        String pathToIcon = null;
        try {
            pathToIcon = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon jLabelImageIcon = new ImageIcon(pathToIcon + "\\src\\main\\resources\\choose.png");
        optionLabel.setIcon(jLabelImageIcon);
        optionLabel.setVerticalAlignment(SwingConstants.TOP);
        optionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        optionLabel.add(oneFileButton);
        optionLabel.add(twoFilesButton);
        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(oneFileButton);
        optionGroup.add(twoFilesButton);

    }

    private void initFirstButton() {
        oneFileButton = new JRadioButton("Compare file with online version");
        oneFileButton.setFont(new Font("INK Free", Font.BOLD, 20));
        oneFileButton.setBackground(COLOR);
        oneFileButton.addActionListener(this);


    }

    private void initSecondButton() {
        twoFilesButton = new JRadioButton("Compare two files");
        twoFilesButton.setFont(new Font("INK Free", Font.BOLD, 20));
        twoFilesButton.setBackground(COLOR);
        twoFilesButton.addActionListener(this);

    }

    private void initFileChooser1() {
        oneFileLabel = new JLabel("Please select file from disk:");
        oneFileLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        oneFileLabel.setBounds(new Rectangle(10, 210, 400, 100));
        oneFileLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));
        oneFileLabel.setBackground(COLOR);
        oneFileLabel.setOpaque(true);
        oneFileLabel.setVisible(false);
        oneFileLabel.setVerticalAlignment(SwingConstants.TOP);
        oneFileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        oneFileLabel.add(readFileButton1);
        String pathToIcon = null;
        try {
            pathToIcon = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon imageIcon = new ImageIcon(pathToIcon + "\\src\\main\\resources\\folder.png");
        oneFileLabel.setIcon(imageIcon);

    }

    private void initFileChooser2() {
        twoFilesLabel = new JLabel("Please select file 1:   file2:");
        twoFilesLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        twoFilesLabel.setBounds(new Rectangle(10, 210, 400, 100));
        twoFilesLabel.setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 70));
        twoFilesLabel.setOpaque(true);
        twoFilesLabel.setBackground(COLOR);
        twoFilesLabel.setVisible(false);
        twoFilesLabel.setVerticalAlignment(SwingConstants.TOP);
        twoFilesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        twoFilesLabel.add(readFileButton2);
        twoFilesLabel.add(readFileButton3);
        String pathToIcon = null;
        try {
            pathToIcon = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon imageIcon = new ImageIcon(pathToIcon + "\\src\\main\\resources\\folder.png");
        twoFilesLabel.setIcon(imageIcon);
    }

    private void initSaveLabel() {
        saveFileLabel = new JLabel("Save as:");
        saveFileLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        saveFileLabel.setBounds(new Rectangle(10, 410, 400, 100));
        saveFileLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 70));
        saveFileLabel.setBackground(COLOR);
        saveFileLabel.setOpaque(true);
        saveFileLabel.setVisible(false);
        saveFileLabel.setVerticalAlignment(SwingConstants.TOP);
        saveFileLabel.setHorizontalAlignment(SwingConstants.LEFT);
        saveFileLabel.add(saveFile);
        saveFileLabel.add(saveSubmitButton);
        String pathToIcon = null;
        try {
            pathToIcon = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon imageIcon = new ImageIcon(pathToIcon + "\\src\\main\\resources\\save.png");
        saveFileLabel.setIcon(imageIcon);
    }

    private void initFileButtons() {
        readFileButton1 = new JButton("Select a file");
        readFileButton1.addActionListener(this);
        readFileButton2 = new JButton("Select a file");
        readFileButton2.addActionListener(this);
        readFileButton3 = new JButton("Select a file");
        readFileButton3.addActionListener(this);
        saveFile = new JButton("Select a file");
        saveFile.addActionListener(this);
        saveSubmitButton = new JButton("Submit");
        saveSubmitButton.addActionListener(this);
        saveSubmitButton.setVisible(false);
    }

    private void initTextFields() {
        file1PathText = new JTextField();
        file1PathText.setPreferredSize(new Dimension(250, 20));
        file1PathText.setEditable(false);
        file2PathText = new JTextField();
        file2PathText.setPreferredSize(new Dimension(250, 20));
        file2PathText.setEditable(false);
        outputPathText = new JTextField();
        outputPathText.setPreferredSize(new Dimension(250, 20));
        outputPathText.setEditable(false);
    }

    private void initTextLabel1() {
        textLabel1 = new JLabel("Path to file 1: ");
        textLabel1.setFont(new Font("INK Free", Font.BOLD, 20));
        textLabel1.setBounds(new Rectangle(10, 310, 400, 50));
        textLabel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 00, 5));
        textLabel1.setBackground(COLOR);
        textLabel1.setOpaque(true);
        textLabel1.setVisible(false);
        textLabel1.setVerticalAlignment(SwingConstants.TOP);
        textLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        textLabel1.add(file1PathText);
    }

    private void initTextLabel2() {
        textLabel2 = new JLabel("Path to file 2: ");
        textLabel2.setFont(new Font("INK Free", Font.BOLD, 20));
        textLabel2.setBounds(new Rectangle(10, 360, 400, 50));
        textLabel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 00, 5));
        textLabel2.setBackground(COLOR);
        textLabel2.setOpaque(true);
        textLabel2.setVisible(false);
        textLabel2.setVerticalAlignment(SwingConstants.TOP);
        textLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        textLabel2.add(file2PathText);
    }

    private void initTextLabel3() {
        textLabel3 = new JLabel("Output path: ");
        textLabel3.setFont(new Font("INK Free", Font.BOLD, 20));
        textLabel3.setBounds(new Rectangle(10, 510, 400, 50));
        textLabel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
        textLabel3.setBackground(COLOR);
        textLabel3.setOpaque(true);
        textLabel3.setVisible(false);
        textLabel3.setVerticalAlignment(SwingConstants.TOP);
        textLabel3.setHorizontalAlignment(SwingConstants.LEFT);
        textLabel3.add(outputPathText);
    }

    private void initComparisionCompletedLabel() {
        completedLabel = new JLabel("Comparision completed");
        completedLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        completedLabel.setBounds(new Rectangle(10, 560, 400, 150));
        completedLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));
        completedLabel.setBackground(COLOR);
        completedLabel.setOpaque(true);
        completedLabel.setVisible(false);
        completedLabel.setVerticalAlignment(SwingConstants.TOP);
        completedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        String pathToIcon = null;
        try {
            pathToIcon = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon imageIcon = new ImageIcon(pathToIcon + "\\src\\main\\resources\\finished.png");
        completedLabel.setIcon(imageIcon);
    }
    private void initApartmentChooserLabel()
    {
        apartmentChooserLabel = new JLabel("<html>The apartments are listed in the table below.<br/>If you want to see more details, please select:</html>");
        apartmentChooserLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        apartmentChooserLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        apartmentChooserLabel.setVerticalTextPosition(SwingConstants.TOP);
        apartmentChooserLabel.setBounds(new Rectangle(410, 110, 500, 150));
        apartmentChooserLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));
        apartmentChooserLabel.setBackground(COLOR);
        apartmentChooserLabel.setOpaque(true);
        apartmentChooserLabel.setVisible(false);
        apartmentChooserLabel.setVerticalAlignment(SwingConstants.TOP);
        apartmentChooserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jComboBox=new JComboBox();
        jComboBox.addActionListener(this);
        for(Apartment apartment:apartmentComparator.getNewApartmentsList())
        {
            jComboBox.addItem(apartment.getNumber());
        }
        openSubmitButton = new JButton("Submit");
        openSubmitButton.addActionListener(this);
        apartmentChooserLabel.add(jComboBox);
        apartmentChooserLabel.add(openSubmitButton);

    }

    private void initTableLabel() {
        tableLabel = new JLabel();
        tableLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        tableLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tableLabel.setBounds(new Rectangle(370, 240, 600, 450));
        tableLabel.setLayout(new FlowLayout());
        tableLabel.setBackground(COLOR);
        tableLabel.setOpaque(true);
        tableLabel.setVisible(true);
        tableLabel.setVerticalAlignment(SwingConstants.CENTER);
        tableLabel.setHorizontalAlignment(SwingConstants.LEFT);
        JPanel panel = new JPanel();
        String[] columnNames = {"Number",
                "Level",
                "Surface[m2]",
                "Number of rooms",
                "Price per square[zł]",
                "Total price[zł]",
                "Reserved"};
        Collections.sort(apartmentComparator.getNewApartmentsList());
        Object[][] data = new Object[apartmentComparator.getNewApartmentsList().size()][7];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = apartmentComparator.getNewApartmentsList().get(i).getNumber();
                        break;
                    case 1:
                        data[i][j] = apartmentComparator.getNewApartmentsList().get(i).getLevel();
                        break;
                    case 2:
                        data[i][j] = apartmentComparator.getNewApartmentsList().get(i).getSurface();
                        break;
                    case 3:
                        data[i][j] = apartmentComparator.getNewApartmentsList().get(i).getNumberOfRooms();
                        break;
                    case 4:
                        data[i][j] = apartmentComparator.getNewApartmentsList().get(i).getPricePerSquare();
                        break;
                    case 5:
                        data[i][j] = apartmentComparator.getNewApartmentsList().get(i).getTotalprice();
                        break;
                    case 6:
                        data[i][j] = apartmentComparator.getNewApartmentsList().get(i).isAvailability();
                        break;
                }
            }
        }

        TableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.ORANGE);
        JScrollPane pane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        panel.add(pane);
        panel.setBackground(COLOR);
        panel.setOpaque(true);
        tableLabel.add(panel);

    }
    private void reset()
    {
          completedLabel.setVisible(false);
          tableLabel.setVisible(false);
          apartmentChooserLabel.setVisible(false);
          saveSubmitButton.setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == oneFileButton) {
            saveFileLabel.setVisible(true);
            oneFileLabel.setVisible(true);
            twoFilesLabel.setVisible(false);
            textLabel1.setVisible(true);
            textLabel2.setVisible(false);
            textLabel3.setVisible(true);
            if(wasSubmitted) {
                wasSubmitted = false;
                reset();
            }

        }
        if (e.getSource() == twoFilesButton) {
            saveFileLabel.setVisible(true);
            twoFilesLabel.setVisible(true);
            oneFileLabel.setVisible(false);
            textLabel1.setVisible(true);
            textLabel2.setVisible(true);
            textLabel3.setVisible(true);
            if(wasSubmitted) {
                wasSubmitted = false;
                reset();
            }
        }
        if (e.getSource() == readFileButton1) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null);
            if (response == 0) {
                file1 = new File(fileChooser.getSelectedFile().getAbsolutePath());
                file1PathText.setText(file1.getAbsolutePath());
                file1PathText.repaint();
            }
        }
        if (e.getSource() == readFileButton2) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null);
            if (response == 0) {
                file1 = new File(fileChooser.getSelectedFile().getAbsolutePath());
                file1PathText.setText(file1.getAbsolutePath());
                file1PathText.repaint();
            }
        }
        if (e.getSource() == readFileButton3) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null);
            if (response == 0) {
                file2 = new File(fileChooser.getSelectedFile().getAbsolutePath());
                file2PathText.setText(file2.getAbsolutePath());
                file2PathText.repaint();
            }
        }
        if (e.getSource() == saveFile) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showSaveDialog(null);
            if (response == 0) {
                outputFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                outputPathText.setText(outputFile.getAbsolutePath());
            }
        }
        if (file1 != null && file2 != null && outputFile != null && twoFilesLabel.isVisible()) {
            saveSubmitButton.setVisible(true);
        }else
        {
            saveSubmitButton.setVisible(false);
        }
        if (file1 != null && oneFileLabel.isVisible() && outputFile != null) {
            saveSubmitButton.setVisible(true);
        }
        if (e.getSource() == saveSubmitButton) {
            if (twoFilesLabel.isVisible()) {
                apartmentComparator = new ApartmentComparator(file1, file2, outputFile);
            } else {
                apartmentComparator = new ApartmentComparator(file1, outputFile);
            }
            if (apartmentComparator.isComparisionCompleted()) {
                wasSubmitted=true;
                initTableLabel();
                initApartmentChooserLabel();
                completedLabel.setVisible(true);
                this.add(tableLabel);
                this.add(apartmentChooserLabel);
                apartmentChooserLabel.setVisible(true);

            }
        }
        if (e.getSource() == openSubmitButton) {
            int index=jComboBox.getSelectedIndex();
            Apartment apartment=apartmentComparator.getNewApartmentsList().get(index);
            new InformationJFrame(apartment);
        }
        }
    }


