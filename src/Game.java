import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    private Figurine[][] board;
    private boolean take;
    private int[] whiteMoves = new int[2];
    private int[] blackMoves = new int[2];
    private int checked = 0;

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
        //BUDU KONTROLOVAT KAZDOU FIGURKU
        if(!((toLine + toColumn) % 2 == 0)){
            System.out.println("You can't move the figurine at all");
            return;
        }
        if (getFigurineAt(toLine, toColumn) != null){
            System.out.println("You can't move the figurine");
            return;
        }
        System.out.println("checking");
        checkMove(fromLine, fromColumn, toLine, toColumn);
        System.out.println("checked");
    }

    public void checkMove(int fromLine, int fromColumn, int toLine, int toColumn){
        if(getFigurineAt(fromLine,fromColumn) == null){
            return;
        }
        int i;
        if(getFigurineAt(fromLine,fromColumn).isWhite()){
            i = 1;
        } else {
            i = -1;
        }
        if(getFigurineAt(fromLine, fromColumn).isQueen()){
            if(fromLine-i != toLine || fromLine+i != toLine){
                return;
            }
        }
        if(fromLine+i == toLine && (fromColumn+i == toColumn || fromColumn-i == toColumn)){
            Figurine f = board[fromLine][fromColumn];
            board[fromLine][fromColumn] = null;
            board[toLine][toColumn] = f;
            System.out.println("Figurine moved.");
            if ((f.isWhite() && toLine == 7) || (!f.isWhite() && toLine == 0)) {
                f.setQueen(true);
                System.out.println("Queen");
            }
            takeFigurine(fromLine, fromColumn);
        } else {
            System.out.println("Figurine didnt move.");
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

    public void takeFigurines(boolean white){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].isWhite() == white){


                }
            }
        }
    }

    public void takeFigurine(int fromLine, int fromColumn){
//        checked--;
//        if(checked == 0){
//            takeFigurine(fromLine,fromColumn);
//        }
        boolean figurines[] = new boolean[5];
        int index = 0;
        int usedIndex = 0;
        for (int y = -2; y <= 2; y++) {
            if(y != 0){

                int i = -1;

                if(y > 0){
                    i = 1;
                }

                for (int x = -i*y; x < i*y; x=i*y) {

                    if((fromLine+y) >= 0 && (fromLine+y) < 8 && (fromColumn+x) >= 0 && (fromColumn+x) < 8){
                        Figurine f = board[fromLine+y][fromColumn+x];
                        if(f != null){
                            if(y == -2 || y == 1){
                                figurines[index] = f.isWhite();
                                index++;
                            } else {
                                if(f.isWhite() != figurines[usedIndex]){
                                    take = true;
                                    usedIndex++;
                                } else {
                                    take = false;
                                }
                                System.out.println("take is " + take);
                                System.out.println("x = " + (fromColumn+1) + " y = " + (fromLine+1));
                            }
                        }
                    }
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
