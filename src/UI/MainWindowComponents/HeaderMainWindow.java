package UI.MainWindowComponents;

import FolderController.SetDeckName;

import javax.swing.*;
import java.awt.*;


public class HeaderMainWindow extends JPanel implements SetDeckName {
    private static HeaderMainWindow instance ;
    private String deckName;
    private JLabel deckNameLabel = new JLabel();
    private Integer score = 70000000;

    private HeaderMainWindow() {
        this.setLayout(new BorderLayout());
        this.setSize(200, 200);

        deckNameLabel.setText("No Deck Selected");
        JLabel scoreLabel = new JLabel("Score: " + score.toString());

        this.add(deckNameLabel, BorderLayout.LINE_START);
        this.add(scoreLabel, BorderLayout.LINE_END);
    }

    public static HeaderMainWindow getInstance() {
        if (instance == null) {
            instance = new HeaderMainWindow();
        }
        return instance;
    }

    @Override
    public void setDeckName(String folderName) {
        deckName = folderName;
        deckNameLabel.setText(deckName); // Update the title text
        this.revalidate();
        this.repaint(); // Ask the system to repaint the component
    }
}
