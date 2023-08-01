package UI;

import UI.MainWindowComponents.CardContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents a panel for adding cards to a digital flashcard application.
 * It follows the Singleton pattern to ensure only one instance of this panel exists.
 * It also implements ActionListener to handle button press events.
 */
public class AddCardPanel extends JPanel implements ActionListener {

    // Singleton instance of AddCardPanel.
    private static AddCardPanel instance;

    private String cardTitle;

    // Swing components used for user input.
    private final JTextArea cardFrontTextArea = new JTextArea();
    private final JTextField titleTextField = new JTextField();
    private final JButton addButton = new JButton();
    private final JTextField rightAnswerField = new JTextField();
    private final JTextField wrongAnswerFieldA = new JTextField();
    private final JTextField wrongAnswerFieldB = new JTextField();

    // Variables related to file handling.
    private String folderName;
    private File rootFolder;

    /**
     * Returns the Singleton instance of AddCardPanel, creating it if it doesn't exist.
     * @return instance of AddCardPanel
     */
    public static AddCardPanel getInstance() {
        if (instance == null) {
            instance = new AddCardPanel();
        }
        return instance;
    }

    /**
     * Private constructor following the Singleton design pattern.
     * Initializes file handling and Swing components, and configures the layout.
     */
    private AddCardPanel() {
        // Initialize the folder variables.
        // Instance of SelectDeckPanel.
        SelectDeckPanel selectDeckPanel = SelectDeckPanel.getInstance();
        folderName = selectDeckPanel.getFolderName();
        rootFolder = new File("./flashyCard_DB/" + folderName);

        // Set layout manager.
        this.setLayout(new BorderLayout());

        // Create and configure headerPanel.
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        // Create and configure contentPanel.
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        // Configure GridBagConstraints.
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;

        // Set up headerTitleLabel.
        JLabel headerTitleLabel = new JLabel();
        headerTitleLabel.setFont(getFont().deriveFont(Font.BOLD, 12));
        headerTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerTitleLabel.setText("Enter the Card-name");

        // Configure GridBagConstraints for the grid items.
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);

        // Add headerTitleLabel to headerPanel.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        headerPanel.add(headerTitleLabel, gridBagConstraints);

        // Add titleTextField to headerPanel.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        headerPanel.add(titleTextField, gridBagConstraints);

