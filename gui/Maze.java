package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.GridLayout;
import grid.Grid;
import grid.Cell;

public class Maze extends JPanel {

    Cell[][] gridArray;
    
    public Maze(Grid grid) {
        gridArray = grid.getArray();

        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        setLayout(new GridLayout(gridArray.length, gridArray[0].length));
        addGrid();
    }

    private void addGrid() {
        for (int row = 0; row < gridArray.length; row++) {
            for (int col = 0; col < gridArray[0].length; col++) {
                add(gridArray[row][col]);
            }
        }
    }

}
