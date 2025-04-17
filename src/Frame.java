import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    private static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 850;

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            repaint();
        }
    });

    public Frame() {
        JPanel mainPanel = new JPanel();

        this.getContentPane().add(mainPanel);

        this.pack();
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(180, 180, 180));

        timer.start();
    }
}
