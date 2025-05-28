package Graphics;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    MenuPanel menuPanel = new MenuPanel(this);
    BoardPanel boardPanel = new BoardPanel(this);
    GuidePanel guidePanel = new GuidePanel(this);

    public Frame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

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

    public void showWin(String winner) {
        WinPanel winPanel = new WinPanel(winner, this);
        restartGame(winPanel.getMenu());

        mainPanel.add(winPanel, "win");
        cardLayout.show(mainPanel, "win");
    }

    public void restartGame(boolean menu) {
        mainPanel.removeAll();

        MenuPanel newMenuPanel = new MenuPanel(this);
        BoardPanel newBoardPanel = new BoardPanel(this);
        GuidePanel newGuidePanel = new GuidePanel(this);

        mainPanel.add(newMenuPanel, "menu");
        mainPanel.add(newBoardPanel, "game");
        mainPanel.add(newGuidePanel, "guide");

        if(menu){
            cardLayout.show(mainPanel, "menu");
        } else {
            cardLayout.show(mainPanel, "game");
        }

    }

    public void showMenu(){
        cardLayout.show(mainPanel, "menu");
    }

    public void showGame() {
        cardLayout.show(mainPanel, "game");
    }

    public void showGuide(){
        cardLayout.show(mainPanel, "guide");
    }

    public void quitGame() {
        System.exit(0);
    }
}