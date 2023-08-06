package UI.CustomComponents;

import javax.swing.*;

public class CustomTextArea extends JTextArea {
    public CustomTextArea() {
        setEditable(false);
        setOpaque(false);
        setLineWrap(true);
        setWrapStyleWord(true);
    }
}
