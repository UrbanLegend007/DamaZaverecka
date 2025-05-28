package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Logistics.*;

public class BoardPanel extends JPanel {

    private static final int SQUARE_SIZE = 100;
    private static final int BOARD_SIZE = 8 * SQUARE_SIZE;
    private int[] selectedPosition = null;
    private Label warningText = new Label(Color.GREEN, Color.orange, 400, 400, 300, 300,30);
    private int period = 0;
    private String changedWarning = "";
    Game game;
    Frame frame;

    public BoardPanel(Frame frame) {
        timer.start();
        this.frame = frame;
        game = new Game();
        this.add(warningText);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int column = e.getX() / SQUARE_SIZE;
                int line = 7 - (e.getY() / SQUARE_SIZE);

                Figurine clicked = game.getFigurineAt(line, column);
                System.out.println("clicked: " + clicked);

                if (selectedPosition == null) {
                    if (clicked != null) {
                        selectedPosition = new int[]{line, column};
                        System.out.println("selected position: " + selectedPosition[0] + ", " + selectedPosition[1]);
                    }
                } else {
                    game.moveFigurine(selectedPosition[0], selectedPosition[1], line, column);
                    System.out.println("moved figurine: " + line + ", " + column);
                    if(game.isNextTake()){
                        selectedPosition = new int[]{line, column};
                    } else {
                        selectedPosition = null;
                    }
                    repaint();

                    if (game.isGameOver()) {
                        String winner = game.getWinner();
                        System.out.println("Konec hry! Vyhrál: " + winner);
                        frame.showWin(winner);
                    }
                }
            }
        });
    }

    Timer timer = new Timer(1, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(game.getWarningText() != null){
                if(period == 1){
                    changedWarning = game.getWarningText();
                }
                if(!changedWarning.equals(game.getWarningText())){
                    period = 0;
                }
                period++;
                warningText.setText(game.getWarningText());
                warningText.setVisible(true);
                if(period >= 200){
                    period = 0;
                    warningText.setVisible(false);
                    game.setWarningText(null);
                }
            }
        }
    });

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
                if(!game.moves.isEmpty()){
                    int index;
                    Move m = new Move(lin, col, lin, col);
                    for (index = 0; index < game.moves.size(); index++) {
                        if(game.moves.get(index).getToX() == m.getToX() && game.moves.get(index).getToY() == m.getToY()) {
                            g.setColor(Color.GREEN);
                            g.fillOval(col * SQUARE_SIZE + 25, line * SQUARE_SIZE + 25, SQUARE_SIZE - 50, SQUARE_SIZE - 50);
                        }
                        if(game.moves.get(index).getFromX() == m.getFromX() && game.moves.get(index).getFromY() == m.getFromY()) {
                            g.setColor(Color.GREEN);
                            g.fillOval(col * SQUARE_SIZE + 8, line * SQUARE_SIZE + 8, SQUARE_SIZE - 16, SQUARE_SIZE - 16);
                        }
                    }
                }
                Figurine f = game.getFigurineAt(lin,col);
                if(f != null) {
                    Color c;
                    if(f.isWhite()) {
                        c = Color.WHITE;
                        g.setColor(c);
                    }else{
                        c = Color.RED;
                        g.setColor(c);
                    }
                    if(f.isQueen()){
                        g.setColor(Color.YELLOW);
                        g.fillOval(col * SQUARE_SIZE + 10, line * SQUARE_SIZE + 10, SQUARE_SIZE - 20, SQUARE_SIZE - 20);
                        g.setColor(c);
                        g.fillOval(col * SQUARE_SIZE + 15, line * SQUARE_SIZE + 15, SQUARE_SIZE - 30, SQUARE_SIZE - 30);
                        g.setColor(Color.YELLOW);
                        g.fillOval(col * SQUARE_SIZE + 30, line * SQUARE_SIZE + 30, SQUARE_SIZE - 60, SQUARE_SIZE - 60);
                    } else {
                        g.fillOval(col * SQUARE_SIZE + 10, line * SQUARE_SIZE + 10, SQUARE_SIZE - 20, SQUARE_SIZE - 20);
                    }
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_SIZE, BOARD_SIZE);
    }
}
