package UI.MenuComponents;

import UI.CustomComponents.CustomTextArea;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{
    private static MenuPanel instance;

    private MenuPanel() {
        this.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS ));

        JLabel menuLabel = new JLabel("Menu");
        JPanel introPanel = new JPanel();
        CustomTextArea descriptionTArea = new CustomTextArea();
        descriptionTArea.setText("Hi Welcome to my Flashcard Applikation  i developed this over 3 weeks please if u Notice any Bugs contact me and i will try my best to fix those same for Features :)");
        descriptionTArea.setColumns(3);
        descriptionTArea.setRows(3);

        this.add(menuLabel, BorderLayout.PAGE_START);
        this.add(descriptionTArea, BorderLayout.CENTER);

    }

    public static MenuPanel getInstance() {
        if (instance == null) {
            instance = new MenuPanel();
        }
        return instance;
    }
}
