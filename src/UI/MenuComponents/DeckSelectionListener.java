package UI.MenuComponents;

/**
 * Interface for the DeckSelectionListener.
 * Any class implementing this interface must provide an implementation for the deckSelected method,
 * which will be called when a deck is selected in the user interface.
 */
public interface DeckSelectionListener {

    /**
     * This method is called when a deck is selected.
     * Classes that implement this interface should define what should happen when a deck is selected.
     */
    void deckSelected();
}

