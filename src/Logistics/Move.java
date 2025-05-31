package Logistics;

import java.util.Objects;

/**
 * Represents a move in the game from one coordinate to another.
 * Stores the starting position (fromX, fromY) and the destination position (toX, toY).
 */
public class Move {
    /**
     * X-coordinate of the starting position.
     */
    private int fromX;

    /**
     * Y-coordinate of the starting position.
     */
    private int fromY;

    /**
     * X-coordinate of the destination position.
     */
    private int toX;

    /**
     * Y-coordinate of the destination position.
     */
    private int toY;

    /**
     * Constructs a Move object with specified start and end coordinates.
     *
     * @param fromX starting X coordinate
     * @param fromY starting Y coordinate
     * @param toX destination X coordinate
     * @param toY destination Y coordinate
     */
    public Move(int fromX, int fromY, int toX, int toY) {
        this.setFromX(fromX);
        this.setFromY(fromY);
        this.setToX(toX);
        this.setToY(toY);
    }

    /**
     * Checks whether this move is equal to another object.
     * Two moves are equal if their from and to coordinates match exactly.
     *
     * @param obj the object to compare with
     * @return {@code true} if both moves have the same coordinates, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Move)) return false;
        Move other = (Move) obj;
        return this.getFromX() == other.getFromX() && this.getFromY() == other.getFromY() &&
                this.getToX() == other.getToX() && this.getToY() == other.getToY();
    }

    /**
     * Returns a hash code value for the move, based on all four coordinates.
     *
     * @return the hash code of the move
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFromX(), getFromY(), getToX(), getToY());
    }

    /**
     * Returns a string representation of the move in the format:
     * (fromX,fromY) → (toX,toY)
     *
     * @return a string representing the move
     */
    @Override
    public String toString() {
        return "(" + getFromX() + "," + getFromY() + ") → (" + getToX() + "," + getToY() + ")";
    }

    /**
     * Gets the starting X coordinate.
     *
     * @return the starting X coordinate
     */
    public int getFromX() {
        return fromX;
    }

    /**
     * Sets the starting X coordinate.
     *
     * @param fromX the starting X coordinate to set
     */
    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    /**
     * Gets the starting Y coordinate.
     *
     * @return the starting Y coordinate
     */
    public int getFromY() {
        return fromY;
    }

    /**
     * Sets the starting Y coordinate.
     *
     * @param fromY the starting Y coordinate to set
     */
    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    /**
     * Gets the destination X coordinate.
     *
     * @return the destination X coordinate
     */
    public int getToX() {
        return toX;
    }

    /**
     * Sets the destination X coordinate.
     *
     * @param toX the destination X coordinate to set
     */
    public void setToX(int toX) {
        this.toX = toX;
    }

    /**
     * Gets the destination Y coordinate.
     *
     * @return the destination Y coordinate
     */
    public int getToY() {
        return toY;
    }

    /**
     * Sets the destination Y coordinate.
     *
     * @param toY the destination Y coordinate to set
     */
    public void setToY(int toY) {
        this.toY = toY;
    }
}
