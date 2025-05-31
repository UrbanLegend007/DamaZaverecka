package Logistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game logic and state of a checkers-like board game.
 * Handles the game board, player turns, moves, capturing pieces, and win conditions.
 */
public class Game {

    private Figurine[][] board;
    private boolean take;
    private boolean nextTake = false;
    private String winner;
    private boolean whiteTurn = true;
    private int checked = 0;
    private boolean gameOver = false;
    private String warningText = null;
    public List<Move> moves = new ArrayList<>();
    CommandManager commandManager = new CommandManager();

    /**
     * Constructs a new Game instance and loads the default board position.
     */
    public Game(){
        board = new Figurine[8][8];
        System.out.println(loadDefaultPosition());
    }

    /**
     * Executes a move from one position to another and updates the game state accordingly.
     * @param fromLine starting row index of the piece
     * @param fromColumn starting column index of the piece
     * @param toLine target row index to move the piece
     * @param toColumn target column index to move the piece
     * @param whiteTurn boolean indicating if it is white's turn
     */
    public void makeMove(int fromLine, int fromColumn, int toLine, int toColumn, boolean whiteTurn) {
        MoveCommand move = new MoveCommand(this, fromLine, fromColumn, toLine, toColumn, whiteTurn);
        commandManager.executeCommand(move);
    }

    /**
     * Undoes the last move executed in the game.
     */
    public void undoLastMove() {
        commandManager.undo();
        setWarningText(commandManager.getWarning());
    }

