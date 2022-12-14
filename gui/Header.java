package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Font;
import grid.Grid;

public class Header extends JPanel {
    
    public Header(Frame frame, Grid grid) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Maze Visualizer");
        title.setFont(new Font("Sans-Serif", Font.PLAIN, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        JPanel programOptions = new JPanel();

        String[] generationAlgo = {"Recursive Backtracking", "Eller's Algorithm", "Prim's Algorithm"};
        JComboBox<String> algoSelector = new JComboBox<>(generationAlgo);
        programOptions.add(algoSelector);

        JButton generateButton = new JButton();
        generateButton.setText("Generate maze");
        generateButton.setFocusable(false);
        generateButton.addActionListener(e -> grid.generateMaze((String) algoSelector.getSelectedItem()));
        programOptions.add(generateButton);

        JButton tutorialButton = new JButton();
        tutorialButton.setText("Tutorial");
        tutorialButton.setFocusable(false);
        tutorialButton.addActionListener(e -> frame.runTutorial(true));
        programOptions.add(tutorialButton);

        this.add(programOptions);
    }

}
