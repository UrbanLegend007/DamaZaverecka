package Graphics;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code TextArea} class is a customized, non-editable {@link JTextArea} component
 * used to display read-only text with a specific visual style.
 * <p>
 * It is typically used for displaying game guides, rules, or informational text
 * within the Checkers game UI.
 */
public class TextArea extends JTextArea {

    /**
     * Constructs a new {@code TextArea} with the given text content.
     * <p>
     * The text area is styled with:
     * <ul>
     *     <li>Non-editable text</li>
     *     <li>Line wrapping enabled</li>
     *     <li>Word-based wrap style</li>
     *     <li>Serif font, size 20</li>
     *     <li>Black background and orange text color</li>
     * </ul>
     *
     * @param text the initial text to display in the text area
     */
    public TextArea(String text) {
        super();
        this.setText(text);                // Set initial content
        this.setEditable(false);          // Make text area read-only
        this.setLineWrap(true);           // Enable automatic line wrapping
        this.setWrapStyleWord(true);      // Wrap lines at word boundaries
        this.setFont(new Font("Serif", Font.PLAIN, 20));  // Set font style
        this.setBackground(Color.BLACK);  // Set background color
        this.setForeground(Color.ORANGE); // Set text color
    }
}
