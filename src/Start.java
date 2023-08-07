import UI.Menu;

public class Start {

    // Constructor initializes the Menu
    Start() {
        Menu menu = Menu.getInstance();
        menu.setVisible(true); // Makes the Menu visible
    }

    // Main method, the entry point of the application
    public static void main(String[] args) {
        new Start(); // Creates an instance of Start, which launches the menu
    }
}
