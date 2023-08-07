package FolderController;

/**
 * SetDeckName interface provides a contract for setting the name of a deck.
 * Any class implementing this interface must provide an implementation for the setDeckName method.
 */
public interface SetDeckName {

    /**
     * Sets the name of a deck to the specified folder name.
     *
     * @param folderName the name to be assigned to the deck
     */
    void setDeckName(String folderName);
}
