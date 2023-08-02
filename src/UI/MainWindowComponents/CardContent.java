package UI.MainWindowComponents;

import FolderController.Card;
import FolderController.SetDeckName;
import UI.CardComponents.CustomBorderTextArea;
import UI.CardComponents.CustomTextArea;
import UI.SelectDeckPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;

import static FolderController.CardFolderReader.addFolderStructure;

public class CardContent extends JPanel implements SetDeckName {
    private static CardContent instance;
    private String deckName;
    private File folder;

    private JLabel nameLabel;
    private CustomTextArea frontTextArea;
    private CustomBorderTextArea rightTextArea = new CustomBorderTextArea();
    private CustomBorderTextArea wrong1TextArea = new CustomBorderTextArea();
    private CustomBorderTextArea wrong2TextArea = new CustomBorderTextArea();

    private JButton rightButton = new JButton();
    private JButton wrongButton = new JButton();
    private JButton wrong2Button = new JButton();
    private JPanel namePanel;
    private JPanel frontCardPanel;
    private JLabel frontCardLabel;
    private Card[] cards;
    private int currentIndex = 1;

    private CardContent() {
        SelectDeckPanel selectDeckPanel = SelectDeckPanel.getInstance();
        deckName = selectDeckPanel.getSelectDeck();
        setDeckName(deckName);

        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        this.setLayout(new BorderLayout());

        JPanel cardPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        cardPanel.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();

        namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        nameLabel = new JLabel();
        namePanel.add(nameLabel, BorderLayout.PAGE_START);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.ITALIC, 24));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        frontTextArea = new CustomTextArea();

        frontCardPanel = new JPanel();
        frontCardLabel = new JLabel();
        frontCardLabel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.LIGHT_GRAY));
        frontCardPanel.setLayout(new BorderLayout());
        frontCardPanel.add(frontCardLabel, BorderLayout.PAGE_START);
        frontCardPanel.add(frontTextArea, BorderLayout.CENTER);





        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        cardPanel.add(namePanel, constraints);

        constraints.gridy = 1;
        constraints.weighty = 2; // Doppelter Platz in der Höhe

        constraints.fill = GridBagConstraints.BOTH;
        cardPanel.add(frontCardPanel, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1; // Gewicht für die Textarea
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        cardPanel.add(rightTextArea, constraints);


        constraints.gridx = 2; // Position des Buttons
        constraints.gridy = 2;
        constraints.weightx = 0; // Gewicht für den Button, damit er nicht die ganze Breite einnimmt
        constraints.fill = GridBagConstraints.NONE;
        cardPanel.add(rightButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        cardPanel.add(wrong1TextArea, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        cardPanel.add(wrongButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        cardPanel.add(wrong2TextArea, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        cardPanel.add(wrong2Button, constraints);

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
            rightTextArea.setText(card.getRight());
            wrong1TextArea.setText(card.getWrong1());

            wrong2TextArea.setText(card.getWrong2());
            frontCardLabel.setText("Question of the Card:");
            frontCardLabel.setFont(frontCardLabel.getFont().deriveFont(Font.BOLD, 14));
            frontCardLabel.setHorizontalAlignment(JLabel.LEFT);
            frontCardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            rightButton.setText("This one");
            wrongButton.setText("No this one");
            wrong2Button.setText("Oh wait this one");
        }
    }


}
