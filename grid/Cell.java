package grid;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Color;

public class Cell extends JPanel {
    private final int row;
    private final int col;
    private int topBorder;
    private int leftBorder;
    private int bottomBorder;
    private int rightBorder;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        topBorder = leftBorder = bottomBorder = rightBorder = 1;
        setBorders();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return col;
    }

    public int getBorder(String direction) {
        switch (direction) {
            case "top":
                return topBorder;
            case "left":
                return leftBorder;
            case "bottom":
                return bottomBorder;
            case "right":
                return rightBorder;
            default:
                return -1;
        }
    }

    private void setBorders() {
        setBorder(BorderFactory.createMatteBorder(topBorder, leftBorder, bottomBorder, rightBorder, Color.black));
    }
}
