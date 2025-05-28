package Logistics;

import java.util.Objects;

public class Move {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    public Move(int fromX, int fromY, int toX, int toY) {
        this.setFromX(fromX);
        this.setFromY(fromY);
        this.setToX(toX);
        this.setToY(toY);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Move)) return false;
        Move other = (Move) obj;
        return this.getFromX() == other.getFromX() && this.getFromY() == other.getFromY() &&
                this.getToX() == other.getToX() && this.getToY() == other.getToY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFromX(), getFromY(), getToX(), getToY());
    }

    @Override
    public String toString() {
        return "(" + getFromX() + "," + getFromY() + ") → (" + getToX() + "," + getToY() + ")";
    }

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public int getToX() {
        return toX;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public int getToY() {
        return toY;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }
}
