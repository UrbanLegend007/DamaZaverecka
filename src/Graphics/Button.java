package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * A custom JButton class that allows for easy creation of buttons with specific appearance and behavior.
 */
public class Button extends JButton implements MouseListener {

    Color color;
    Font font = new Font("Times New Roman", Font.PLAIN, 60);
    public Button(int x, int y, int width, int height, String title, Color color){

        this.color = color;

        this.setOpaque(true);
        this.setBounds(x, y, width, height);
        this.setLocation(x, y);
        this.setPreferredSize(new Dimension(width, height));
        this.setText(title);
        this.setFont(font);
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        this.addMouseListener(this);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    /**
     * Changes the button's foreground color to the specified color when the mouse enters.
     */
    public void mouseEntered(MouseEvent e) {
        this.setForeground(color);
    }

    @Override
    /**
     * Changes the button's foreground color back to white when the mouse exits.
     */
    public void mouseExited(MouseEvent e) {
        this.setForeground(Color.white);
    }
}
