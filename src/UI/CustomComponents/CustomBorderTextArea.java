package UI.CustomComponents;

import javax.swing.*;
import java.awt.*;

/**
 * CustomBorderTextArea is a specialized JTextArea with predefined settings and a custom border.
 * The text area is set to be non-editable, non-opaque, and supports line wrapping at word boundaries.
 * A light gray line border is also added to this text area.
 */
public class CustomBorderTextArea extends JTextArea {

    /**
     * Constructor for CustomBorderTextArea class.
     * Initializes the text area with the custom settings and border.
     */
    public CustomBorderTextArea() {
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Add a light gray line border with thickness 1.
        setEditable(false);       // Disable editing of the text within the area.
        setOpaque(false);         // Make the text area non-opaque.
        setLineWrap(true);        // Enable line wrapping.
        setWrapStyleWord(true);   // Set the line wrap to occur at word boundaries.
    }
}
