package Panels;

import javax.swing.*;
import Graphics.*;

public class DefaultPanelFactory implements PanelFactory {

    @Override
    public JPanel createMenuPanel(Frame frame) {
        return new MenuPanel(frame);
    }

    @Override
    public JPanel createBoardPanel(Frame frame) {
        return new BoardPanel(frame);
    }

    @Override
    public JPanel createWinPanel(Frame frame, String winner, Runnable onRestart) {
        return new WinPanel(winner, (Frame) onRestart);
    }

    @Override
    public JPanel createGuidePanel(Frame frame) {
        return new GuidePanel(frame);
    }
}

