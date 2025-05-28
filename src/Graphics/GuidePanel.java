package Graphics;

import javax.swing.*;
import java.awt.*;

public class GuidePanel extends JPanel {

    public GuidePanel(Frame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        this.setBackground(Color.BLACK);

        Label checkersGame = new Label(Color.RED, Color.orange, 20,20,300,100,50);
        checkersGame.setText("Checkers Game");

        //SOUBORY NA TEXT

        Button menuButton = new Button(20,20,300,100,"Menu", Color.GREEN);
//        menuButton.addActionListener(e -> frame.showMenu());

        grid.insets = new Insets(10, 0, 10, 0);
        grid.gridx = 0;
        grid.gridy = 0;
        add(checkersGame, grid);

        grid.gridy = 1;
        add(menuButton, grid);
    }
}
