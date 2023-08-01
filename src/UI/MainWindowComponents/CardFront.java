package UI.MainWindowComponents;

import FolderController.Card;

import javax.swing.*;
import java.util.ArrayList;

public class CardFront extends JTextArea {
    private static CardFront instance;
    //TODO Create ArrayList for storing then saving them!!!
    ArrayList<ArrayList<String>> card = new ArrayList<ArrayList<String >>();

    public CardFront() {
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setEditable(false);


    }

    public static CardFront getInstance() {
        if (instance == null) {
            instance = new CardFront();
        }
        return instance;
    }

    // Methode zum Setzen des Textinhalts für die Karte
    public void setCard(Card card) {
        this.setText(card.getFront());
    }
}
