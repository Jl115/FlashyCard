package UI;

import UI.MenuComponents.DeckSelectionListener;
import UI.MenuComponents.SideMenuOptions;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

/**
 * This panel allows users to select, create, and delete "decks", represented as directories within a specified path.
 */
public class SelectDeckPanel extends JPanel implements ActionListener {
    // Singleton instance of the SelectDeckPanel
    private static SelectDeckPanel instance;

    // Name of the folder to be created or deleted
    protected String folderName;
    private static String selectDeck;

    // Text field for the user to input the name of the deck
    private JTextField folderTextField = new JTextField();
    private JButton folderAddButton = new JButton();
    private JButton deleteFolderButton = new JButton();

    private JButton selectButton = new JButton();

    // Accessing relative path of the folder for the decks
    private File rootFile = new File("./database");
    private DefaultListModel<String> dataModel = new DefaultListModel<>();

    private JList<String> dataList = new JList<>(this.dataModel);

    /**
     * Private constructor for Singleton pattern.
     */
    private SelectDeckPanel() {
        this.setLayout(new BorderLayout());

        // Populate the list model with existing decks
        fillDataModel(dataModel, rootFile);

        // Create a list for displaying deck names

        dataList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // Custom rendering for the list cells
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

        // Add a scroll pane to the panel for the list
        this.add(new JScrollPane(dataList), BorderLayout.CENTER);

        // Set the action for the text field (used for folder name input)
        this.folderTextField.addActionListener(e -> addFolder());



        folderAddButton.setText("Create Deck");
        folderAddButton.addActionListener(this);

        // Creates a Button for deleting an existing deck

        deleteFolderButton.setText("Delete Deck");
        deleteFolderButton.addActionListener(this);

        selectButton.setText("Select Deck");
        selectButton.addActionListener(this);

        // Footer panel for folder text field and buttons
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

        // Check if the list of decks is empty
        if (dataModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "U need to create a Card deck.");
        }
    }

    /**
     * Returns the instance of SelectDeckPanel.
     * @return the SelectDeckPanel instance
     */
    public static SelectDeckPanel getInstance() {
        if (instance == null) {
            instance = new SelectDeckPanel();
        }
        return instance;
    }

    public static String getSelectDeck() {
        return selectDeck;
    }

    public void setSelectDeck(String selectDeck) {
        this.selectDeck = selectDeck;
    }

    /**
     * Creates a new deck directory with the name entered in the text field.
     */
    private  void addFolder() {
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
     * Fills the list model with the names of existing deck directories Alphabetic sorted.
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

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == folderAddButton || event.getSource() == folderTextField) {
            addFolder();
        }else if (event.getSource() == selectButton ) {
            selectDeck = dataList.getSelectedValue();
            setSelectDeck(selectDeck);
            SideMenuOptions sideMenuOptions = SideMenuOptions.getInstance();
            sideMenuOptions.setSelectMode("Menu");
            for (DeckSelectionListener listener : sideMenuOptions.getListeners()) {  // assuming you have a getter for listeners
                listener.deckSelected();
            }
        }else if (event.getSource() == deleteFolderButton ) {
            String selectedName = dataList.getSelectedValue();
            if (selectedName != null) {
                File file = new File(rootFile, selectedName);
                if (file.delete()) {
                    dataModel.removeElement(selectedName);
                }else {
                    JOptionPane.showMessageDialog(this, "The Card deck could not be deleted.");
                }
            }
        }
    }
}
