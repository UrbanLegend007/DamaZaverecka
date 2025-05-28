package Panels;

import Graphics.*;
import javax.swing.*;

public interface PanelFactory {
    JPanel createMenuPanel(Frame frame);
    JPanel createBoardPanel(Frame frame);
    JPanel createWinPanel(Frame frame, String winner);
    JPanel createGuidePanel(Frame frame);
}

