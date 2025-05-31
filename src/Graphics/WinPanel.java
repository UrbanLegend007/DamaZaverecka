package Graphics;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code WinPanel} class represents a UI screen displayed when a player wins the game.
 * <p>
 * It shows the winner, and provides three interactive buttons:
 * <ul>
 *     <li><b>Restart</b> - restarts the game</li>
 *     <li><b>Menu</b> - returns to the main menu</li>
 *     <li><b>Exit</b> - closes the application</li>
 * </ul>
 * <p>
 * The panel uses a {@link GridBagLayout} for flexible component placement and is styled with
 * a black background and custom colors for each label and button.
 */
public class WinPanel extends JPanel {

    /**
     * Indicates whether the user has chosen to return to the menu.
     */
    private boolean menu = false;

    /**
     * Constructs a new {@code WinPanel} showing the game winner and options for next actions.
     *
     * @param winner the name of the winning player ("Black" or "White")
     * @param frame the main application frame used to navigate between panels
     */
    public WinPanel(String winner, Frame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        setBackground(Color.BLACK);

        // Determine label color based on the winner
        Color color = (winner == "Black") ? Color.RED : Color.WHITE;

        // Create and configure the winner label
        Label winnerLabel = new Label(color, Color.BLUE, 20, 20, 300, 100, 70);
        winnerLabel.setText(winner + " wins!");

        // Create and configure the restart button
        Button restartButton = new Button(20, 120, 300, 100, "Restart", Color.GREEN, 60);
        restartButton.addActionListener(e -> frame.showGame());

        // Create and configure the menu button
        Button menuButton = new Button(20, 220, 300, 100, "Menu", Color.YELLOW, 60);
        menuButton.addActionListener(e -> frame.showMenu());

        // Create and configure the exit button
        Button exitButton = new Button(20, 320, 300, 100, "Quit", Color.RED, 60);
        exitButton.setText("Exit Game");
        exitButton.addActionListener(e -> { System.exit(0); });

        // Layout configuration and component adding
        grid.insets = new Insets(10, 0, 10, 0);
        grid.gridx = 0;
        grid.gridy = 0;
        add(winnerLabel, grid);

        grid.gridy = 1;
        add(restartButton, grid);

        grid.gridy = 2;
        add(menuButton, grid);

        grid.gridy = 3;
        add(exitButton, grid);
    }

    /**
     * Returns whether the menu option has been selected.
     *
     * @return {@code true} if menu was selected; {@code false} otherwise
     */
    public boolean isMenu() {
        return menu;
    }

    /**
     * Sets the value of the menu flag.
     *
     * @param menu {@code true} if menu was selected; {@code false} otherwise
     */
    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    /**
     * Gets the value of the menu flag.
     * (Duplicate of {@link #isMenu()}, included for compatibility.)
     *
     * @return {@code true} if menu was selected; {@code false} otherwise
     */
    public boolean getMenu() {
        return menu;
    }
}
