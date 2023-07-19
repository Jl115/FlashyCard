package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;


public class CardContent extends JPanel  {
    private static CardContent instance;

    private CardContent() {
        CardFront cardFront = CardFront.getInstance();
        JLabel title = new JLabel("Card Title");
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
}
