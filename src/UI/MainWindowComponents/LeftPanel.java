package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private static LeftPanel instance;


    private LeftPanel() {
        this.setLayout(new GridBagLayout());

        JButton backButton = new JButton("←");

        this.add(backButton);


    }

    public static LeftPanel getInstance() {
        if (instance == null) {
            instance = new LeftPanel();
        }
        return instance;
    }

    //TODO Adding the Button Function for the Back Panel
}
