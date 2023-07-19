package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class MainContent extends JPanel {
    private static MainContent instance;

    private MainContent() {
        LeftPanel leftPanel = LeftPanel.getInstance();
        RightPanel rightPanel = RightPanel.getInstance();
        CardPanel cardPanel = CardPanel.getInstance();

        this.setLayout(new BorderLayout());

        this.add(leftPanel, BorderLayout.LINE_START);
        this.add(rightPanel, BorderLayout.LINE_END);
        this.add(cardPanel, BorderLayout.CENTER);

    }

    public static MainContent getInstance() {
        if (instance == null) {
            instance = new MainContent();
        }
        return instance;
    }
}
