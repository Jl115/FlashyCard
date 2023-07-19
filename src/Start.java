import UI.Menu;

public class Start {

     Start() {
        Menu menu = Menu.getInstance();
        menu.setVisible(true);
    }
    public static void main(String[] args) {
        new Start();
    }
}