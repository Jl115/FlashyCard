package FolderController;

import java.io.IOException;

/**
 * FolderController interface provides a contract for managing folder operations.
 * Any class implementing this interface must provide an implementation for the addFolder method.
 */
public interface FolderController {

     /**
      * Adds a new folder, potentially involving file operations.
      * Implementing classes are responsible for providing the logic for adding the folder.
      *
      * @throws IOException if an I/O error occurs during the operation
      */
     void addFolder() throws IOException;
}
