package UI.MainWindowComponents;

import javax.swing.*;
import java.awt.*;

public class CardFront extends JTextArea {
    private static CardFront instance;

    public CardFront() {
        String testData = "Oft ist es sinnvoll ein JTextArea einem JScrollPane hinzuzufügen, damit man den eingegebenen Text auch scrollen kann. Dies ist dann wichtig, wenn die Anzahl der eingegebenen Zeilen die Anzahl der sichtbaren Zeilen übersteigt. Standardmäßig werden, wenn man die GUI-Entwicklungstools der Entwicklungsumgebungen benutzt (z.B. NetBeans) oft die entsprechenden Komponenten automatisch hinzugefügt, so dass man hier evtl. nur einige Einstellungen in den Eigenschaften vornehmen muss.";
        this.setText(testData);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setEditable(false);
    }

    public static CardFront getInstance() {
        if (instance == null) {
            instance = new CardFront();
        }
        return instance;
    }
}
