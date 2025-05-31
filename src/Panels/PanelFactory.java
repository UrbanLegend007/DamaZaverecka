package Panels;

import Graphics.*;
import javax.swing.*;

/**
 * Interface defining a factory for creating different types of panels used in the application.
 *
 * This interface allows for the creation of various panels such as menu, board, win, and guide panels,
 * which are part of the GUI framework.
 */
public interface PanelFactory {

    /**
     * Creates and returns the menu panel.
     *
     * @param frame the main application frame that owns the panel
     * @return a new JPanel representing the menu
     */
    JPanel createMenuPanel(Frame frame);

    /**
     * Creates and returns the game board panel.
     *
     * @param frame the main application frame that owns the panel
     * @return a new JPanel representing the game board
     */
    JPanel createBoardPanel(Frame frame);

    /**
     * Creates and returns the win panel which displays the winner.
     *
     * @param frame the main application frame that owns the panel
     * @param winner the name or identifier of the winner to display
     * @return a new JPanel representing the win screen
     */
    JPanel createWinPanel(Frame frame, String winner);

    /**
     * Creates and returns the guide panel.
     *
     * @param frame the main application frame that owns the panel
     * @return a new JPanel representing the game guide or instructions
     */
    JPanel createGuidePanel(Frame frame);
}
