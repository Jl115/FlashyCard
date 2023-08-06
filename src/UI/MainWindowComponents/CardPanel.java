package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private static CardPanel instance;

    private CardPanel() {
        CardContent cardContent = CardContent.getInstance();
        this.setSize(200,200);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));


        this.add(cardContent, BorderLayout.CENTER);

    }

    public static CardPanel getInstance() {
        if (instance == null) {
            instance = new CardPanel();
        }
        return instance;
    }
}
