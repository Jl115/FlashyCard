package UI;
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
    public static String selectDeck;

    // Text field for the user to input the name of the deck
    private JTextField folderTextField = new JTextField();
    private JButton folderAddButton = new JButton();
    private JButton deleteFolderButton = new JButton();
    private JButton selectButton = new JButton();

    // Accessing relative path of the folder for the decks
    private File rootFile = new File("./flashyCard_DB");
    private DefaultListModel<String> dataModel = new DefaultListModel<>();
    private JList<String> dataList = new JList<>(this.dataModel);

    private String cardTitle;

    /**
     * Private constructor for Singleton pattern.
     * Sets up the panel layout and functionality.
     */
    private SelectDeckPanel() {
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
            instance = new SelectDeckPanel();
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
        this.selectDeck = selectDeck;
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
            this.setSelectDeck(selectDeck);
            AddCardPanel addCardPanel = AddCardPanel.getInstance();
            addCardPanel.setFolderName(selectDeck);
            CardContent cardContent = CardContent.getInstance();
            cardContent.setDeckName(selectDeck); // Verwende selectDeck statt cardTitle
            HeaderMainWindow headerMainWindow = HeaderMainWindow.getInstance();
            headerMainWindow.setDeckName(selectDeck);

            SideMenuOptions sideMenuOptions = SideMenuOptions.getInstance();
            sideMenuOptions.setSelectMode("Menu");
            sideMenuOptions.setDeckName(selectDeck);
            for (DeckSelectionListener listener : sideMenuOptions.getListeners()) {  // assuming you have a getter for listeners
                listener.deckSelected();
            }
        } else if (event.getSource() == deleteFolderButton) {
            String selectedName = dataList.getSelectedValue();
            if (selectedName != null) {
                File file = new File(rootFile, selectedName);
                if (deleteDirectory(file)) {
                    dataModel.removeElement(selectedName);
                } else {
                    JOptionPane.showMessageDialog(this, "The Card deck could not be deleted.");
                }
            }
        }
    }


}