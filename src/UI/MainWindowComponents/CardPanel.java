package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private static CardPanel instance;

    private CardPanel() {
        CardPanelFooter cardPanelFooter = CardPanelFooter.getInstance();
        CardContent cardContent = CardContent.getInstance();
        this.setSize(200,200);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel cardLabel = new JLabel("Front");
        cardLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(cardLabel, BorderLayout.PAGE_START);
        this.add(cardPanelFooter, BorderLayout.PAGE_END);
        this.add(cardContent, BorderLayout.CENTER);

    }

    public static CardPanel getInstance() {
        if (instance == null) {
            instance = new CardPanel();
        }
        return instance;
    }
}
