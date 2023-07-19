package UI;

import UI.MainWindowComponents.CardPanel;
import UI.MainWindowComponents.FooterMainWindow;
import UI.MainWindowComponents.HeaderMainWindow;
import UI.MainWindowComponents.MainContent;
import UI.MenuComponents.SideMenu;
import UI.MenuComponents.SideMenuOptions;
import UI.MenuComponents.DeckSelectionListener;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JPanel implements DeckSelectionListener {
    private static MainWindow instance;



    private HeaderMainWindow headerMainWindow = HeaderMainWindow.getInstance();
    private FooterMainWindow footerMainWindow = FooterMainWindow.getInstance();
    private SelectDeckPanel selectDeckPanel = SelectDeckPanel.getInstance();
    private MainContent mainContent = MainContent.getInstance();
    private SideMenuOptions sideMenuOptions = SideMenuOptions.getInstance();



    private String setMode = "Menu";

    private MainWindow() {
        this.setLayout(new BorderLayout());
        this.setSize(200, 200);

        sideMenuOptions.addDeckSelectionListener(this);
        deckSelected();
    }

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    @Override
    public void deckSelected() {
        String playMode = sideMenuOptions.getSelectMode();
        if (playMode.equals("Menu")) {
            this.add(mainContent, BorderLayout.CENTER);
            this.add(footerMainWindow, BorderLayout.PAGE_END);
            this.add(headerMainWindow, BorderLayout.PAGE_START);
            this.remove(selectDeckPanel);
        } else if (playMode.equals("SelectDeck")){
            this.add(selectDeckPanel, BorderLayout.CENTER);
            this.remove(footerMainWindow);
            this.remove(headerMainWindow);
            this.remove(mainContent);
        }
        this.revalidate();
        this.repaint();
    }
}
