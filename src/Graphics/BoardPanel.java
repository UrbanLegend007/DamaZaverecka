package Graphics;

import Logistics.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The {@code BoardPanel} class represents the main UI panel for the checkers game.
 * It displays the game board, handles user interaction (mouse clicks), and shows UI controls
 * such as undo and menu buttons as well as warning messages.
 */
public class BoardPanel extends JPanel {

    private static final int SQUARE_SIZE = 100;
    private static final int BOARD_SIZE = 8 * SQUARE_SIZE;
    private Game game = new Game();
    private Frame frame;
    private Label warningText;
    private int[] selectedPosition = null;
    private int period = 0;
    private String changedWarning = "";
    private Button undoButton;
    private Button menuButton;
    private BoardCanvas boardCanvas;

    /**
     * Constructs the game board panel with the provided main frame reference.
     *
     * @param frame the main application frame that manages game states
     */
    public BoardPanel(Frame frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());

        warningText = new Label(Color.GREEN, Color.orange, 250, 0, 300, 50, 30);

        // Undo button setup
        undoButton = new Button(820, 50, 120, 55, "Undo", Color.GREEN, 35);
        undoButton.addActionListener(e -> {
            game.undoLastMove();
            boardCanvas.repaint();
        });

        // Menu button setup
        menuButton = new Button(820, 50, 120, 55, "Quit Game", Color.GREEN, 35);
        menuButton.addActionListener(e -> {
            frame.restartGame(true);
            boardCanvas.repaint();
        });

        // Top panel with buttons and warning text
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setPreferredSize(new Dimension(BOARD_SIZE, 60));
        topPanel.setBackground(Color.BLACK);
        topPanel.add(undoButton);
        topPanel.add(menuButton);
        topPanel.add(warningText);
        this.add(topPanel, BorderLayout.NORTH);

        // Game board canvas
        boardCanvas = new BoardCanvas();
        boardCanvas.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        this.add(boardCanvas, BorderLayout.CENTER);

        timer.start();

        // Mouse interaction on game board
        boardCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int column = e.getX() / SQUARE_SIZE;
                int line = 7 - (e.getY() / SQUARE_SIZE);

                Figurine clicked = game.getFigurineAt(line, column);

                if (selectedPosition == null) {
                    if (clicked != null) {
                        selectedPosition = new int[]{line, column};
                    }
                } else {
                    game.moveFigurine(selectedPosition[0], selectedPosition[1], line, column);
                    if (game.isNextTake()) {
                        selectedPosition = new int[]{line, column};
                    } else {
                        selectedPosition = null;
                    }
                    boardCanvas.repaint();

                    if (game.isGameOver()) {
                        String winner = game.getWinner();
                        frame.showWin(winner);
                    }
                }
            }
        });
    }

    /**
     * Timer that updates warning messages from the game logic.
     */
    Timer timer = new Timer(1, e -> {
        if (game.getWarningText() != null) {
            if (period == 1) {
                changedWarning = game.getWarningText();
            }
            if (!changedWarning.equals(game.getWarningText())) {
                period = 0;
            }
            period++;
            warningText.setText(game.getWarningText());
            warningText.setVisible(true);
            if (period >= 200) {
                period = 0;
                warningText.setVisible(false);
                game.setWarningText(null);
            }
        }
    });

    /**
     * Inner class {@code BoardCanvas} handles the actual drawing of the game board and pieces.
     */
    class BoardCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int size = 8;

            // Draw checkerboard squares
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

            // Draw move indicators and pieces
            int line = -1;
            for (int lin = 7; lin >= 0; lin--) {
                line++;
                for (int col = 0; col < size; col++) {

                    // Highlight possible move positions
                    if (!game.moves.isEmpty()) {
                        Move m = new Move(lin, col, lin, col);
                        for (Move move : game.moves) {
                            if (move.getToX() == m.getToX() && move.getToY() == m.getToY()) {
                                g.setColor(Color.GREEN);
                                g.fillOval(col * SQUARE_SIZE + 25, line * SQUARE_SIZE + 25, SQUARE_SIZE - 50, SQUARE_SIZE - 50);
                            }
                            if (move.getFromX() == m.getFromX() && move.getFromY() == m.getFromY()) {
                                g.setColor(Color.GREEN);
                                g.fillOval(col * SQUARE_SIZE + 8, line * SQUARE_SIZE + 8, SQUARE_SIZE - 16, SQUARE_SIZE - 16);
                            }
                        }
                    }

                    // Draw figurines
                    Figurine f = game.getFigurineAt(lin, col);
                    if (f != null) {
                        Color c = f.isWhite() ? Color.WHITE : Color.RED;

                        if (f.isQueen()) {
                            // Draw queen with layered circles
                            g.setColor(Color.YELLOW);
                            g.fillOval(col * SQUARE_SIZE + 10, line * SQUARE_SIZE + 10, SQUARE_SIZE - 20, SQUARE_SIZE - 20);
                            g.setColor(c);
                            g.fillOval(col * SQUARE_SIZE + 15, line * SQUARE_SIZE + 15, SQUARE_SIZE - 30, SQUARE_SIZE - 30);
                            g.setColor(Color.YELLOW);
                            g.fillOval(col * SQUARE_SIZE + 30, line * SQUARE_SIZE + 30, SQUARE_SIZE - 60, SQUARE_SIZE - 60);
                        } else {
                            // Draw normal piece
                            g.setColor(c);
                            g.fillOval(col * SQUARE_SIZE + 10, line * SQUARE_SIZE + 10, SQUARE_SIZE - 20, SQUARE_SIZE - 20);
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the preferred size of the panel, including the top button panel.
     *
     * @return the preferred size as a {@code Dimension} object
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_SIZE, BOARD_SIZE + 60); // Add height of top panel
    }
}
