package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import grid.Grid;

public class Frame {

    JFrame frame;
    
    public Frame(Grid grid) {
        frame = new JFrame();
        frame.setTitle("Maze Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        
        JPanel header = new Header(this, grid);
        JPanel maze = new Maze(grid);

        frame.add(header, BorderLayout.NORTH);
        frame.add(maze, BorderLayout.CENTER);
        frame.pack();
        maze.requestFocusInWindow();
        frame.setVisible(true);

        runTutorial(false);
    }

    public void runTutorial(boolean skipPrompt) {
        Object[] options = {"OK", "Skip"};
        int result = (skipPrompt) ? 0 : JOptionPane.showConfirmDialog(frame, "Welcome! Would you like a tutorial?", "Tutorial", JOptionPane.YES_NO_OPTION);  
        if (result == 0) {
            result = JOptionPane.showOptionDialog(frame, "To get started, first select from our collection of maze generation algorithms.", "Tutorial (1/3)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
            if (result == 0) {
                result = JOptionPane.showOptionDialog(frame, "Then, use your arrow keys to try and solve the maze." +
                " You start at the top-left corner and you're trying to get to the bottom-right.", "Tutorial (2/3)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
                if (result == 0) {
                    JOptionPane.showOptionDialog(frame, "Once you start, the program will run a pathfinding algorithm. Try and beat the program to the end!", "Tutorial (3/3)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
                } 
            } 
        } 
    }

}
