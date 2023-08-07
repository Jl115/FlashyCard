package UI.MainWindowComponents;

import FolderController.Card;
import FolderController.SetDeckName;
import UI.CustomComponents.CustomBorderTextArea;
import UI.CustomComponents.CustomTextArea;
import UI.Panels.SelectDeckPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import static FolderController.CardFolderReader.addFolderStructure;

public class CardContent extends JPanel implements SetDeckName, ActionListener {
    private static CardContent instance;
    private String deckName;

    private final JLabel nameLabel;
    private final CustomTextArea frontTextArea;
    private final CustomBorderTextArea rightTextArea = new CustomBorderTextArea();
    private final CustomBorderTextArea wrong1TextArea = new CustomBorderTextArea();
    private final CustomBorderTextArea wrong2TextArea = new CustomBorderTextArea();
    private final HeaderMainWindow headerMainWindow = HeaderMainWindow.getInstance();

    private final JButton correctButton = new JButton();
    private final JButton wrongButton = new JButton();
    private final JButton wrong2Button = new JButton();
    private final JButton shuffleButton = new JButton();
    private final JButton previousButton = new JButton();
    private final JButton nextButton = new JButton();
    private final JPanel frontCardPanel;
    private final JLabel frontCardLabel;
    private Card[] cards;
    private int currentIndex = 0;


    private final JPanel cardPanel;
    private boolean correctButtonListenerAdded = false;
    private boolean wrongButtonListenerAdded = false;
    private boolean wrong2ButtonListenerAdded = false;

    private static class AnswerGroup {
        CustomBorderTextArea textArea;
        JButton button;

        AnswerGroup(CustomBorderTextArea textArea, JButton button) {
            this.textArea = textArea;
            this.button = button;
        }
    }

    private CardContent() {
        deckName = SelectDeckPanel.getSelectDeck();
        setDeckName(deckName);

        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        this.setLayout(new BorderLayout());

        cardPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        cardPanel.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel namePanel = new JPanel();
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

        this.add(cardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        previousButton.setText("←");
        shuffleButton.setText("Shuffle");
        nextButton.setText("→");

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        shuffleButton.addActionListener(this);

        buttonPanel.add(previousButton);
        buttonPanel.add(shuffleButton);
        buttonPanel.add(nextButton);
        this.add(buttonPanel, BorderLayout.PAGE_END);

        showCard();
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
        File folder = new File("./flashyCard_DB/" + folderName);
        cards = addFolderStructure(folder);
        currentIndex = 0;
        showCard();
        this.revalidate();
        this.repaint();
    }

    private void shuffleAnswers() {
        AnswerGroup[] answerGroups = {
                new AnswerGroup(rightTextArea, correctButton),
                new AnswerGroup(wrong1TextArea, wrongButton),
                new AnswerGroup(wrong2TextArea, wrong2Button)
        };

        // Shuffle the answers
        Random rnd = new Random();
        for (int i = answerGroups.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            AnswerGroup tempGroup = answerGroups[index];
            answerGroups[index] = answerGroups[i];
            answerGroups[i] = tempGroup;
        }

        // Remove existing answers before adding new ones
        for (AnswerGroup group : answerGroups) {
            cardPanel.remove(group.textArea);
            cardPanel.remove(group.button);
        }

        // Add the TextAreas and Buttons from the shuffled answer groups to the layout
        for (int i = 0; i < answerGroups.length; i++) {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 2 + i;
            constraints.weightx = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            cardPanel.add(answerGroups[i].textArea, constraints);

            constraints.gridx = 2;
            constraints.gridy = 2 + i;
            constraints.weightx = 0;
            constraints.fill = GridBagConstraints.NONE;
            cardPanel.add(answerGroups[i].button, constraints);
        }
    }



    private void showCard() {

        if (cards != null && currentIndex < cards.length) {
            Card card = cards[currentIndex];
            nameLabel.setText(card.getName());
            frontTextArea.setText(card.getFront());
            rightTextArea.setText(card.getRight());
            wrong1TextArea.setText(card.getWrong1());

            shuffleAnswers();

            // Füge das frontTextArea wieder zum Layout hinzu
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weighty = 2; // Doppelter Platz in der Höhe
            constraints.fill = GridBagConstraints.BOTH;
            cardPanel.add(frontCardPanel, constraints);

            wrong2TextArea.setText(card.getWrong2());
            frontCardLabel.setText("Question of the Card:");
            frontCardLabel.setFont(frontCardLabel.getFont().deriveFont(Font.BOLD, 14));
            frontCardLabel.setHorizontalAlignment(JLabel.LEFT);
            frontCardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
            correctButton.setText("I think this one");
            //Need for Separating the button action-listener
            if (!correctButtonListenerAdded) {
                correctButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent d) {

                        headerMainWindow.increaseScore(10);
                        headerMainWindow.setTimeDecrease(false);
                        currentIndex++;
                        shuffleAnswers();
                        showCard();
                    }
                });
                correctButtonListenerAdded = true;
            }


            wrongButton.setText("I think this one");

            if (!wrongButtonListenerAdded) {
                wrongButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        headerMainWindow.decreaseScore(2);
                        headerMainWindow.setTimeDecrease(true);
                        shuffleAnswers();
                    }
                });
                wrongButtonListenerAdded = true;
            }

            wrong2Button.setText("I think this one");
            if (!wrong2ButtonListenerAdded) {
                wrongButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        headerMainWindow.decreaseScore(2);
                        headerMainWindow.setTimeDecrease(true);
                        shuffleAnswers();
                    }
                });
                wrong2ButtonListenerAdded = true;
            }


            // Update the layout
            cardPanel.revalidate();
            cardPanel.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previousButton) {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = 0;
                shuffleAnswers();
            }
            showCard();
        } else if (e.getSource() == nextButton) {
            currentIndex++;
            if (currentIndex >= cards.length) {
                currentIndex = cards.length - 1;
                shuffleAnswers();
            }
            showCard();
        } else if (e.getSource() == shuffleButton) {
            //implementation of the FisherYates shuffle Algorithm
            int numberOfCards = cards.length;
            Random rnd = new Random();
            int[] cardNumber = new int[numberOfCards];
            for (int i = 0; i < numberOfCards; i++) {
                cardNumber[i] = i;
            }
            for (int i = cardNumber.length - 1; i > 0; i--) {
                int index = rnd.nextInt(i + 1);
                // Simple swap
                int a = cardNumber[index];
                cardNumber[index] = cardNumber[i];
                cardNumber[i] = a;
            }

            Card[] shuffledCard = new Card[numberOfCards];
            for (int i = 0; i < numberOfCards; i++) {
                shuffledCard[i] = cards[cardNumber[i]];
            }
            cards = shuffledCard;
            showCard();
            currentIndex = 0;
        }
    }
}
