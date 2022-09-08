package grid;

import java.awt.Color;
import algorithms.*;

public class Grid {

    Cell[][] gridArray;
    private int playerRow;
    private int playerCol;
    private boolean playerNotStarted;
    private boolean playerFinished;
    
    public Grid() {
        gridArray = new Cell[20][20];
        for (int row = 0; row < gridArray.length; row++) {
            for (int col = 0; col < gridArray[0].length; col++) {
                gridArray[row][col] = new Cell(row, col);
            }
        }

        playerRow = playerCol = 0;
        playerNotStarted = true;
        playerFinished = false;
    }

    public Cell[][] getArray() {
        return gridArray;
    }

    public void clearGrid() {
        for (int row = 0; row < gridArray.length; row++) {
            for (int col = 0; col < gridArray[0].length; col++) {
                gridArray[row][col].setToDefault();
            }
        }
        // clear player position
        gridArray[playerRow][playerCol].setBackground(Color.white);
        playerRow = playerCol = 0;
        playerNotStarted = true;
        playerFinished = false;
    }

    public void generateMaze(String algorithm) {
        clearGrid();
        Algorithm current = null;
        switch (algorithm) {
            case "Recursive Backtracking":
                current = new RecursiveBacktracking(gridArray);
                break;
            case "Eller's Algorithm":
                current = new EllersAlgorithm(gridArray);
                break;
        }
        if (current != null) {
            current.run();
            setPlayer(gridArray[playerRow][playerCol], null);
            setEndpoint(gridArray[gridArray.length - 1][gridArray[0].length - 1]);   
        }
    }

    public void setPlayer(Cell newCell, Cell oldCell) {
        newCell.setBackground(Color.yellow);
        if (oldCell != null) {
            oldCell.setBackground(Color.white);    
        }
    }

    private void setEndpoint(Cell cell) {
        cell.setBackground(Color.red);
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public boolean getPlayerNotStarted() {
        return playerNotStarted;
    }

    public boolean getPlayerFinished() {
        return playerFinished;
    }

    public void setPlayerRow(int amount) {
        playerRow += amount;
    }

    public void setPlayerCol(int amount) {
        playerCol += amount;
    }

    public void setPlayerNotStarted(boolean notStarted) {
        playerNotStarted = notStarted;
    }

    public void setPlayerFinished(boolean finished) {
        playerFinished = finished;
    }

    public void solveMaze() {
        Algorithm current = new Djikstra(this);
        current.run();
    }

}
