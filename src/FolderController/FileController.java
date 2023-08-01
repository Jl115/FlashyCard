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

}
