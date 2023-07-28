package UI.MenuComponents;

import javax.swing.*;

public class MenuPanel extends JPanel{
    private static MenuPanel instance;

    private MenuPanel() {
        JLabel menuLabel = new JLabel("Menu");
        this.add(menuLabel);
    }

    public static MenuPanel getInstance() {
        if (instance == null) {
            instance = new MenuPanel();
        }
        return instance;
    }
}
