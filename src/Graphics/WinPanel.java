package Graphics;

import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {

    private boolean menu = false;
    public WinPanel(String winner, Frame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        setBackground(Color.BLACK);
        Color color = (winner == "Black") ? Color.RED : Color.WHITE;
        Label winnerLabel = new Label(color, Color.BLUE, 20,20,300,100,70);
        winnerLabel.setText(winner + " wins!");
        Button restartButton = new Button(20,120,300,100,"Restart", Color.GREEN);
        restartButton.addActionListener(e -> frame.showGame());
        Button menuButton = new Button(20,220,300,100,"Menu", Color.YELLOW);
        menuButton.addActionListener(e -> frame.showMenu());
        Button exitButton = new Button(20,320,300,100,"Quit", Color.RED);
        exitButton.setText("Exit Logistik.Game");
        exitButton.addActionListener(e -> {System.exit(0);});

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

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }
    public boolean getMenu(){
        return menu;
    }
}
