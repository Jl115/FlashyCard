package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class CardPanelFooter extends JPanel {
    private static CardPanelFooter instance;

    private CardPanelFooter() {
        this.setLayout(new FlowLayout());

        JButton rightButton = new JButton("Right");
        JButton leftButton = new JButton("Left");
        JButton middleButton = new JButton("Middle");

        this.add(rightButton, BorderLayout.LINE_END);
        this.add(middleButton, BorderLayout.CENTER);
        this.add(leftButton, BorderLayout.LINE_START);
    }

    public static CardPanelFooter getInstance() {
        if (instance == null) {
            instance = new CardPanelFooter();
        }
        return instance;
    }

    // TODO Adding the Functions for the leftButton, mittleButton, rightButton
}
