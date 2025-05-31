package Test;

import Logistics.*;

public class GameTest {

    public static void main(String[] args) {
        testCannotMoveIfCaptureAvailable();
        testMultipleCapturesQueen();
        testQueenMovementWithoutCapture();
        testCannotCaptureOwnPiece();
        testLoadDefaultPosition();
    }

    public static void testCannotMoveIfCaptureAvailable() {
        Game game = new Game();
        game.clearBoard();

        game.setFigurineAt(3, 3, new Figurine(false, false));
        game.setFigurineAt(4, 4, new Figurine(true, false));
        game.setFigurineAt(5, 4, new Figurine(true, false));

        boolean result = game.hasValidMove(game.getFigurineAt(5,4),5, 4);

        System.out.println("testCannotMoveIfCaptureAvailable: " + (result ? "✅ OK" : "❌ FAILED"));

    }

    public static void testMultipleCapturesQueen() {
        Game game = new Game();
        game.clearBoard();

        Figurine white = new Figurine(true,true);
        Figurine black1 = new Figurine(false,true);
        Figurine black2 = new Figurine(false,true);

        game.setFigurineAt(5, 0, white);     // bílá figurka
        game.setFigurineAt(4, 1, black1);    // černá figurka 1
        game.setFigurineAt(2, 3, black2);    // černá figurka 2

        // První tah
        boolean canTake1 = game.checkMovement(5, 0, 3, 2, 1, true);
        game.makeMove(5, 0, 3, 2,true); // první skok

        // Druhý tah ve stejném kole (ručně kliknuté)
        boolean canTake2 = game.checkMovement(3, 2, 1, 4, 1, true);
        game.makeMove(3, 2, 1, 4,true); // druhý skok

        boolean result = canTake1 && canTake2 && game.getFigurineAt(1, 4) != null && game.getFigurineAt(1, 4).isWhite();

        System.out.println("testMultipleCapturesManual: " + (result ? "✅ OK" : "❌ FAILED"));
    }

    public static void testQueenMovementWithoutCapture() {
        Game game = new Game();
        game.clearBoard();

        Figurine whiteQueen = new Figurine(true,true);
        game.setFigurineAt(5, 2, whiteQueen);

        // Zkusí jít o 2 pole diagonálně – nesmí
        boolean invalid = game.checkMovement(5, 2, 3, 0, 1, false);

        // Zkusí jít o 1 pole dozadu – smí
        boolean valid = game.checkMovement(5, 2, 4, 1, 1, false);

        System.out.println("testQueenMovementOneSquareOnly: " + (!invalid && valid ? "✅ OK" : "❌ FAILED"));
    }

    public static void testCannotCaptureOwnPiece() {
        Game game = new Game();
        game.setFigurineAt(4, 3, new Figurine(true, false)); // bílá
        game.setFigurineAt(3, 2, new Figurine(true, false)); // bílá
        game.setWhiteTurn(true);

        boolean result = game.checkMovement(4, 3, 2, 1, 1, true);
        boolean passed = !result && "You can't take your figurine".equals(game.getWarningText());

        printResult("testCannotCaptureOwnPiece", passed);
    }

    public static void testLoadDefaultPosition() {
        Game game = new Game();

        try {
            // Simulace zápisu do souboru
            java.io.FileWriter writer = new java.io.FileWriter("res/defaultPosition.txt");
            writer.write("true,1,1\n" +
                    "true,3,1\n" +
                    "true,2,2\n" +
                    "true,1,3\n" +
                    "true,3,3\n" +
                    "true,2,4\n" +
                    "true,1,5\n" +
                    "true,3,5\n" +
                    "true,2,6\n" +
                    "true,1,7\n" +
                    "true,3,7\n" +
                    "true,2,8\n" +
                    "false,7,1\n" +
                    "false,8,2\n" +
                    "false,6,2\n" +
                    "false,7,3\n" +
                    "false,8,4\n" +
                    "false,6,4\n" +
                    "false,7,5\n" +
                    "false,8,6\n" +
                    "false,6,6\n" +
                    "false,7,7\n" +
                    "false,8,8\n" +
                    "false,6,8");
            writer.close();

            String result = game.loadDefaultPosition();
            Figurine f1 = game.getFigurineAt(0, 0);
            Figurine f2 = game.getFigurineAt(6, 0);
            boolean passed = "Default position loaded".equals(result)
                    && f1 != null && f1.isWhite()
                    && f2 != null && !f2.isWhite();

            printResult("testLoadDefaultPosition", passed);

        } catch (Exception e) {
            printResult("testLoadDefaultPosition", false);
            System.out.println("Chyba při načítání nebo zápisu souboru: " + e.getMessage());
        }
    }

    private static void printResult(String testName, boolean passed) {
        System.out.println(testName + ": " + (passed ? "✅ OK" : "❌ FAILED"));
    }
}

