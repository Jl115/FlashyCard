package UI.CustomComponents;

import javax.swing.*;

/**
 * CustomTextArea is a specialized JTextArea with predefined settings.
 * The text area is set to be non-editable, non-opaque, and supports line wrapping at word boundaries.
 */
public class CustomTextArea extends JTextArea {

    /**
     * Constructor for CustomTextArea class.
     * Initializes the text area with the custom settings.
     */
    public CustomTextArea() {
        setEditable(false);       // Disable editing of the text within the area.
        setOpaque(false);         // Make the text area non-opaque.
        setLineWrap(true);        // Enable line wrapping.
        setWrapStyleWord(true);   // Set the line wrap to occur at word boundaries.
    }
}
