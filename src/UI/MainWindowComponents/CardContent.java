package UI.MainWindowComponents;

import FolderController.SetDeckName;
import UI.SelectDeckPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CardContent extends JPanel implements SetDeckName {
    private static CardContent instance;
    private String cardName;

    private String deckName;
    private File folder;
    private JLabel title; // Make it a field
    private String cardBody;

    private CardContent() {
        CardFront cardFront = CardFront.getInstance();
        title = new JLabel(); // Initialize it here

        //getting the deckName for working with the Folder
        SelectDeckPanel selectDeckPanel = SelectDeckPanel.getInstance();
        deckName = selectDeckPanel.getSelectDeck();
        if (deckName != null) {
            title.setText(deckName);
        }else {
            title.setText("No Card Selected");
            System.out.println(deckName);
        }

        title.setFont(title.getFont().deriveFont(Font.ITALIC, 16));
        title.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.PAGE_START);
        this.add(cardFront, BorderLayout.CENTER);
    }

    public static CardContent getInstance() {
        if (instance == null) {
            instance = new CardContent();
        }
        return instance;
    }




    @Override
    public void setDeckName(String folderName) {
        deckName = folderName;
        title.setText(deckName); // Update the title text
        folder = new File("./flashyCard_DB/" + folderName);
        this.revalidate();
        this.repaint(); // Ask the system to repaint the component
    }
}
