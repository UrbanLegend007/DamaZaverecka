import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    private Figurine[][] board;
    private boolean take;
    private boolean winner;
    private boolean whiteTurn = true;
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
        if(whiteTurn){
            if(!getFigurineAt(fromLine, fromColumn).isWhite()){
                return;
            }
        } else {
            if(getFigurineAt(fromLine, fromColumn).isWhite()){
                return;
            }
        }
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
        boolean take = false;
        if(getFigurineAt(fromLine, fromColumn).isQueen()){
            if(fromLine-(2*i) == toLine || fromLine+(2*i) == toLine){
                take = true;
                System.out.println("take");
            } else if(!(fromLine-i == toLine || fromLine+i == toLine)){
                System.out.println("return queen");
                return;
            }
        } else {
            if(fromLine+(2*i) == toLine){
                take = true;
                System.out.println("take");
            } else if(!(fromLine+i == toLine)){
                System.out.println("return figurine");
                return;
            }
        }
        int takeColumn;
        int takeLine;
        if(!take){
            if(!(fromColumn+i == toColumn || fromColumn-i == toColumn)){
                System.out.println("Figurine didnt move.");
                return;
            }
        } else {
            if(fromColumn+(2*i) == toColumn || fromColumn-(2*i) == toColumn){
                System.out.println("take");
                if(fromColumn < toColumn){
                    takeColumn = fromColumn+1;
                } else {
                    takeColumn = fromColumn-1;
                }
                if(fromLine < toLine){
                    takeLine = fromLine+1;
                } else {
                    takeLine = fromLine-1;
                }
                System.out.println("take: x = " + (takeColumn+1) + " y = " + (takeLine+1));
                System.out.println("was: x = " + (fromColumn+1) + " y = " + (fromLine+1));
                System.out.println("is: x = " + (toColumn+1) + " y = " + (toLine+1));
                if(getFigurineAt(takeLine,takeColumn) == null){
                    System.out.println("no figurine");
                    return;
                } else if(getFigurineAt(takeLine,takeColumn).isWhite() == getFigurineAt(fromLine,fromColumn).isWhite()){
                    System.out.println("same color");
                    return;
                }
                board[takeLine][takeColumn] = null;
                System.out.println("figurine taken");
            } else {
                System.out.println("take is false");
                return;
            }
        }
        Figurine f = board[fromLine][fromColumn];
        board[fromLine][fromColumn] = null;
        board[toLine][toColumn] = f;
        System.out.println("Figurine moved.");
        if ((f.isWhite() && toLine == 7) || (!f.isWhite() && toLine == 0)) {
            f.setQueen(true);
            System.out.println("Queen");
        }
        whiteTurn = !f.isWhite();
        System.out.println("checking white moves: " + whiteTurn);
        takeFigurines(whiteTurn);
        System.out.println("checked moves: " + checked);
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
        checked = 0;
        boolean[][] takes = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != null){
                    if(board[i][j].isWhite() == white){
                        takeFigurine(i,j);
                        takes[i][j] = take;
                        if(takes[i][j]){
                            checked++;
                        }
                    }
                }
            }
        }
    }

    public void takeFigurine(int fromLine, int fromColumn){
        boolean figurines[] = new boolean[4];
        boolean[] takes = new boolean[8];
        take = false;
        int index = -2;
        int usedIndex = 0;
        for (int y = -2; y <= 2; y++) {
            if(y == 0){
                index--;
            } else {

                int i = -1;

                if(y > 0){
                    i = 1;
                }

                for (int x = -i*y; x < i*y; x=i*y) {
                    System.out.println("we´re checking figurines at " + x + "," + y);
                    System.out.println((fromLine+y));
                    System.out.println((fromColumn+x));

                    if(((fromLine+y) >= 0 && (fromLine+y) < 8) && ((fromColumn+x) >= 0 && (fromColumn+x) < 8)){
                        System.out.println("x = " + (fromColumn+x) + " y = " + (fromLine+y) + " is checking");
                        Figurine f = board[fromLine+y][fromColumn+x];

                        System.out.println("index: " + index);
                        if(y == i){
                            index++;
                            if(f != null){
                                takes[index] = true;
                                figurines[usedIndex] = f.isWhite();
                                usedIndex++;
                            } else {
                                takes[index] = false;
                            }
                            index++;
                        } else {
                            if(x == -2){
                                index-=y;
                            }
                            if(f == null){
                                takes[index] = true;
                            } else {
                                takes[index] = false;
                            }
                            index-=x;
                        }
                        System.out.println("index: " + index);
//                        if(takes[]){
//
//                        }
//                        1-3
//                        2-4
//                        5-7
//                        6-8

//                        if(y == -2){
//                            if(f == null){
//                                figurines[index] = true;
//                                index++;
//                            }
//                        } else {
//                            if(f != null){
//
//                            }
//                        }
//                        if(f != null){
//                            if(y == -2 || y == 1){
//                                figurines[index] = f.isWhite();
//                                index++;
//                            } else {
//                                if(f.isWhite() != figurines[usedIndex]){
//                                    take = true;
//                                    usedIndex++;
//                                } else {
//                                    take = false;
//                                }
//                                System.out.println("take is " + take);
//                                System.out.println("x = " + (fromColumn+1) + " y = " + (fromLine+1));
//                            }
//                        }
                    } else {
                        if(y == i){
                            index+=2;
                        } else {
                            if(x == -2){
                                index-=y;
                            }
                            index-=x;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 8; i+=2) {
            if(takes[i] == takes[i+1]){
                take = true;
                System.out.println("take " + i + ": " + take);
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
