package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Label extends JLabel implements MouseListener {

    /**
     * Creates variable label.
     */

    private int size = 70;
    private Color color;
    private Color foregroundColor;

    public Label(Color foregroundColor, Color color, int x, int y, int width, int height, int size) {
        addMouseListener(this);
        this.size = size;
        this.setFont(new Font("Times New Roman", Font.PLAIN, size));
        this.color = color;
        this.foregroundColor = foregroundColor;
        this.setForeground(foregroundColor);
        this.setBounds(x, y, width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Changes the font size when mouse is pressed and released over the label.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        size += 2;
        this.setFont(new Font("Times New Roman", Font.PLAIN, size));
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        size -= 2;
        this.setFont(new Font("Times New Roman", Font.PLAIN, size));
    }

    /**
     * Changes the text color and changes the font size when mouse enters or exits the label.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(color);
        size += 1;
        this.setFont(new Font("Times New Roman", Font.PLAIN, size));
    }
    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(foregroundColor);
        size -= 1;
        this.setFont(new Font("Times New Roman", Font.PLAIN, size));
    }
}
