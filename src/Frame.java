import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

//    private static final int FRAME_WIDTH = 850, FRAME_HEIGHT = 850;

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            repaint();
        }
    });

    public Frame() {
        BoardPanel boardPanel = new BoardPanel();
        this.add(boardPanel);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        timer.start();

        this.setVisible(true);
    }
}
