package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private static RightPanel instance;


    private RightPanel() {
        this.setLayout(new GridBagLayout());

        JButton backButton = new JButton("→");

        this.add(backButton);


    }

    public static RightPanel getInstance() {
        if (instance == null) {
            instance = new RightPanel();
        }
        return instance;
    }

    //TODO Adding Function for The next Button
}
