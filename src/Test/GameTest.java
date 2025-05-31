package Test;

import Logistics.*;

/**
 * A set of manual test methods for the {@link Game} class.
 * <p>
 * Each test is designed to verify specific game logic such as movement rules,
 * capturing behavior, and board loading functionality.
 * <p>
 * Tests are executed from the {@code main} method and results are printed to the console.
 */
public class GameTest {

    /**
     * Main entry point to run all test methods.
     * Prints the results of each test to the console.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        testCannotMoveIfCaptureAvailable();
        testMultipleCapturesQueen();
        testQueenMovementWithoutCapture();
        testCannotCaptureOwnPiece();
        testLoadDefaultPosition();
    }

    /**
     * Tests that a piece cannot move normally if there is a capture available.
     * Sets up a board scenario where a capture is possible and verifies that
     * a valid move without capturing is not allowed.
     */
    public static void testCannotMoveIfCaptureAvailable() {
        Game game = new Game();
        game.clearBoard();

        game.setFigurineAt(3, 3, new Figurine(false, false));
        game.setFigurineAt(4, 4, new Figurine(true, false));
        game.setFigurineAt(5, 4, new Figurine(true, false));

        boolean result = game.hasValidMove(game.getFigurineAt(5,4),5, 4);

        System.out.println("testCannotMoveIfCaptureAvailable: " + (result ? "✅ OK" : "❌ FAILED"));
    }

    /**
     * Tests multiple captures performed manually by a queen piece.
     * The test verifies that the queen can perform sequential captures
     * by moving stepwise and that the captures are correctly recognized.
     */
    public static void testMultipleCapturesQueen() {
        Game game = new Game();
        game.clearBoard();

        Figurine white = new Figurine(true,true);
        Figurine black1 = new Figurine(false,true);
        Figurine black2 = new Figurine(false,true);

        game.setFigurineAt(5, 0, white);
        game.setFigurineAt(4, 1, black1);
        game.setFigurineAt(2, 3, black2);

        boolean canTake1 = game.checkMovement(5, 0, 3, 2, 1, true);
        game.makeMove(5, 0, 3, 2,true); // first jump

        boolean canTake2 = game.checkMovement(3, 2, 1, 4, 1, true);
        game.makeMove(3, 2, 1, 4,true); // second jump

        boolean result = canTake1 && canTake2 && game.getFigurineAt(1, 4) != null && game.getFigurineAt(1, 4).isWhite();

        System.out.println("testMultipleCapturesManual: " + (result ? "✅ OK" : "❌ FAILED"));
    }

    /**
     * Tests that a queen can only move exactly one square diagonally in any direction
     * when not capturing. It verifies that moving more than one square without capture is invalid.
     */
    public static void testQueenMovementWithoutCapture() {
        Game game = new Game();
        game.clearBoard();

        Figurine whiteQueen = new Figurine(true,true);
        game.setFigurineAt(5, 2, whiteQueen);

        boolean invalid = game.checkMovement(5, 2, 3, 0, 1, false);
        boolean valid = game.checkMovement(5, 2, 4, 1, 1, false);

        System.out.println("testQueenMovementOneSquareOnly: " + (!invalid && valid ? "✅ OK" : "❌ FAILED"));
    }

    /**
     * Tests that a piece cannot capture a figurine of the same color.
     * Sets up a scenario where the player tries to capture their own piece
     * and verifies that the move is rejected with the correct warning message.
     */
    public static void testCannotCaptureOwnPiece() {
        Game game = new Game();
        game.setFigurineAt(4, 3, new Figurine(true, false)); // white piece
        game.setFigurineAt(3, 2, new Figurine(true, false)); // white piece
        game.setWhiteTurn(true);

        boolean result = game.checkMovement(4, 3, 2, 1, 1, true);
        boolean passed = !result && "You can't take your figurine".equals(game.getWarningText());

        printResult("testCannotCaptureOwnPiece", passed);
    }

    /**
     * Tests loading the default position from a file.
     * Writes a test default position to the file system, loads it into the game,
     * and verifies that pieces are correctly placed with the expected colors.
     */
    public static void testLoadDefaultPosition() {
        Game game = new Game();

        try {
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
            System.out.println("Error loading or writing the file: " + e.getMessage());
        }
    }

    /**
     * Utility method to print formatted test results.
     * @param testName The name of the test
     * @param passed Whether the test passed or failed
     */
    private static void printResult(String testName, boolean passed) {
        System.out.println(testName + ": " + (passed ? "✅ OK" : "❌ FAILED"));
    }
}
