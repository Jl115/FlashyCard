package FolderController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileController class manages the file structure related to the decks.
 * It is implemented as a singleton, meaning only one instance of this class can be created.
 * The class implements the SetDeckName interface, allowing the deck name to be set.
 */
public class FileController implements SetDeckName {
    private static FileController instance; // Singleton instance of FileController.
    private File decksStructure;            // File representing the decks' structure.
    private String deckName;                // Name of the current deck.

    /**
     * Private constructor for FileController.
     *
     * @param deckName Name of the deck to manage.
     */
    private FileController(String deckName) {
        this.deckName = deckName;
        File rootFolder = new File("./flashyCard_DB/" + deckName);
        this.decksStructure = rootFolder;
    }

    /**
     * Implements the setDeckName method from the SetDeckName interface.
     * Sets the name of the deck and updates the decksStructure accordingly.
     *
     * @param folderName Name of the folder representing the deck.
     */
    @Override
    public void setDeckName(String folderName) {
        this.deckName = folderName;
        File rootFolder = new File("./flashyCard_DB/" + this.deckName);
        this.decksStructure = rootFolder;
    }

    /**
     * Provides access to the singleton instance of FileController, creating it if necessary.
     * This implementation overwrites the instance every time it's called, which may be unintentional.
     *
     * @param deckName Name of the deck to manage.
     * @return The singleton instance of FileController.
     */
    public static FileController getInstance(String deckName) {
        instance = new FileController(deckName);
        return instance;
    }

    public void addFile(String score)  {


    }

}
