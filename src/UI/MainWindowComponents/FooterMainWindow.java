package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class FooterMainWindow extends JPanel {
    private static FooterMainWindow instance ;

    private FooterMainWindow() {
        this.setLayout(new FlowLayout());
        this.setSize(200, 200);

        JButton shuffleButton = new JButton("Shuffle");


        this.add(shuffleButton, BorderLayout.CENTER);

    }

    public static FooterMainWindow getInstance() {
        if (instance == null) {
            instance = new FooterMainWindow();
        }
        return instance;
    }

    //TODO adding function for the shuffleButton
}
