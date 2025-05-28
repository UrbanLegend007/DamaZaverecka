package Graphics;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(Frame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        this.setBackground(Color.BLACK);

        Label checkersGame = new Label(Color.RED, Color.orange, 20,20,300,100,70);
        checkersGame.setText("Checkers Game");

        Button startButton = new Button(20,20,300,100,"Start", Color.GREEN);
        Button guideButton = new Button(20,20,300,100,"Guide", Color.YELLOW);
        Button exitButton = new Button(20,90,300,100,"Quit", Color.RED);

        startButton.addActionListener(e -> frame.showGame());
        guideButton.addActionListener(e -> frame.showGuide());
        exitButton.addActionListener(e -> frame.quitGame());

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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }
}
