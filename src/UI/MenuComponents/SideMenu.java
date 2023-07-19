package UI.MenuComponents;

import javax.swing.*;
import java.awt.*;

public class SideMenu extends JPanel {
    private static SideMenu instance;
    private SideMenu() {
        SideMenuOptions sideMenuOptions = SideMenuOptions.getInstance();
        this.setLayout(new BorderLayout());
        this.setSize(95, 152);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        JLabel title = new JLabel("Flashy Card");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18));
        title.setHorizontalAlignment(JLabel.CENTER);





        this.add(title, BorderLayout.PAGE_START);
        this.add(sideMenuOptions, BorderLayout.CENTER);
    }

    /**
     * Singleton
     * @return the SideMenu
     */
    public static SideMenu getInstace() {
        if (instance == null){
            instance = new SideMenu();
        }
        return instance;
    }
}
