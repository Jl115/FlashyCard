package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class HeaderMainWindow extends JPanel {
    private static HeaderMainWindow instance ;

    private HeaderMainWindow() {
        this.setLayout(new BorderLayout());
        this.setSize(200, 200);

        JLabel deckNameLabel = new JLabel("Deck Name");
        JLabel scoreLabel = new JLabel("Score: 0");

        this.add(deckNameLabel, BorderLayout.LINE_START);
        this.add(scoreLabel, BorderLayout.LINE_END);
    }

    public static HeaderMainWindow getInstance() {
        if (instance == null) {
            instance = new HeaderMainWindow();
        }
        return instance;
    }
}
