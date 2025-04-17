import java.util.Arrays;

public class Figurine {

    boolean white;
    boolean queen = false;

    int[] position;

    public Figurine(boolean white, int line, int column){
        this.white = white;
        this.position = new int[]{line, column};
    }

    @Override
    public String toString() {
        return "Figurine: " +
                "white = " + white +
                ", queen = " + queen +
                ", position = " + Arrays.toString(position);
    }

    public void setQueen(boolean queen){
        this.queen = queen;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isQueen() {
        return queen;
    }

    public int[] getPosition() {
        return position;
    }
}
