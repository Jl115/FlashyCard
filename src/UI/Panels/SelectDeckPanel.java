package UI.Panels;
import FolderController.*;
import UI.MainWindowComponents.CardContent;
import UI.MainWindowComponents.HeaderMainWindow;
import UI.MenuComponents.DeckSelectionListener;
import UI.MenuComponents.SideMenuOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

/**
 * This panel allows users to select, create, and delete "decks", represented as directories within a specified path.
 */
public class SelectDeckPanel extends JPanel implements ActionListener, FolderController {
    // Singleton instance of the SelectDeckPanel
    private static SelectDeckPanel instance;

    // Name of the folder to be created or deleted
    protected String folderName;
    private static String selectDeck;

    // Text field for the user to input the name of the deck
    private final JTextField folderTextField = new JTextField();
    private final JButton folderAddButton = new JButton();
    private final JButton deleteFolderButton = new JButton();
    private final JButton selectButton = new JButton();

    // Accessing relative path of the folder for the decks
    private final File rootFile = new File("./flashyCard_DB");
    private final DefaultListModel<String> dataModel = new DefaultListModel<>();
    private final JList<String> dataList = new JList<>(this.dataModel);

    private static String cardTitle;

    /**
     * Private constructor for Singleton pattern.
     * Sets up the panel layout and functionality.
     */
    private SelectDeckPanel(String cardTitle) {
        SelectDeckPanel.cardTitle = cardTitle;
        this.setLayout(new BorderLayout());

        // Check if the root directory exists, if not create it
        checkAndCreateDirectory(rootFile);

        // Populate the list model with existing deck names
        fillDataModel(dataModel, rootFile);

        // Setting up list properties
        dataList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        dataList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(getFont().deriveFont(Font.PLAIN, 16 ));
                return label;
            }
        });

        // Adding list with a scroll pane to the panel
        this.add(new JScrollPane(dataList), BorderLayout.CENTER);

        // Add an action to the text field to create a new deck
        this.folderTextField.addActionListener(e -> addFolder());

        // Setting up the create deck button
        folderAddButton.setText("Create Deck");
        folderAddButton.addActionListener(this);

        // Setting up the delete deck button
        deleteFolderButton.setText("Delete Deck");
        deleteFolderButton.addActionListener(this);

        // Setting up the select deck button
        selectButton.setText("Select Deck");
        selectButton.addActionListener(this);

        // Create and add footer panel with text field and buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.add(this.folderTextField, BorderLayout.CENTER);

        JPanel footerButtonPanel = new JPanel();
        footerButtonPanel.setLayout(new FlowLayout());
        footerButtonPanel.add(folderAddButton);
        footerButtonPanel.add(deleteFolderButton);
        footerButtonPanel.add(selectButton);

        footerPanel.add(footerButtonPanel, BorderLayout.LINE_END);

        this.add(footerPanel, BorderLayout.PAGE_END);

        // Display message if no decks exist
        if (dataModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "U need to create a Card deck.");
        }
    }

    /**
     * Singleton pattern instance accessor.
     * @return the SelectDeckPanel instance
     */
    public static SelectDeckPanel getInstance() {
        if (instance == null) {
            instance = new SelectDeckPanel(cardTitle);
        }
        return instance;
    }

    public String getFolderName() {
        return folderName;
    }

    public static String getSelectDeck() {
        return selectDeck;
    }

    public void setSelectDeck(String selectDeck) {
        SelectDeckPanel.selectDeck = selectDeck;
    }

    /**
     * Checks if the specified directory exists, and creates it if it doesn't.
     * @param directory The directory to check/create.
     */
    private void checkAndCreateDirectory(File directory) {
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                System.out.println("Could not create directory: " + directory.getAbsolutePath());
                System.exit(1);
            }
        }
    }

    /**
     * Creates a new deck directory with the name entered in the text field.
     */
    @Override
    public void addFolder() {
        this.folderName = this.folderTextField.getText();
        this.folderName = folderName.replace(" ", "_");
        this.folderName = folderName.replace("ä", "ae");
        this.folderName = folderName.replace("ö", "oe");
        this.folderName = folderName.replace("ü", "ue");
        this.folderName = folderName.replace("Ä", "Ae");
        this.folderName = folderName.replace("Ö", "Oe");
        this.folderName = folderName.replace("Ü", "Ue");
        // Creating a new file with the name inside the text field
        File folder = new File(this.rootFile, folderName);
        if (folder.mkdir()) {
            // If folder creation was successful
            this.dataModel.addElement(folder.getName());
        }else {
            JOptionPane.showMessageDialog(this, "The Card Deck could not be created or a Card Deck with this Name already exist.");
        }
    }

    /**
     * Populates the list model with the names of existing deck directories.
     * @param dataModel the DefaultListModel to populate
     * @param rootFile the directory to scan for deck directories
     */
    private void fillDataModel(DefaultListModel<String> dataModel, File rootFile) {
        File[] files = rootFile.listFiles();
        if (files != null) {
            Arrays.sort(files);
            for (File file : files) {
                dataModel.addElement(file.getName());
            }
        }
    }

    /**
     * Recursively deletes a directory and all its contents.
     * @param directoryToBeDeleted the directory to delete
     * @return true if the directory was successfully deleted, false otherwise
     */
    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    /**
     * Handles button click events.
     * @param event The action event.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == folderAddButton || event.getSource() == folderTextField) {
            // If the user clicks the add folder button or presses enter in the text field, create a new deck
            addFolder();
        } else if (event.getSource() == selectButton) {
            // If the user clicks the select deck button, update the selected deck in various components
            selectDeck = dataList.getSelectedValue();
            this.setSelectDeck(selectDeck); // Update the selected deck
            AddCardPanel addCardPanel = AddCardPanel.getInstance(); // Get instance of AddCardPanel
            addCardPanel.setFolderName(selectDeck); // Set folder name
            CardContent cardContent = CardContent.getInstance(); // Get instance of CardContent
            cardContent.setDeckName(selectDeck); // Use selectDeck instead of cardTitle to set the deck name
            HeaderMainWindow headerMainWindow = HeaderMainWindow.getInstance(); // Get instance of HeaderMainWindow
            headerMainWindow.setDeckName(selectDeck); // Set the deck name

            // Get instance of SideMenuOptions and set mode and deck name
            SideMenuOptions sideMenuOptions = SideMenuOptions.getInstance();
            sideMenuOptions.setSelectMode("Menu");
            sideMenuOptions.setDeckName(selectDeck);

            // Notify all DeckSelectionListener objects about the selection
            for (DeckSelectionListener listener : sideMenuOptions.getListeners()) {
                listener.deckSelected();
            }
        } else if (event.getSource() == deleteFolderButton) {
            // If the user clicks the delete folder button, delete the selected folder
            String selectedName = dataList.getSelectedValue();
            if (selectedName != null) {
                File file = new File(rootFile, selectedName);
                // Try to delete the directory and update the data model if successful
                if (deleteDirectory(file)) {
                    dataModel.removeElement(selectedName);
                } else {
                    // Show a dialog if the card deck could not be deleted
                    JOptionPane.showMessageDialog(this, "The Card deck could not be deleted.");
                }
            }
        }
    }

}


