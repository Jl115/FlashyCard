package UI.MenuComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates a side menu panel with options for the application.
 * Implements ActionListener to handle action events on its components.
 */
public class SideMenuOptions extends JPanel implements ActionListener {
    // Singleton instance of SideMenuOptions
    private static SideMenuOptions instance;

    // Mode indicating current selected option in the menu
    private String selectMode = "Menu";

    // Select Deck button
    private JButton selectDeckButton = new JButton("Select Deck");
    private  JButton exitButton = new JButton("Exit FlashyCard");

    // List to hold the listeners for deck selection
    private List<DeckSelectionListener> listeners = new ArrayList<>();

    /**
     * Private constructor to initialize the SideMenuOptions panel.
     */
    private SideMenuOptions() {
        // Set layout manager
        this.setLayout(new GridBagLayout());


        // Set size of the panel
        this.setSize(95, 152);

        // Create constraints for components
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // Specify that components should fill the space both horizontally
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        // Add padding around components
        gridBagConstraints.insets = new Insets(30,0,30,0);

        // Add action listener to select deck button and add it to the panel
        selectDeckButton.addActionListener(this);
        gridBagConstraints.gridx = 0; // Specify the column in grid for button
        gridBagConstraints.gridy = 0; // Specify the row in grid for button
        gridBagConstraints.gridheight = 1; // Specify the height in grid terms
        gridBagConstraints.gridwidth = 1; // Specify the width in grid terms
        this.add(selectDeckButton, gridBagConstraints);

        // Create 'Add Card' button and add it to the panel
        JButton addCardButton = new JButton("Add Card");
        addCardButton.addActionListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(addCardButton, gridBagConstraints);

        // Create 'Exit FlashyCard' button and add it to the panel

        exitButton.addActionListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(exitButton, gridBagConstraints);
    }

    /**
     * Returns the singleton instance of SideMenuOptions. If it does not exist, it creates one.
     *
     * @return The single instance of SideMenuOptions
     */
    public static SideMenuOptions getInstance() {
        // If instance doesn't exist, create one
        if (instance == null) {
            instance = new SideMenuOptions();
        }
        // Return the single instance of SideMenuOptions
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // If the select deck button was clicked
        if (event.getSource() == this.selectDeckButton) {
            // Update select mode
            setSelectMode("SelectDeck");
            // Notify all listeners that a deck has been selected
            for (DeckSelectionListener listener : listeners) {
                listener.deckSelected();
            }
        }else if(event.getSource() == this.exitButton) {
            System.exit(0);
        }
    }

    /**
     * Adds a DeckSelectionListener to this SideMenuOptions.
     * @param listener the DeckSelectionListener to add
     */
    public void addDeckSelectionListener(DeckSelectionListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Returns the list of DeckSelectionListeners.
     * @return the list of DeckSelectionListeners
     */
    public List<DeckSelectionListener> getListeners() {
        return this.listeners;
    }

    /**
     * Returns the current select mode of this SideMenuOptions.
     * @return the current select mode
     */
    public String getSelectMode() {
        return selectMode;
    }

    /**
     * Sets the select mode of this SideMenuOptions.
     * @param selectMode the select mode to set
     */
    public void setSelectMode(String selectMode) {
        this.selectMode = selectMode;
    }


}
