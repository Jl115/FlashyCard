package UI.CardComponents;

import javax.swing.*;
import java.awt.*;

public class CustomBorderTextArea extends JTextArea{
    public CustomBorderTextArea(){
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        setEditable(false);
        setOpaque(false);
        setLineWrap(true);
        setWrapStyleWord(true);
    }
}
