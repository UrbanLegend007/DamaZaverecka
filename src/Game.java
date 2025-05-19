import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    private Figurine[][] board;
    private boolean take;
    private boolean white;

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
        boolean move = false;
        if(!((toLine + toColumn) % 2 == 0)){
            System.out.println("You can't move the figurine at all");
            return;
        }
        if (getFigurineAt(toLine, toColumn) != null){
            System.out.println("You can't move the figurine");
            return;
        } else {
            move = true;
//            Figurine f = board[fromLine][fromColumn];
//            board[fromLine][fromColumn] = null;
//            board[toLine][toColumn] = f;
//            if ((f.isWhite() && toLine == 7) || (!f.isWhite() && toLine == 0)) {
//                f.setQueen(true);
//                System.out.println("Queen");
//            }
        }
        if(move){
            if(getFigurineAt(fromLine, fromColumn).isWhite()){
                if(getFigurineAt(fromLine, fromColumn).isQueen()){
                    if((fromLine+1 == toLine || fromLine-1 == toLine) && (fromColumn+1 == toColumn || fromColumn-1 == toColumn)){
                        Figurine f = board[fromLine][fromColumn];
                        board[fromLine][fromColumn] = null;
                        board[toLine][toColumn] = f;
                        System.out.println("Queen moved.");
                    } else {
                        System.out.println("Queen didnt move.");
                    }
                } else {
                    if(fromLine+1 == toLine && (fromColumn+1 == toColumn || fromColumn-1 == toColumn)){
                        Figurine f = board[fromLine][fromColumn];
                        board[fromLine][fromColumn] = null;
                        board[toLine][toColumn] = f;
                        System.out.println("Figurine moved.");
                        if ((f.isWhite() && toLine == 7) || (!f.isWhite() && toLine == 0)) {
                            f.setQueen(true);
                            System.out.println("Queen");
                        }
                    } else {
                        System.out.println("Figurine didnt move.");
                    }
                }
            } else {
                if(getFigurineAt(fromLine, fromColumn).isQueen()){
                    if((fromLine-1 == toLine || fromLine+1 == toLine) && (fromColumn+1 == toColumn || fromColumn-1 == toColumn)){
                        Figurine f = board[fromLine][fromColumn];
                        board[fromLine][fromColumn] = null;
                        board[toLine][toColumn] = f;
                        System.out.println("Queen moved.");
                    } else {
                        System.out.println("Queen didnt move.");
                    }
                } else {
                    if(fromLine-1 == toLine && (fromColumn-1 == toColumn || fromColumn+1 == toColumn)){
                        Figurine f = board[fromLine][fromColumn];
                        board[fromLine][fromColumn] = null;
                        board[toLine][toColumn] = f;
                        System.out.println("Figurine moved.");
                        if ((f.isWhite() && toLine == 7) || (!f.isWhite() && toLine == 0)) {
                            f.setQueen(true);
                            System.out.println("Queen");
                        }
                    } else {
                        System.out.println("Figurine didnt move.");
                    }
                }
            }
        }
//        takeFigurine(fromLine, fromColumn);
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

//    public boolean takeFigurine(int fromLine, int fromColumn) {
//        Figurine f = board[fromLine][fromColumn];
//
//        if (f == null) return false;
//
//        int direction = f.isWhite() ? 1 : -1; // směr pohybu obyčejné figurky
//
//        // směr pohybu ve 4 diagonálách: [řádek, sloupec]
//        int[][] directions = {
//                {direction * 1, -1}, // doleva
//                {direction * 1, 1},  // doprava
//                {-direction * 1, -1}, // zpětný směr doleva (jen pro královnu)
//                {-direction * 1, 1}   // zpětný směr doprava (jen pro královnu)
//        };
//
//        for (int[] dir : directions) {
//            int midLine = fromLine + dir[0];
//            int midCol = fromColumn + dir[1];
//            int toLine = fromLine + 2 * dir[0];
//            int toCol = fromColumn + 2 * dir[1];
//
//            if (inBounds(midLine, midCol) && inBounds(toLine, toCol)) {
//                Figurine middle = board[midLine][midCol];
//                Figurine target = board[toLine][toCol];
//
//                if (middle != null && middle.isWhite() != f.isWhite() && target == null) {
//                    return true; // existuje figura soupeře + za ní je prázdné místo
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private boolean inBounds(int line, int col) {
//        return line >= 0 && line < 8 && col >= 0 && col < 8;
//    }


    public boolean takeFigurine(int fromLine, int fromColumn, boolean white){
        //ZMENIT FROM PROTOZE TO BUDU KONTROLOVAT PO TOM POHYBU
        Figurine f = board[fromLine][fromColumn];
        if(f == null){
            boolean checked = false;
            for (int y = -2; y <= 2; y++) {

                for (int x = y; x <= -y; x-=2*y) {
                    if(!checked){
                        Figurine take = board[fromLine+x][fromColumn+x];
                        if(take != null){
                            if(take.isWhite()){
                                white = true;
                            }
                        } else {
                            x = 2;
                            checked = true;
                        }
                    }
                }
            }
        }
        return take;
    }

//    public void printBoardState() {
//        for (int row = 0; row < 8; row++) {
//            for (int col = 0; col < 8; col++) {
//                Figurine f = board[row][col];
//                if (f != null) {
//                    int line = 8 - row;
//                    int column = col + 1;
//                    System.out.println("[" + line + "," + column + "] -> " + f);
//                }
//            }
//        }
//    }
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
