package UI.MainWindowComponents;

import FolderController.Card;

import javax.swing.*;

public class CardFront extends JTextArea {
    private static CardFront instance;

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
