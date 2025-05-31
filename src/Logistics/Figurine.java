package Logistics;

/**
 * The {@code Figurine} class represents a game piece in a checkers-like game.
 * It contains information about the piece's color and whether it has been promoted to a queen.
 */
public class Figurine {

    /**
     * Indicates whether the figurine is white.
     * If {@code true}, the piece belongs to the white player.
     */
    private boolean white;

    /**
     * Indicates whether the figurine is a queen.
     * A queen has enhanced movement abilities.
     */
    private boolean queen;

    /**
     * Constructs a {@code Figurine} with the specified color and status.
     *
     * @param white {@code true} if the figurine is white, {@code false} if black
     * @param queen {@code true} if the figurine is a queen, {@code false} otherwise
     */
    public Figurine(boolean white, boolean queen) {
        this.white = white;
        this.queen = queen;
    }

    /**
     * Checks if the figurine is white.
     *
     * @return {@code true} if the figurine is white, {@code false} otherwise
     */
    public boolean isWhite() {
        return white;
    }

    /**
     * Checks if the figurine is a queen.
     *
     * @return {@code true} if the figurine is a queen, {@code false} otherwise
     */
    public boolean isQueen() {
        return queen;
    }

    /**
     * Sets whether the figurine is a queen.
     *
     * @param queen {@code true} to promote the figurine to a queen, {@code false} otherwise
     */
    public void setQueen(boolean queen) {
        this.queen = queen;
    }

    /**
     * Returns a string representation of the figurine, including its color and status.
     *
     * @return a string describing the figurine
     */
    @Override
    public String toString() {
        return "Figurine{" +
                "white=" + white +
                ", queen=" + queen +
                '}';
    }
}
