package grid;

import algorithms.*;

public class Grid {

    Cell[][] gridArray;
    
    public Grid() {
        gridArray = new Cell[20][20];
        for (int row = 0; row < gridArray.length; row++) {
            for (int col = 0; col < gridArray[0].length; col++) {
                gridArray[row][col] = new Cell(row, col);
            }
        }
    }

    public Cell[][] getArray() {
        return gridArray;
    }

    private void clearGrid() {
        for (int row = 0; row < gridArray.length; row++) {
            for (int col = 0; col < gridArray[0].length; col++) {
                gridArray[row][col].setToDefault();
            }
        }
    }

    public void generateMaze(String algorithm) {
        clearGrid();
        Algorithm current = null;
        switch (algorithm) {
            case "Recursive Backtracking":
                current = new RecursiveBacktracking(gridArray);
                break;
        }
        if (current != null) {
            current.generate();    
        }
    }

}
