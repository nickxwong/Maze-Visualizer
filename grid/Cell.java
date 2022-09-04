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
    private boolean visited;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        setToDefault();
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

    public boolean getVisited() {
        return visited;
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

    public void setToDefault() {
        topBorder = leftBorder = bottomBorder = rightBorder = 1;
        setBorders();
        visited = false;
    }
    
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    private void setBorders() {
        setBorder(BorderFactory.createMatteBorder(topBorder, leftBorder, bottomBorder, rightBorder, Color.black));
    }

    public void removeBorder(String direction) {
        switch (direction) {
            case "top": // north
                topBorder = 0;
                break;
            case "left": // west
                leftBorder = 0;
                break;
            case "bottom": // south
                bottomBorder = 0;
                break;
            case "right": // east
                rightBorder = 0;
                break;
        }
        setBorders();
    }
}
