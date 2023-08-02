package UI.CardComponents;

import javax.swing.*;
import java.awt.*;

public class CustomTextArea extends JTextArea {
    public CustomTextArea() {
        setEditable(false);
        setOpaque(false);
        setLineWrap(true);
        setWrapStyleWord(true);
    }
}
