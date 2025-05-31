package Graphics;

import javax.swing.*;
import java.awt.*;
import Panels.*;

/**
 * The {@code Frame} class represents the main application window
 * of the game. It uses a {@link CardLayout} to switch between
 * different screens (menu, game, guide, win screen).
 */
public class Frame extends JFrame {

    /** Layout manager to handle switching between panels. */
    private CardLayout cardLayout;

    /** Main container panel that holds all other panels. */
    private JPanel mainPanel;

    /** Factory for creating game panels. */
    private PanelFactory panelFactory;

    /**
     * Constructs the main application frame.
     * Initializes the layout and all game-related panels.
     */
    public Frame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        this.panelFactory = new DefaultPanelFactory();
        JPanel menuPanel = panelFactory.createMenuPanel(this);
        JPanel boardPanel = panelFactory.createBoardPanel(this);
        JPanel guidePanel = panelFactory.createGuidePanel(this);

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(boardPanel, "game");
        mainPanel.add(guidePanel, "guide");

        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Displays the win screen with the given winner's name.
     *
     * @param winner the name of the winning player
     */
    public void showWin(String winner) {
        WinPanel winPanel = (WinPanel) panelFactory.createWinPanel(this, winner);
        restartGame(winPanel.getMenu());

        mainPanel.add(winPanel, "win");
        cardLayout.show(mainPanel, "win");
    }

    /**
     * Resets and restarts the game panels.
     *
     * @param menu if true, show the menu panel after restart;
     *             if false, show the game panel
     */
    public void restartGame(boolean menu) {
        mainPanel.removeAll();

        MenuPanel newMenuPanel = new MenuPanel(this);
        BoardPanel newBoardPanel = new BoardPanel(this);
        GuidePanel newGuidePanel = new GuidePanel(this);

        mainPanel.add(newMenuPanel, "menu");
        mainPanel.add(newBoardPanel, "game");
        mainPanel.add(newGuidePanel, "guide");

        if (menu) {
            cardLayout.show(mainPanel, "menu");
        } else {
            cardLayout.show(mainPanel, "game");
        }
    }

    /**
     * Displays the main menu panel.
     */
    public void showMenu() {
        cardLayout.show(mainPanel, "menu");
    }

    /**
     * Displays the game panel.
     */
    public void showGame() {
        cardLayout.show(mainPanel, "game");
    }

    /**
     * Displays the guide/instruction panel.
     */
    public void showGuide() {
        cardLayout.show(mainPanel, "guide");
    }

    /**
     * Quits the game and exits the application.
     */
    public void quitGame() {
        System.exit(0);
    }
}
