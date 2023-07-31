package FolderController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class FileController implements SetDeckName {
    private static FileController instance;
    private File decksStructure;
    private String deckName;

    private FileController(String deckName) {
        this.deckName = deckName;
        File rootFolder = new File("./flashyCard_DB/" + deckName);
        this.decksStructure = rootFolder;
        printStructure(traverseFolder(this.decksStructure));
    }

    @Override
    public void setDeckName(String folderName) {
        this.deckName = folderName;
        File rootFolder = new File("./flashyCard_DB/" + this.deckName);
        this.decksStructure = rootFolder;
    }

    public static FileController getInstance(String deckName) {
        instance = new FileController(deckName);
        return instance;
    }

    // Eine rekursive Methode, die durch alle Verzeichnisse läuft und die Dateien auflistet.
    public Card[] traverseFolder(File currentFolder) {
        List<Card> structure = new ArrayList<>();
        if (currentFolder.isDirectory()) {
            File[] cardFolders = currentFolder.listFiles(File::isDirectory);
            if (cardFolders != null) {
                for (File cardFolder : cardFolders) {
                    String front = null, right = null, wrong1 = null, wrong2 = null;
                    File[] frontBackFolders = cardFolder.listFiles(File::isDirectory);
                    if (frontBackFolders != null) {
                        for (File frontBackFolder : frontBackFolders) {
                            File[] files = frontBackFolder.listFiles();
                            if (files != null) {
                                for (File file : files) {
                                    String content = readFileToString(file.getPath());
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
                            }
                        }
                        structure.add(new Card(cardFolder.getName(), front, right, wrong1, wrong2));
                    }
                }
            }
        }
        return structure.toArray(new Card[0]);
    }

    // Hilfsmethode, um den Inhalt einer Datei in einen String zu lesen
    public static String readFileToString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Eine Methode, die die Ordnerstruktur zurückgibt.
    public Card[] getDecksStructure() {
        return traverseFolder(this.decksStructure);
    }

    // Methode zum Drucken der Ordnerstruktur
    public void printStructure(Card[] structure) {
        for (Card card : structure) {
            System.out.println("Card Name: " + card.cardName);
            System.out.println("\tFront: " + card.front);
            System.out.println("\tRight: " + card.right);
            System.out.println("\tWrong1: " + card.wrong1);
            System.out.println("\tWrong2: " + card.wrong2);
        }
    }
}