        // Set up and add cardFrontLabel to headerPanel.
        JLabel cardFrontLabel = new JLabel();
        cardFrontLabel.setFont(getFont().deriveFont(Font.BOLD, 12));
        cardFrontLabel.setHorizontalAlignment(JLabel.CENTER);
        cardFrontLabel.setText("A Question for the Card-front:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        headerPanel.add(cardFrontLabel, gridBagConstraints);

        // Configure and add cardFrontTextArea to headerPanel.
        cardFrontTextArea.setRows(5);
        cardFrontTextArea.setColumns(30);
        cardFrontTextArea.setLineWrap(true);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        headerPanel.add(cardFrontTextArea, gridBagConstraints);

        // Set up and add cardBackLabel to headerPanel.
        JLabel cardBackLabel = new JLabel();
        cardBackLabel.setFont(getFont().deriveFont(Font.BOLD, 12));
        cardBackLabel.setHorizontalAlignment(JLabel.CENTER);
        cardBackLabel.setText("Enter the Correct Answer:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        headerPanel.add(cardBackLabel, gridBagConstraints);

        // Add rightAnswerField to headerPanel.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        headerPanel.add(rightAnswerField, gridBagConstraints);

        // Set up and add cardBackWrongLabelA to headerPanel.
        JLabel cardBackWrongLabelA = new JLabel();
        cardBackWrongLabelA.setFont(getFont().deriveFont(Font.BOLD, 12));
        cardBackWrongLabelA.setHorizontalAlignment(JLabel.CENTER);
        cardBackWrongLabelA.setText("Enter a Wrong Answer 1:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        headerPanel.add(cardBackWrongLabelA, gridBagConstraints);

        // Add wrongAnswerFieldA to headerPanel.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        headerPanel.add(wrongAnswerFieldA, gridBagConstraints);

        // Set up and add cardBackWrongLabelB to headerPanel.
        JLabel cardBackWrongLabelB = new JLabel();
        cardBackWrongLabelB.setFont(getFont().deriveFont(Font.BOLD, 12));
        cardBackWrongLabelB.setHorizontalAlignment(JLabel.CENTER);
        cardBackWrongLabelB.setText("Enter a Wrong Answer 2:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        headerPanel.add(cardBackWrongLabelB, gridBagConstraints);

        // Add wrongAnswerFieldB to headerPanel.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        headerPanel.add(wrongAnswerFieldB, gridBagConstraints);

        // Configure and add addButton to headerPanel.
        addButton.setText("Add Card");
        addButton.setSize(100, 50);
        addButton.addActionListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        headerPanel.add(addButton, gridBagConstraints);

        // Add headerPanel to contentPanel.
        contentPanel.add(headerPanel, c);

        // Add contentPanel to this panel.
        this.add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * This method is triggered when an action event occurs. Specifically, it handles events from the addButton.
     * It retrieves the text inputs from various text fields and text areas,
     * and calls the addCardToDeck() method to add the new card to the deck.
     *
     * @param e The action event that triggered this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the source of the action event is the addButton
        if (e.getSource() == addButton) {
            // Get the card's title from the titleTextField
            cardTitle = titleTextField.getText();
            //Replacing whitespace with _ and äöü/ÖÄÜ
            cardTitle = cardTitle.replace(" ", "_");
            cardTitle = cardTitle.replace("ä", "ae");
            cardTitle = cardTitle.replace("ö", "oe");
            cardTitle = cardTitle.replace("ü", "ue");
            cardTitle = cardTitle.replace("Ä", "Ae");
            cardTitle = cardTitle.replace("Ö", "Oe");
            cardTitle = cardTitle.replace("Ü", "Ue");
            // Get the front text of the card from the cardFrontTextArea
            // Variables holding user input for the flashcard front text, card title, and correct answer.
            String frontText = cardFrontTextArea.getText();
            // Get the correct answer from the rightAnswerField
            String rightAnswer = rightAnswerField.getText();
            // Get the first incorrect answer from wrongAnswerFieldA
            String wrongAnswerA = wrongAnswerFieldA.getText();
            // Get the second incorrect answer from wrongAnswerFieldB
            String wrongAnswerB = wrongAnswerFieldB.getText();

            try {
                // Try to add the card to the deck
                addCardToDeck(frontText, rightAnswer, wrongAnswerA, wrongAnswerB);
            } catch (IOException ex) {
                // If an IOException occurs, throw a new RuntimeException
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * This method is used to add a card to the deck.
     * It creates necessary directories and files, and writes the card data to the files.
     *
     * @param frontText    The text for the front of the card.
     * @param rightAnswer  The correct answer for the card.
     * @param wrongAnswerA The first incorrect answer for the card.
     * @param wrongAnswerB The second incorrect answer for the card.
     * @throws IOException If an I/O error occurs.
     */
    private void addCardToDeck(String frontText, String rightAnswer, String wrongAnswerA, String wrongAnswerB) throws IOException {
        // Initialize the File objects for the directories and files.
        File folder = new File(rootFolder, cardTitle);
        File folderFrontName = new File(folder, "front");
        File backRightTextFile = new File(folderFrontName, "right.txt");
        File backWrong1TextFile = new File(folderFrontName, "wrong1.txt");
        File backWrong2TextFile = new File(folderFrontName, "wrong2.txt");
        File frontTextFile = new File(folderFrontName, "front.txt");

        // Create the directories and files, and write the card data to the files.
        if (folder.mkdir()) {
            CardContent cardContent = CardContent.getInstance();
            cardContent.setDeckName(cardTitle);
            if (folderFrontName.mkdirs()) {
                try {
                    if (frontTextFile.createNewFile()) {
                        FileWriter fileWriter = new FileWriter(frontTextFile.getAbsoluteFile());
                        fileWriter.write(frontText);
                        fileWriter.close();
                    }
                    if (backRightTextFile.createNewFile()){
                        FileWriter fileWriter = new FileWriter(backRightTextFile.getAbsoluteFile());
                        fileWriter.write(rightAnswer);
                        fileWriter.close();
                    }
                    if (backWrong1TextFile.createNewFile()) {
                        FileWriter fileWriter = new FileWriter(backWrong1TextFile.getAbsoluteFile());
                        fileWriter.write(wrongAnswerA);
                        fileWriter.close();
                    }
                    if (backWrong2TextFile.createNewFile()) {
                        FileWriter fileWriter = new FileWriter(backWrong2TextFile.getAbsoluteFile());
                        fileWriter.write(wrongAnswerB);
                        fileWriter.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "The Card Deck could not be created or a Card Deck with this Name already exists.");
        }
    }

    /**
     * This method is used to set the folder name for the deck.
     * It also initializes the rootFolder based on the folder name.
     *
     * @param folderName The name of the folder.
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
        rootFolder = new File("./flashyCard_DB/" + folderName);
    }


}
