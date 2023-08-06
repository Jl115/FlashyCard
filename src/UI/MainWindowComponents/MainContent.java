package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class MainContent extends JPanel {
    private static MainContent instance;

    private MainContent() {

        CardPanel cardPanel = CardPanel.getInstance();

        this.setLayout(new BorderLayout());
        this.add(cardPanel, BorderLayout.CENTER);

    }

    public static MainContent getInstance() {
        if (instance == null) {
            instance = new MainContent();
        }
        return instance;
    }
}
