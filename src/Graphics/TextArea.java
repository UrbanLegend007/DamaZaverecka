package Graphics;

import javax.swing.*;
import java.awt.*;

public class TextArea extends JTextArea {
    public TextArea(String text) {
        super();
        this.setText(text);
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setFont(new Font("Serif", Font.PLAIN, 20));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.ORANGE);
    }
}
