import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ComparatorJFrame extends JFrame implements ActionListener {
    private static final Color COLOR = new Color(227, 227, 227);
    private InformationJFrame informationJFrame;
    private JRadioButton twoFilesButton;
    private JRadioButton oneFileButton;
    private JLabel optionLabel;
    private JLabel oneFileLabel;
    private JLabel twoFilesLabel;
    private JLabel saveFileLabel;
    private JButton readFileButton1;
    private JButton readFileButton2;
    private JButton readFileButton3;
    private JButton saveFile;
    private JButton submitButton;
    private File file1;
    private File file2;
    private File outputFile;
    private JTextField file1PathText;
    private JTextField file2PathText;
    private JTextField file3PathText;
    private JTextField outputPathText;

    public ComparatorJFrame() {
        initComponents(this);
        this.setLayout(null);
        this.setTitle("Apartment Comparator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(1000, 800));
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - this.getWidth()) / 2;
        int iCoordY = (objDimension.height - this.getHeight()) / 2;
        this.setLocation(iCoordX, iCoordY);
        this.getContentPane().setBackground(new Color(227, 227, 227));
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
        jFrame.add(optionLabel);
        jFrame.add(oneFileLabel);
        jFrame.add(twoFilesLabel);
        jFrame.add(saveFileLabel);
    }

    private void initOptionLabel() {
        optionLabel = new JLabel("Choose comparing options");
        optionLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        optionLabel.setBounds(new Rectangle(10, 10, 400, 200));
        optionLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 60));
        optionLabel.setBackground(COLOR);
        optionLabel.setOpaque(true);
        optionLabel.setBorder(BorderFactory.createLineBorder(SystemColor.cyan, 1));//TO DELETE
        optionLabel.setBackground(COLOR);
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
        oneFileLabel = new JLabel("Please select file from disc:");
        oneFileLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        oneFileLabel.setBounds(new Rectangle(10, 210, 400, 200));
        oneFileLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));
        oneFileLabel.setBackground(COLOR);
        oneFileLabel.setOpaque(true);
        oneFileLabel.setBorder(BorderFactory.createLineBorder(SystemColor.cyan, 1));//TO DELETE
        oneFileLabel.setBackground(COLOR);
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
        oneFileLabel.add(file1PathText);

    }

    private void initFileChooser2() {
        twoFilesLabel = new JLabel("Please select file 1:   file2:");
        twoFilesLabel.setFont(new Font("INK Free", Font.BOLD, 20));
        twoFilesLabel.setBounds(new Rectangle(10, 210, 400, 200));
        twoFilesLabel.setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 70));
        twoFilesLabel.setBackground(COLOR);
        twoFilesLabel.setOpaque(true);
        twoFilesLabel.setBorder(BorderFactory.createLineBorder(SystemColor.cyan, 1));//TO DELETE
        twoFilesLabel.setBackground(COLOR);
        twoFilesLabel.setVisible(false);
        twoFilesLabel.setVerticalAlignment(SwingConstants.TOP);
        twoFilesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        twoFilesLabel.add(readFileButton2);
        twoFilesLabel.add(readFileButton3);
        twoFilesLabel.add(file2PathText);
        twoFilesLabel.add(file3PathText);
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
        saveFileLabel.setBounds(new Rectangle(10, 410, 400, 200));
        saveFileLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 90));
        saveFileLabel.setBackground(COLOR);
        saveFileLabel.setOpaque(true);
        saveFileLabel.setBorder(BorderFactory.createLineBorder(SystemColor.cyan, 1));//TO DELETE
        saveFileLabel.setBackground(COLOR);
        saveFileLabel.setVisible(false);
        saveFileLabel.setVerticalAlignment(SwingConstants.TOP);
        saveFileLabel.setHorizontalAlignment(SwingConstants.LEFT);
        saveFileLabel.add(saveFile);
        saveFileLabel.add(submitButton);
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
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setVisible(false);
    }
    private void initTextFields()
    {
        file1PathText=new JTextField();
        file2PathText=new JTextField();
        file3PathText=new JTextField();
        outputPathText=new JTextField();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == oneFileButton) {
            saveFileLabel.setVisible(true);
            oneFileLabel.setVisible(true);
            twoFilesLabel.setVisible(false);
        }
        if (e.getSource() == twoFilesButton) {
            saveFileLabel.setVisible(true);
            twoFilesLabel.setVisible(true);
            oneFileLabel.setVisible(false);
        }
        if (e.getSource() == readFileButton1) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null);
            if (response == 0) {
                file1 = new File(fileChooser.getSelectedFile().getAbsolutePath());
//                file1Path=new JTextField(file1.getAbsolutePath());
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
//                file1Path=new JTextField(file1.getAbsolutePath());
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
//                file2Path=new JTextField(file2.getAbsolutePath());
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
//                outputPath=new JTextField(outputFile.getAbsolutePath());
            }
        }
        if(file1!=null&&file2!=null&&outputFile!=null&&twoFilesLabel.isVisible())
        {
            submitButton.setVisible(true);
        }
        if(file1!=null&&oneFileLabel.isVisible()&&outputFile!=null)
        {
            submitButton.setVisible(true);
        }
        if (e.getSource() == submitButton) {
            if(twoFilesLabel.isVisible()) {
                new ApartmentComparator(file1,file2,outputFile);
            }else
            {
                new ApartmentComparator(file1,outputFile);
            }
        }
    }

}
