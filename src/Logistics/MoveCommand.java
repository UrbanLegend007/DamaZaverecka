package Logistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a move command in the game, implementing the Command interface.
 * It encapsulates a move action from one position to another and supports undoing the move.
 * This includes capturing opponent figurines if the move is a capture move.
 */
public class MoveCommand implements Command {
    /**
     * The game instance on which the move is performed.
     */
    private Game game;

    /**
     * Starting row of the move.
     */
    private int fromRow;

    /**
     * Starting column of the move.
     */
    private int fromCol;

    /**
     * Destination row of the move.
     */
    private int toRow;

    /**
     * Destination column of the move.
     */
    private int toCol;

    /**
     * Indicates which player's turn it was before the move (true for white, false for black).
     */
    private boolean whiteTurn;

    /**
     * Sets whether the figurine was a queen.
     */
    private boolean wasQueen;


    /**
     * List of captured figurines during this move, stored for undoing purposes.
     */
    private final List<CapturedFigurine> capturedFigurines = new ArrayList<>();

    /**
     * Constructs a MoveCommand with all necessary information to perform and undo the move.
     *
     * @param game the game instance on which the move is executed
     * @param fromRow starting row of the move
     * @param fromCol starting column of the move
     * @param toRow destination row of the move
     * @param toCol destination column of the move
     * @param whiteTurn the player turn (true for white, false for black) before executing the move
     */
    public MoveCommand(Game game, int fromRow, int fromCol, int toRow, int toCol, boolean whiteTurn) {
        this.game = game;
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.whiteTurn = whiteTurn;
    }

    /**
     * Executes the move on the game board.
     * If the move is a capturing move (distance of 2), the captured figurine is removed from the board
     * and stored for undoing later.
     * The figurine is moved from the starting position to the destination.
     */
    @Override
    public void execute() {
        if (Math.abs(fromRow - toRow) == 2) {
            int midRow = (fromRow + toRow) / 2;
            int midCol = (fromCol + toCol) / 2;
            Figurine captured = game.getFigurineAt(midRow, midCol);
            if (captured != null) {
                capturedFigurines.add(new CapturedFigurine(captured, midRow, midCol));
                game.setFigurineAt(midRow, midCol, null);
            }
        }
        Figurine piece = game.getFigurineAt(fromRow, fromCol);
        wasQueen = piece.isQueen();
        game.setFigurineAt(toRow, toCol, piece);
        game.setFigurineAt(fromRow, fromCol, null);
    }

    /**
     * Undoes the previously executed move.
     * Moves the figurine back to the starting position, restores any captured figurines,
     * clears the list of captured figurines, and resets the game's state accordingly.
     */
    @Override
    public void undo() {
        Figurine piece = game.getFigurineAt(toRow, toCol);
        game.setFigurineAt(fromRow, fromCol, piece);
        piece.setQueen(wasQueen);
        game.setFigurineAt(toRow, toCol, null);
        for (int i = 0; i < capturedFigurines.size(); i++) {
            game.setFigurineAt(capturedFigurines.get(i).row, capturedFigurines.get(i).col, capturedFigurines.get(i).figurine);
        }
        capturedFigurines.clear();
        game.setNextTake(true);
        game.takeFigurines(game.isWhiteTurn());
        game.setWhiteTurn(whiteTurn);
        game.takeFigurines(game.isWhiteTurn());
    }
}
