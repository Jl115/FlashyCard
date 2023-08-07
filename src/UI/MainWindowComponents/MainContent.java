package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

/**
 * MainContent class extends JPanel and serves as the primary container for the main content of the application.
 * It includes the CardPanel in the center of the layout.
 * Utilizes the Singleton pattern to ensure only one instance.
 */
public class MainContent extends JPanel {
    private static MainContent instance; // Singleton instance

    private MainContent() {
        // Get the singleton instance of CardPanel
        CardPanel cardPanel = CardPanel.getInstance();

        // Set the layout and add the CardPanel to the center
        this.setLayout(new BorderLayout());
        this.add(cardPanel, BorderLayout.CENTER);
    }

    /**
     * Singleton pattern applied to get the MainContent instance.
     *
     * @return the unique instance of MainContent.
     */
    public static MainContent getInstance() {
        if (instance == null) {
            instance = new MainContent();
        }
        return instance;
    }
}
