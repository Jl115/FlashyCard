package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

/**
 * CardPanel class extends JPanel and serves as a container for CardContent.
 * It sets the layout, size, and border for the CardContent.
 * Utilizes the Singleton pattern to ensure only one instance.
 */
public class CardPanel extends JPanel {
    private static CardPanel instance; // Singleton instance

    private CardPanel() {
        // Get the singleton instance of CardContent
        CardContent cardContent = CardContent.getInstance();

        // Set the size of this panel
        this.setSize(200,200);

        // Set the layout to BorderLayout and add the CardContent to the center
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        this.add(cardContent, BorderLayout.CENTER);
    }

    /**
     * Singleton pattern applied to get the CardPanel instance.
     *
     * @return the unique instance of CardPanel.
     */
    public static CardPanel getInstance() {
        if (instance == null) {
            instance = new CardPanel();
        }
        return instance;
    }
}
