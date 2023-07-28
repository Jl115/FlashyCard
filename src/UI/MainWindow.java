package UI;

import UI.MainWindowComponents.FooterMainWindow;
import UI.MainWindowComponents.HeaderMainWindow;
import UI.MainWindowComponents.MainContent;
import UI.MenuComponents.MenuPanel;
import UI.MenuComponents.SideMenuOptions;
import UI.MenuComponents.DeckSelectionListener;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JPanel implements DeckSelectionListener {
    private static MainWindow instance;



    private final HeaderMainWindow headerMainWindow = HeaderMainWindow.getInstance();
    private final FooterMainWindow footerMainWindow = FooterMainWindow.getInstance();
    private final SelectDeckPanel selectDeckPanel = SelectDeckPanel.getInstance();
    private final MainContent mainContent = MainContent.getInstance();
    private final SideMenuOptions sideMenuOptions = SideMenuOptions.getInstance();
    private final AddCardPanel addCardPanel = AddCardPanel.getInstance();

    private final MenuPanel menuPanel = MenuPanel.getInstance();


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
        switch (playMode) {
            case "Play" -> {
                this.add(mainContent, BorderLayout.CENTER);
                this.add(footerMainWindow, BorderLayout.PAGE_END);
                this.add(headerMainWindow, BorderLayout.PAGE_START);
                this.remove(selectDeckPanel);
                this.remove(addCardPanel);
                this.remove(menuPanel);
            }
            case "SelectDeck" -> {
                this.add(selectDeckPanel, BorderLayout.CENTER);
                this.remove(footerMainWindow);
                this.remove(headerMainWindow);
                this.remove(mainContent);
                this.remove(menuPanel);
                this.remove(addCardPanel);
            }
            case "AddCard" -> {
                this.add(addCardPanel, BorderLayout.CENTER);
                this.remove(selectDeckPanel);
                this.remove(footerMainWindow);
                this.remove(headerMainWindow);
                this.remove(mainContent);
                this.remove(menuPanel);
            }
            case "Menu" -> {
                this.add(menuPanel, BorderLayout.CENTER);
                this.remove(selectDeckPanel);
                this.remove(footerMainWindow);
                this.remove(headerMainWindow);
                this.remove(mainContent);
                this.remove(addCardPanel);
            }
        }
            this.revalidate();
            this.repaint();
        }

}
