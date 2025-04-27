import javax.swing.*;
import java.awt.*;

class BoardPanel extends JPanel {

    private static final int SQUARE_SIZE = 100;
    private static final int BOARD_SIZE = 8 * SQUARE_SIZE;
    Game game = new Game();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = 8;

        for (int lin = 0; lin < size; lin++) {
            for (int col = 0; col < size; col++) {
                if ((lin + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(col * SQUARE_SIZE, lin * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
        for (int lin = 1; lin <= size; lin++) {
            for (int col = 1; col <= size; col++) {
                Figurine f = game.getFigurineAt(lin,col);
                if(f != null) {
                    if(f.isWhite()) {
                        g.setColor(Color.WHITE);
                    }else{
                        g.setColor(Color.RED);
                    }
                    g.fillOval((col - 1) * SQUARE_SIZE + 10, (8 - lin) * SQUARE_SIZE + 10, SQUARE_SIZE - 20, SQUARE_SIZE - 20);

                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_SIZE, BOARD_SIZE);
    }
}
