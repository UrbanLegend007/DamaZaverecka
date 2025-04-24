import javax.swing.*;
import java.awt.*;

class BoardPanel extends JPanel {

    private static final int SQUARE_SIZE = 100;
    private static final int BOARD_SIZE = 8 * SQUARE_SIZE;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = 8;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
        g.setColor(Color.RED);
        g.fillOval(1 * SQUARE_SIZE + 10, 2 * SQUARE_SIZE + 10, SQUARE_SIZE - 20, SQUARE_SIZE - 20);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_SIZE, BOARD_SIZE);
    }
}
