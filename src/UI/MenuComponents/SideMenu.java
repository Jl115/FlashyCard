package UI.MenuComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Class that represents the side menu of the application.
 */
public class SideMenu extends JPanel {
    // Singleton instance of SideMenu
    private static SideMenu instance;

    /**
     * Private constructor to initialize the SideMenu panel.
     */
    private SideMenu() {
        // Retrieve the singleton instance of SideMenuOptions
        SideMenuOptions sideMenuOptions = SideMenuOptions.getInstance();

        // Set layout manager for this panel
        this.setLayout(new BorderLayout());

        // Set size of the panel
        this.setSize(95, 152);

        // Add border to this panel
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        // Create a title label and set its properties
        JLabel title = new JLabel("Flashy Card");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18));
        title.setHorizontalAlignment(JLabel.CENTER);

        // Add title to the start (top) of this panel
        this.add(title, BorderLayout.PAGE_START);

        // Add SideMenuOptions instance to the center of this panel
        this.add(sideMenuOptions, BorderLayout.CENTER);
    }

    /**
     * Returns the singleton instance of SideMenu. If it does not exist, it creates one.
     *
     * @return The single instance of SideMenu
     */
    public static SideMenu getInstance() {
        // If instance doesn't exist, create one
        if (instance == null){
            instance = new SideMenu();
        }
        // Return the single instance of SideMenu
        return instance;
    }
}
