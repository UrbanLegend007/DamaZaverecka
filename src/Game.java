import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    private Figurine[][] board;

    public Game(){
        board = new Figurine[8][8];
        loadDefaultPosition();
    }

    public Figurine getFigurineAt(int line, int col) {
        return board[line][col];
    }

    public String addFigurine(boolean white, int line, int col){
        line -= 1;
        col -= 1;
        board[line][col] = new Figurine(white);
        return "Figurine added";
    }

    public void moveFigurine(int fromLine, int fromColumn, int toLine, int toColumn) {
//        int fromRow = 8 - fromLine;
//        int fromCol = fromColumn - 1;
//        int toRow = 8 - toLine;
//        int toCol = toColumn -1;
        System.out.println(fromLine + " " + fromColumn + " " + toLine + " " + toColumn);

        if (getFigurineAt(toLine, toColumn) != null){
            System.out.println("You can't move the figurine");
        } else {
            Figurine f = board[fromLine][fromColumn];
            board[fromLine][fromColumn] = null;
            board[toLine][toColumn] = f;
            if ((f.isWhite() && toLine == 7) || (!f.isWhite() && toLine == 0)) {
                f.setQueen(true);
                System.out.println("Queen");
            }
        }
    }

    public String loadDefaultPosition(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("res/defaultPosition"))){
            while((line = reader.readLine()) != null){
                String parts[] = line.split(",");
                addFigurine(Boolean.parseBoolean(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            }
            return "Default position loaded";
        } catch (Exception e){
            return "Error loading default position";
        }
    }

    public void printBoardState() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figurine f = board[row][col];
                if (f != null) {
                    int line = 8 - row;
                    int column = col + 1;
                    System.out.println("[" + line + "," + column + "] -> " + f);
                }
            }
        }
    }
}

/*
   line
     _________________________
   8 |__|__|__|__|__|__|__|__|
   7 |__|__|__|__|__|__|__|__|
   6 |__|__|__|__|__|__|__|__|
   5 |__|__|__|__|__|__|__|__|
   4 |__|__|__|__|__|__|__|__|
   3 |__|__|__|__|__|__|__|__|
   2 |__|__|__|__|__|__|__|__|
   1 |__|__|__|__|__|__|__|__|
       1  2  3  4  5  6  7  8  column
 */
