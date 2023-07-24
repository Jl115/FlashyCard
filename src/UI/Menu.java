package UI;

import UI.MenuComponents.SideMenu;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private static Menu instance;


    //constructor
    private Menu() {
        SideMenu sideMenu = SideMenu.getInstace();
        MainWindow mainWindow = MainWindow.getInstance();

        //settings for JFrame window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(650, 520));
        this.setBounds(500, 350, 400, 600);




        this.add(sideMenu, BorderLayout.LINE_START);
        this.add(mainWindow, BorderLayout.CENTER);

    }

    /**
     * This is the getter for the Singleton menu!
     * @return the instance of Menu()
     */
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }
}
