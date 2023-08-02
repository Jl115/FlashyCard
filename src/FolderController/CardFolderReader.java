package FolderController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CardFolderReader {

    /**
     * Reads the folder structure and creates cards.
     *
     * @param currentFolder The current folder
     * @return Array of Cards
     */

    public static Card[] addFolderStructure(File currentFolder) {
        List<Card> structure = new ArrayList<>();
        if (currentFolder.isDirectory()) {
            File[] cardFolders = currentFolder.listFiles(File::isDirectory);
            if (cardFolders != null) {
                for (File cardFolder : cardFolders) {
                    System.out.println("Reading card folder: " + cardFolder.getPath());
                    String front = null, right = null, wrong1 = null, wrong2 = null;
                    File frontFolder = new File(cardFolder, "front");
                    if (frontFolder.isDirectory()) {
                        File[] files = frontFolder.listFiles();
                        if (files != null) {
                            System.out.println("Reading files from folder: " + frontFolder.getPath());
                            for (File file : files) {
                                String content = readFileToString(file.getPath());
                                System.out.println("Reading file: " + file.getName() + ", content: " + content);
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
                            System.out.println("No files in folder: " + frontFolder.getPath());
                        }
                    } else {
                        System.out.println("Not a directory: " + frontFolder.getPath());
                    }
                    structure.add(new Card(cardFolder.getName(), front, right, wrong1, wrong2));
                }
            } else {
                System.out.println("No card folders in directory: " + currentFolder.getPath());
            }
        } else {
            System.out.println("Not a directory: " + currentFolder.getPath());
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

