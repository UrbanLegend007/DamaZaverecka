import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BoardPanel extends JPanel {

    private static final int SQUARE_SIZE = 100;
    private static final int BOARD_SIZE = 8 * SQUARE_SIZE;
    Game game;
    private int[] selectedPosition = null;

    BoardPanel(){
        game = new Game();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int column = e.getX() / SQUARE_SIZE;
                int line = 7 - (e.getY() / SQUARE_SIZE);

                System.out.println("Kliknuto na: řádek " + (line+1) + ", sloupec " + (column+1));

                Figurine clicked = game.getFigurineAt(line, column);

                if (selectedPosition == null) {
                    if (clicked != null) {
                        selectedPosition = new int[]{line, column};
                        System.out.println("Figurka vybrána.");
                    }
                } else {
                    System.out.println("Zkusím přesunout figurku...");
                    game.moveFigurine(selectedPosition[0], selectedPosition[1], line, column);
                    selectedPosition = null;
                    repaint();
                }
            }
        });
    }

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
        int line = -1;
        for (int lin = 7; lin >= 0; lin--) {
            line++;
            for (int col = 0; col < size; col++) {
                Figurine f = game.getFigurineAt(lin,col);
                if(f != null) {
                    if(f.isWhite()) {
                        g.setColor(Color.WHITE);
                    }else{
                        g.setColor(Color.RED);
                    }
                    g.fillOval(col * SQUARE_SIZE + 10, line * SQUARE_SIZE + 10, SQUARE_SIZE - 20, SQUARE_SIZE - 20);

                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_SIZE, BOARD_SIZE);
    }
}
