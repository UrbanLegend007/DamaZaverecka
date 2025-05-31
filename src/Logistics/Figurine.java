package Logistics;

public class Figurine {

    private boolean white;
    private boolean queen;

    public Figurine(boolean white, boolean queen) {
        this.white = white;
        this.queen = queen;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isQueen() {
        return queen;
    }

    public void setQueen(boolean queen) {
        this.queen = queen;
    }

    @Override
    public String toString() {
        return "Figurine{" +
                "white=" + white +
                ", queen=" + queen +
                '}';
    }
}
