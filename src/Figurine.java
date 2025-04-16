public class Figurine {

    boolean white;
    boolean queen = false;

    int[] position;

    public Figurine(boolean white, int line, int column){
        this.white = white;
        this.position = new int[]{line, column};
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
