package grid;

import java.awt.Color;
import algorithms.*;

public class Grid {

    Cell[][] gridArray;
    private int playerRow;
    private int playerCol;
    private boolean playerNotStarted;
    private boolean playerFinished;
    private Djikstra pathfindingAlgo;
    
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
        // clear pathfinding algorithm if needed
        if (pathfindingAlgo != null) {
            pathfindingAlgo.cancel();
        }
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
        Algorithm generationAlgorithm = null;
        switch (algorithm) {
            case "Recursive Backtracking":
                generationAlgorithm = new RecursiveBacktracking(gridArray);
                break;
            case "Eller's Algorithm":
                generationAlgorithm = new EllersAlgorithm(gridArray);
                break;
            case "Prim's Algorithm":
                generationAlgorithm = new PrimsAlgorithm(gridArray); 
        }
        if (generationAlgorithm != null) {
            generationAlgorithm.run();
            setPlayer(gridArray[playerRow][playerCol], null);
            setEndpoint(gridArray[gridArray.length - 1][gridArray[0].length - 1]);   
        }
    }

    public void setPlayer(Cell newCell, Cell oldCell) {
        newCell.setBackground(new Color(209, 224, 224));
        if (oldCell != null) {
            if (oldCell.getVisited()) { // if the cell has been visited by the pathfinding algorithm
                oldCell.setBackground(new Color(225, 234, 234)); 
            } else { // if the cell hasn't been visited by the pathfinding algorithm
                oldCell.setBackground(new Color(255, 255, 204));
            }
            
        }
    }

    private void setEndpoint(Cell cell) {
        cell.setBackground(new Color(240, 240, 245));
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
        pathfindingAlgo = new Djikstra(this);
        pathfindingAlgo.run();
    }

}
