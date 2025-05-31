package Graphics;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The {@code GuidePanel} class displays the game rules and a button
 * to return to the main menu. It reads rules from a text file and shows
 * them inside a scrollable text area.
 */
public class GuidePanel extends JPanel {

    /** Stores the content of the rules loaded from a file. */
    private String rules;

    /**
     * Constructs a new {@code GuidePanel}.
     *
     * @param frame the main application frame used to switch panels
     *
     * Here Chat GPT helped me ONLY with creating GritBagLayout.
     */
    public GuidePanel(Frame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        this.setBackground(Color.BLACK);

        // Create a scrollable text area with loaded guide text
        TextArea textArea = new TextArea(loadGuideText());

        // Create a button to return to the main menu
        Button menuButton = new Button(20, 20, 300, 100, "Menu", Color.GREEN, 60);
        menuButton.addActionListener(e -> frame.showMenu());

        // Add scroll pane containing the text area
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(600, 680));

        grid.insets = new Insets(10, 0, 10, 0);
        grid.gridx = 0;
        grid.gridy = 0;
        add(scroll, grid);

        grid.gridy = 1;
        add(menuButton, grid);
    }

    /**
     * Loads the game rules from the {@code res/rules.txt} file.
     *
     * @return the rules as a single string, or an error message if loading fails
     */
    public String loadGuideText() {
        String line;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("res/rules.txt"))) {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
                rules = sb.toString();
            }
            return rules;
        } catch (Exception e) {
            return "Error loading rules";
        }
    }
}
