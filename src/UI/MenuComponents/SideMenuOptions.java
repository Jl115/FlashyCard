package UI.MenuComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates a side menu panel with options for the application.
 */
public class SideMenuOptions extends JPanel implements ActionListener {
    // Singleton instance of SideMenuOptions
    private static SideMenuOptions instance;




    private String selectMode = "Menu";
    private JButton selectDeckButton = new JButton("Select Deck");

    // Create a list to hold the listeners
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

        selectDeckButton.addActionListener(this);
        gridBagConstraints.gridx = 0; // Specify the column in grid for button
        gridBagConstraints.gridy = 0; // Specify the row in grid for button
        gridBagConstraints.gridheight = 1; // Specify the height in grid terms
        gridBagConstraints.gridwidth = 1; // Specify the width in grid terms
        this.add(selectDeckButton, gridBagConstraints);

        // Create 'Add Card' button and add it to the panel
        JButton addCardButton = new JButton("Add Card");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(addCardButton, gridBagConstraints);

        // Create 'Exit FlashyCard' button and add it to the panel
        JButton exitButton = new JButton("Exit FlashyCard");
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
        if (event.getSource() == this.selectDeckButton) {
            setSelectMode("SelectDeck");
            for (DeckSelectionListener listener : listeners) {
                listener.deckSelected();
            }
        }
    }

    // Function to add a listener to this class
    public void addDeckSelectionListener(DeckSelectionListener listener) {
        this.listeners.add(listener);
    }



    public List<DeckSelectionListener> getListeners() {
        return this.listeners;
    }

    public String getSelectMode() {
        return selectMode;
    }

    public void setSelectMode(String selectMode) {
        this.selectMode = selectMode;
    }
}

