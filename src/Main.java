import Graphics.Frame;

/**
 * The main class that serves as the entry point for the application.
 *
 * It initializes the main GUI frame and handles any exceptions
 * that might occur during the startup of the game.
 */
public class Main {

    /**
     * The main method which launches the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            new Frame();
        } catch (Exception e) {
            System.out.println("An error appeared while playing a game.");
        }
    }
}
