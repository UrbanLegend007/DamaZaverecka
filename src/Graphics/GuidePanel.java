package Graphics;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GuidePanel extends JPanel {

    private String rules;

    public GuidePanel(Frame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        this.setBackground(Color.BLACK);

        TextArea textArea = new TextArea(loadGuideText());

        Button menuButton = new Button(20,20,300,100,"Menu", Color.GREEN,60);
        menuButton.addActionListener(e -> frame.showMenu());

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(600, 600));

        grid.insets = new Insets(10, 0, 10, 0);
        grid.gridx = 0;
        grid.gridy = 0;
        add(scroll, grid);

        grid.gridy = 1;
        add(menuButton, grid);
    }

    public String loadGuideText(){
        String line;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("res/rules.txt"))){
            while((line = reader.readLine()) != null){
                sb.append(line).append("\n");
                rules = sb.toString();
            }
            return rules;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Nepodařilo se načíst pravidla hry. Zkontrolujte prosím soubor rules.txt.", "Chyba při načítání", JOptionPane.ERROR_MESSAGE);
            return "";
        } catch (Exception e){
            return "Error loading rules";
        }
    }
}
