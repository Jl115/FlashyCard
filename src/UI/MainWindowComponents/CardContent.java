package UI.MainWindowComponents;

import FolderController.Card;
import FolderController.SetDeckName;
import UI.SelectDeckPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CardContent extends JPanel implements SetDeckName {
    private static CardContent instance;
    private String deckName;
    private File folder;
    private JLabel title;
    private JLabel nameLabel;
    private JLabel frontLabel;
    private JLabel rightLabel;
    private JLabel wrong1Label;
    private JLabel wrong2Label;
    private Card[] cards;
    private int currentIndex = 1;

    private CardContent() {
        title = new JLabel();
        SelectDeckPanel selectDeckPanel = SelectDeckPanel.getInstance();
        deckName = selectDeckPanel.getSelectDeck();
        setDeckName(deckName);

        title.setFont(title.getFont().deriveFont(Font.ITALIC, 16));
        title.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.PAGE_START);

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(5, 1));
        nameLabel = new JLabel();
        frontLabel = new JLabel();
        rightLabel = new JLabel();
        wrong1Label = new JLabel();
        wrong2Label = new JLabel();

        cardPanel.add(nameLabel);
        cardPanel.add(frontLabel);
        cardPanel.add(rightLabel);
        cardPanel.add(wrong1Label);
        cardPanel.add(wrong2Label);

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
        if (folderName == null || folderName.isEmpty()) {
            System.out.println("Deck name is not set!"); // Debug-Ausgabe
            title.setText("No Card Selected");
            return; // Frühzeitiger Ausstieg, da der Deckname ungültig ist
        }
        deckName = folderName;
        if (deckName != null) {
            title.setText(deckName);
        } else title.setText("No Card Selected");
        folder = new File("./flashyCard_DB/" + folderName);
        System.out.println("Reading deck from folder: " + folder); // Debug-Ausgabe
        cards = traverseFolder(folder);
        currentIndex = 0; // Reset the card index
        showCard();
        this.revalidate();
        this.repaint();
    }


    private void showCard() {
        if (cards != null && currentIndex < cards.length) {
            Card card = cards[currentIndex];
            nameLabel.setText(card.getName());
            frontLabel.setText(card.getFront());
            rightLabel.setText(card.getRight());
            wrong1Label.setText(card.getWrong1());
            wrong2Label.setText(card.getWrong2());
        }
    }
    public static Card[] traverseFolder(File currentFolder) {
        List<Card> structure = new ArrayList<>();
        if (currentFolder.isDirectory()) {
            File[] cardFolders = currentFolder.listFiles(File::isDirectory);
            if (cardFolders != null) {
                for (File cardFolder : cardFolders) {
                    System.out.println("Reading card folder: " + cardFolder.getPath()); // Debug
                    String front = null, right = null, wrong1 = null, wrong2 = null;
                    File frontFolder = new File(cardFolder, "front");
                    if (frontFolder.isDirectory()) {
                        File[] files = frontFolder.listFiles();
                        if (files != null) {
                            System.out.println("Reading files from folder: " + frontFolder.getPath()); // Debug
                            for (File file : files) {
                                String content = readFileToString(file.getPath());
                                System.out.println("Reading file: " + file.getName() + ", content: " + content); // Debug
                                switch (file.getName()) {
                                    case "front.txt":
                                        front = content;
                                        break;
                                    case "right.txt":
                                        right = content;
                                        break;
                                    case "wrong1.txt":
                                        wrong1 = content;
                                        break;
                                    case "wrong2.txt":
                                        wrong2 = content;
                                        break;
                                }
                            }
                        } else {
                            System.out.println("No files in folder: " + frontFolder.getPath()); // Debug
                        }
                    } else {
                        System.out.println("Not a directory: " + frontFolder.getPath()); // Debug
                    }
                    structure.add(new Card(cardFolder.getName(), front, right, wrong1, wrong2));
                }
            } else {
                System.out.println("No card folders in directory: " + currentFolder.getPath()); // Debug
            }
        } else {
            System.out.println("Not a directory: " + currentFolder.getPath()); // Debug
        }
        return structure.toArray(new Card[0]);
    }







    private static String readFileToString(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString().trim();
    }

}
