package Logistics;

import java.util.ArrayList;
import java.util.List;

public class MoveCommand implements Command {
    private Game game;
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private boolean whiteTurn;
    private final List<CapturedFigurine> capturedFigurines = new ArrayList<>();

    public MoveCommand(Game game, int fromRow, int fromCol, int toRow, int toCol, boolean whiteTurn) {
        this.game = game;
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.whiteTurn = whiteTurn;
    }

    @Override
    public void execute() {
        if (Math.abs(fromRow - toRow) == 2) {
            int midRow = (fromRow + toRow) / 2;
            int midCol = (fromCol + toCol) / 2;
            Figurine captured = game.getFigurineAt(midRow, midCol);
            if (captured != null) {
                capturedFigurines.add(new CapturedFigurine(captured, midRow, midCol));
                game.setFigurineAt(midRow, midCol, null);
            }
        }
        Figurine piece = game.getFigurineAt(fromRow, fromCol);
        game.setFigurineAt(toRow, toCol, piece);
        game.setFigurineAt(fromRow, fromCol, null);
    }

    @Override
    public void undo() {
        Figurine piece = game.getFigurineAt(toRow, toCol);
        game.setFigurineAt(fromRow, fromCol, piece);
        game.setFigurineAt(toRow, toCol, null);
        for (int i = 0; i < capturedFigurines.size(); i++) {
            game.setFigurineAt(capturedFigurines.get(i).row, capturedFigurines.get(i).col, capturedFigurines.get(i).figurine);
        }
        capturedFigurines.clear();
        game.setNextTake(true);
        game.takeFigurines(game.isWhiteTurn());
        game.setWhiteTurn(whiteTurn);
        game.takeFigurines(game.isWhiteTurn());
    }
}
