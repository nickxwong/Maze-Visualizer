package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import grid.Grid;

public class Frame {
    
    public Frame(Grid grid) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        
        JPanel header = new Header(grid);
        JPanel maze = new Maze(grid);

        frame.add(header, BorderLayout.NORTH);
        frame.add(maze, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

}
