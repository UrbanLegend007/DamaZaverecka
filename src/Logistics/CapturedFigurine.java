package Logistics;

/**
 * The {@code CapturedFigurine} class represents a game piece (figurine) that has been captured
 * during gameplay, along with its original position on the board.
 * <p>
 * This class is useful for tracking removed pieces, enabling features like move history,
 * undo functionality, or animations of captured figurines.
 */
public class CapturedFigurine {

    /**
     * The figurine that was captured.
     */
    public Figurine figurine;

    /**
     * The row on the board where the figurine was located before being captured.
     */
    public int row;

    /**
     * The column on the board where the figurine was located before being captured.
     */
    public int col;

    /**
     * Constructs a new {@code CapturedFigurine} instance with the specified figurine and its position.
     *
     * @param figurine the captured figurine
     * @param row      the row index where the figurine was captured
     * @param col      the column index where the figurine was captured
     */
    public CapturedFigurine(Figurine figurine, int row, int col) {
        this.figurine = figurine;
        this.row = row;
        this.col = col;
    }
}