    /**
     * Sets a figurine at the specified position on the board.
     * @param row the row index on the board
     * @param col the column index on the board
     * @param figurine the figurine to place, or null to clear the position
     */
    public void setFigurineAt(int row, int col, Figurine figurine) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
            board[row][col] = figurine;
        }
    }

    /**
     * Retrieves the figurine at the specified position.
     * @param line the row index
     * @param col the column index
     * @return the Figurine at the position or null if empty
     */
    public Figurine getFigurineAt(int line, int col) {
        if(board[line][col] == null){
            return null;
        } else {
            return board[line][col];
        }
    }

    /**
     * Attempts to move a figurine from one position to another, validating the move according to game rules.
     * @param fromLine starting row index
     * @param fromColumn starting column index
     * @param toLine target row index
     * @param toColumn target column index
     */
    public void moveFigurine(int fromLine, int fromColumn, int toLine, int toColumn) {
        if(getFigurineAt(fromLine, fromColumn) == null){
            return;
        }
        if(whiteTurn){
            if(!getFigurineAt(fromLine, fromColumn).isWhite()){
                setWarningText("Now is white's turn");
                return;
            }
        } else {
            if(getFigurineAt(fromLine, fromColumn).isWhite()){
                setWarningText("Now is black's turn");
                return;
            }
        }
        if(!((toLine + toColumn) % 2 == 0)){
            setWarningText("You can move only diagonally");
            return;
        }
        if (getFigurineAt(toLine, toColumn) != null){
            setWarningText("You can't put the figurine on other figurines");
            return;
        }
        checkMove(fromLine, fromColumn, toLine, toColumn);
    }

    /**
     * Checks if the intended move is valid, applies it, and updates game state.
     * Handles captures, promotion to queen, turn switching, and available moves.
     * @param fromLine starting row index
     * @param fromColumn starting column index
     * @param toLine target row index
     * @param toColumn target column index
     */
    public void checkMove(int fromLine, int fromColumn, int toLine, int toColumn){
        if(getFigurineAt(fromLine,fromColumn) == null){
            warningText = "Figurine not found";
            return;
        }
        int i;
        if(getFigurineAt(fromLine,fromColumn).isWhite()){
            i = 1;
        } else {
            i = -1;
        }
        take = false;
        if(getFigurineAt(fromLine, fromColumn).isQueen()){
            if(fromLine-(2*i) == toLine || fromLine+(2*i) == toLine){
                take = true;
            } else if(!(fromLine-i == toLine || fromLine+i == toLine)){
                warningText = "You can move only by one move up or down";
                return;
            }
        } else {
            if(fromLine+(2*i) == toLine){
                take = true;
            } else if(!(fromLine+i == toLine)){
                if(getFigurineAt(fromLine,fromColumn).isWhite()){
                    warningText = "You can move only by one move up";
                } else {
                    warningText = "You can move only by one move down";
                }
                return;
            }
        }
        if(!moves.isEmpty()){
            Move move = new Move(fromLine, fromColumn, toLine, toColumn);
            if(!moves.contains(move)){
                setWarningText("You have to take a figurine");
                return;
            }
        }
        if(checkMovement(fromLine, fromColumn, toLine, toColumn, i, take)){
            Figurine f = getFigurineAt(fromLine, fromColumn);
            makeMove(fromLine, fromColumn, toLine, toColumn, whiteTurn);
            if (((f.isWhite() && toLine == 7) || (!f.isWhite() && toLine == 0)) && !f.isQueen()) {
                f.setQueen(true);
                setWarningText("Figurine became queen");
            }
            moves.clear();
            if(take){
                hasToTakeFigurine(toLine, toColumn);
                nextTake = true;
            }

            if(moves.isEmpty()){
                whiteTurn = !f.isWhite();
                takeFigurines(whiteTurn);
                nextTake = false;
            } else {
                whiteTurn = f.isWhite();
            }
        }
        checkWinCondition();
    }

    /**
     * Checks if a player has won by evaluating if the opponent has any pieces or valid moves left.
     * Sets the winner and game over flag accordingly.
     */
    public void checkWinCondition() {
        boolean whiteHasPieces = false;
        boolean blackHasPieces = false;
        boolean whiteCanMove = false;
        boolean blackCanMove = false;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                Figurine figurine = getFigurineAt(row, col);
                if (figurine != null) {
                    if (figurine.isWhite()) {
                        whiteHasPieces = true;
                        if (!whiteCanMove && hasValidMove(figurine, row, col)) {
                            whiteCanMove = true;
                        }
                    } else if (!figurine.isWhite()) {
                        blackHasPieces = true;
                        if (!blackCanMove && hasValidMove(figurine, row, col)) {
                            blackCanMove = true;
                        }
                    }
                }
            }
        }

        if (!whiteHasPieces || !whiteCanMove) {
            setWarningText("Black has won!");
            setWinner("Black");
            gameOver = true;
        } else if (!blackHasPieces || !blackCanMove) {
            setWarningText("White has won!");
            setWinner("White");
            gameOver = true;
        }
    }

    /**
     * Checks if the given figurine has any valid moves available.
     * @param piece the figurine to check
     * @param row current row index of the figurine
     * @param col current column index of the figurine
     * @return true if at least one valid move exists, false otherwise
     */
    private boolean hasValidMove(Figurine piece, int row, int col) {
        int[][] directions = piece.isQueen()
                ? new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}
                : (piece.isWhite() ? new int[][]{{1, -1}, {1, 1}} : new int[][]{{-1, 1}, {-1, -1}});

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isInBounds(newRow, newCol) && getFigurineAt(newRow, newCol) == null) {
                return true;
            }

            int midRow = row + dir[0];
            int midCol = col + dir[1];
            int jumpRow = row + 2 * dir[0];
            int jumpCol = col + 2 * dir[1];

            if (isInBounds(jumpRow, jumpCol) && getFigurineAt(jumpRow, jumpCol) == null) {
                Figurine midPiece = getFigurineAt(midRow, midCol);
                if (midPiece != null && midPiece.isWhite() != piece.isWhite()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the given coordinates are within the board boundaries.
     * @param row row index to check
     * @param col column index to check
     * @return true if inside board, false otherwise
     */
    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    /**
     * Checks if a movement from one position to another is valid based on side moves and capturing rules.
     * @param fromLine starting row index
     * @param fromColumn starting column index
     * @param toLine target row index
     * @param toColumn target column index
     * @param i direction modifier (1 or -1) based on piece color
     * @param take true if the move is a capture
     * @return true if the move is valid, false otherwise
     */
    public boolean checkMovement(int fromLine, int fromColumn, int toLine, int toColumn, int i, boolean take){
        int takeColumn;
        int takeLine;
        if(!take){
            if(!(fromColumn+i == toColumn || fromColumn-i == toColumn)){
                setWarningText("You can move the figurine to the side only by one move");
                return false;
            } else {
                return true;
            }
        } else {
            if(fromColumn+(2*i) == toColumn || fromColumn-(2*i) == toColumn){
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
                if(getFigurineAt(takeLine,takeColumn) == null){
                    setWarningText("You can move the figurine only by one move");
                    return false;
                } else if(getFigurineAt(takeLine,takeColumn).isWhite() == getFigurineAt(fromLine,fromColumn).isWhite()){
                    setWarningText("You can't take your figurine");
                    return false;
                }
                return true;
            } else {
                setWarningText("You can move the figurine to the side only by one move");
                return false;
            }
        }
    }

    /**
     * Loads the default board position from a resource file "res/defaultPosition.txt".
     * Each line in the file describes a figurine color and its position.
     * @return status message indicating success or error during loading
     */
    public String loadDefaultPosition(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("res/defaultPosition.txt"))){
            while((line = reader.readLine()) != null){
                String parts[] = line.split(",");
                Figurine f = new Figurine(Boolean.parseBoolean(parts[0]), false);
                int lin = Integer.parseInt(parts[1]) - 1;
                int col = Integer.parseInt(parts[2]) - 1;
                setFigurineAt(lin, col, f);
            }
            return "Default position loaded";
        } catch (Exception e){
            return "Error loading default position";
        }
    }

    /**
     * Checks all pieces of the specified color to see if any must perform a capture.
     * Updates the list of possible capture moves and a counter of such pieces.
     * @param white true to check white pieces, false for black pieces
     */
    public void takeFigurines(boolean white){
        checked = 0;
        moves.clear();
        boolean[][] takes = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(getFigurineAt(i,j) != null){
                    if(getFigurineAt(i,j).isWhite() == white){
                        hasToTakeFigurine(i,j);
                        takes[i][j] = take;
                        if(takes[i][j]){
                            checked++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Determines whether the piece at the given position has any capture moves available.
     * If yes, adds the capture move to the moves list and sets the take flag.
     * @param fromLine row index of the piece
     * @param fromColumn column index of the piece
     */
    public void hasToTakeFigurine(int fromLine, int fromColumn){
        take = false;
        Figurine current = getFigurineAt(fromLine, fromColumn);
        if (current == null) return;

        boolean isQueen = current.isQueen();
        int direction;
        if(current.isWhite()){
            direction = 1;
        } else {
            direction = -1;
        }

        int[][] deltas = isQueen ? new int[][] {
                {2, 2}, {2, -2}, {-2, 2}, {-2, -2}
        } : new int[][] {
                {2 * direction, 2}, {2 * direction, -2}
        };

        for (int[] delta : deltas) {
            int toLine = fromLine + delta[0];
            int toColumn = fromColumn + delta[1];
            int midLine = fromLine + delta[0] / 2;
            int midColumn = fromColumn + delta[1] / 2;

            if (isInsideBoard(toLine, toColumn) && getFigurineAt(toLine, toColumn) == null) {
                Figurine middle = getFigurineAt(midLine, midColumn);
                if (middle != null && middle.isWhite() != current.isWhite()) {
                    take = true;
                    moves.add(new Move(fromLine, fromColumn, toLine, toColumn));
                } else {
                    nextTake = false;
                }
            } else {
                nextTake = false;
            }
        }
    }

    /**
     * Checks if given position is inside the board limits.
     * @param line row index to check
     * @param col column index to check
     * @return true if inside board, false otherwise
     */
    private boolean isInsideBoard(int line, int col) {
        return line >= 0 && line < 8 && col >= 0 && col < 8;
    }

    // Getters and setters for game state variables

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getWarningText() {
        return warningText;
    }

    public void setWarningText(String warningText) {
        this.warningText = warningText;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public boolean isNextTake() {
        return nextTake;
    }

    public void setNextTake(boolean nextTake) {
        this.nextTake = nextTake;
    }

    public boolean isTake() {
        return take;
    }

    public void setTake(boolean take) {
        this.take = take;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
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
