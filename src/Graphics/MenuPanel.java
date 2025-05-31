package Graphics;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code MenuPanel} class represents the main menu of the Checkers game.
 * It provides buttons to start the game, view the game guide, or quit the application.
 */
public class MenuPanel extends JPanel {

    /**
     * Constructs a new {@code MenuPanel} and sets up the menu UI.
     *
     * @param frame the main application frame used to navigate between views
     */
    public MenuPanel(Frame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        this.setBackground(Color.BLACK);

        // Title label of the game
        Label checkersGame = new Label(Color.RED, Color.ORANGE, 20, 20, 300, 100, 70);
        checkersGame.setText("Checkers Game");

        // Buttons for user actions
        Button startButton = new Button(20, 20, 300, 100, "Start", Color.GREEN, 60);
        Button guideButton = new Button(20, 20, 300, 100, "Guide", Color.YELLOW, 60);
        Button exitButton = new Button(20, 90, 300, 100, "Quit", Color.RED, 60);

        // Button event listeners
        startButton.addActionListener(e -> frame.showGame());
        guideButton.addActionListener(e -> frame.showGuide());
        exitButton.addActionListener(e -> frame.quitGame());

        // Layout setup
        grid.insets = new Insets(10, 0, 10, 0);
        grid.gridx = 0;
        grid.gridy = 0;
        add(checkersGame, grid);

        grid.gridy = 1;
        add(startButton, grid);

        grid.gridy = 2;
        add(guideButton, grid);

        grid.gridy = 3;
        add(exitButton, grid);
    }

    /**
     * Returns the preferred size of this panel.
     *
     * @return the preferred dimensions for the menu panel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }
}
