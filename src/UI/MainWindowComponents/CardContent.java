package UI.MainWindowComponents;

import FolderController.Card;
import FolderController.SetDeckName;
import UI.SelectDeckPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static FolderController.CardFolderReader.addFolderStructure;

public class CardContent extends JPanel implements SetDeckName {
    private static CardContent instance;
    private String deckName;
    private File folder;

    private JLabel nameLabel;
    private JTextArea frontTextArea;
    private JLabel rightLabel;
    private JLabel wrong1Label;
    private JLabel wrong2Label;
    private JPanel namePanel;
    private JPanel frontCardPanel;
    private JLabel frontCardLabel;
    private Card[] cards;
    private int currentIndex = 1;

    private CardContent() {
        SelectDeckPanel selectDeckPanel = SelectDeckPanel.getInstance();
        deckName = selectDeckPanel.getSelectDeck();
        setDeckName(deckName);

        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        this.setLayout(new BorderLayout());

        JPanel cardPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        cardPanel.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();

        namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        nameLabel = new JLabel();
        namePanel.add(nameLabel, BorderLayout.PAGE_START);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.ITALIC, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        frontTextArea = new JTextArea();
        frontTextArea.setEditable(false);
        frontTextArea.setOpaque(false);
        frontTextArea.setLineWrap(true);
        frontTextArea.setWrapStyleWord(true);
        frontCardPanel = new JPanel();
        frontCardLabel = new JLabel();
        frontCardPanel.setLayout(new BorderLayout());
        frontCardPanel.add(frontCardLabel, BorderLayout.PAGE_START);
        frontCardPanel.add(frontTextArea, BorderLayout.CENTER);

        rightLabel = new JLabel();
        wrong1Label = new JLabel();
        wrong2Label = new JLabel();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        cardPanel.add(namePanel, constraints);

        constraints.gridy = 1;
        constraints.weighty = 2; // Doppelter Platz in der Höhe
        constraints.fill = GridBagConstraints.BOTH;
        cardPanel.add(frontCardPanel, constraints);

        constraints.gridy = 2;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        cardPanel.add(rightLabel, constraints);

        constraints.gridy = 3;
        cardPanel.add(wrong1Label, constraints);

        constraints.gridy = 4;
        cardPanel.add(wrong2Label, constraints);

        this.add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        previousButton.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                showCard();
            }
        });

        nextButton.addActionListener(e -> {
            if (currentIndex < cards.length - 1) {
                currentIndex++;
                showCard();
            }
        });

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }

    public static CardContent getInstance() {
        if (instance == null) {
            instance = new CardContent();
        }
        return instance;
    }

    @Override
    public void setDeckName(String folderName) {
        deckName = folderName;
        folder = new File("./flashyCard_DB/" + folderName);
        cards = addFolderStructure(folder);
        currentIndex = 0;
        showCard();
        this.revalidate();
        this.repaint();
    }

    private void showCard() {
        if (cards != null && currentIndex < cards.length) {
            Card card = cards[currentIndex];
            nameLabel.setText(card.getName());
            frontTextArea.setText(card.getFront());
            rightLabel.setText(card.getRight());
            wrong1Label.setText(card.getWrong1());
            wrong2Label.setText(card.getWrong2());
            frontCardLabel.setText("Question of the Card:");
            frontCardLabel.setFont(frontCardLabel.getFont().deriveFont(Font.BOLD, 14));
            frontCardLabel.setHorizontalAlignment(JLabel.LEFT);
            frontCardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        }
    }
}
