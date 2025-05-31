package Panels;

import javax.swing.*;
import Graphics.*;

/**
 * Implementation of the PanelFactory interface that creates standard panels
 * used in the application, such as menu, board, win, and guide panels.
 *
 * This factory provides concrete instances of each panel type.
 */
public class DefaultPanelFactory implements PanelFactory {

    /**
     * Creates and returns the menu panel.
     *
     * @param frame the main frame to which the panel belongs
     * @return a new MenuPanel instance
     */
    @Override
    public JPanel createMenuPanel(Frame frame) {
        return new MenuPanel(frame);
    }

    /**
     * Creates and returns the board panel.
     *
     * @param frame the main frame to which the panel belongs
     * @return a new BoardPanel instance
     */
    @Override
    public JPanel createBoardPanel(Frame frame) {
        return new BoardPanel(frame);
    }

    /**
     * Creates and returns the win panel showing the winner.
     *
     * @param frame the main frame to which the panel belongs
     * @param winner the winner's name to display
     * @return a new WinPanel instance with the winner information
     */
    @Override
    public JPanel createWinPanel(Frame frame, String winner) {
        return new WinPanel(winner, frame);
    }

    /**
     * Creates and returns the guide panel.
     *
     * @param frame the main frame to which the panel belongs
     * @return a new GuidePanel instance
     */
    @Override
    public JPanel createGuidePanel(Frame frame) {
        return new GuidePanel(frame);
    }
}
