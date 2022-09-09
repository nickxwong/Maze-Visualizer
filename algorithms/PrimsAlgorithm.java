package algorithms;

import java.util.*;
import grid.Cell;

public class PrimsAlgorithm implements Algorithm {

    private Cell[][] gridArray;
    private HashSet<AbstractMap.SimpleEntry<Integer, Integer>> neighboringCells;

    public PrimsAlgorithm(Cell[][] gridArray) {
        this.gridArray = gridArray;
        neighboringCells = new HashSet<>();
    }

    public void run() {
        // start with a random cell in the grid
        int startRow = (int) Math.floor(Math.random() * gridArray.length);
        int startCol = (int) Math.floor(Math.random() * gridArray[0].length);
        helper(startRow, startCol);
    }

    public void helper(int curRow, int curCol) {
        gridArray[curRow][curCol].setVisited(true);
        // add all unvisited neighboring cells to a set
        if (curRow > 0 && !gridArray[curRow - 1][curCol].getVisited()) { // top neighbor
            neighboringCells.add(new AbstractMap.SimpleEntry<Integer, Integer>(curRow - 1, curCol));
        }
        if (curCol > 0 && !gridArray[curRow][curCol - 1].getVisited()) { // left neighbor
            neighboringCells.add(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol - 1));
        }
        if (curRow < gridArray.length - 1 && !gridArray[curRow + 1][curCol].getVisited()) { // bottom neighbor
            neighboringCells.add(new AbstractMap.SimpleEntry<Integer, Integer>(curRow + 1, curCol));
        }
        if (curCol < gridArray[0].length - 1 && !gridArray[curRow][curCol + 1].getVisited()) { // right neighbor
            neighboringCells.add(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol + 1));
        }
        if (!neighboringCells.isEmpty()) {
            // pick a random cell from the set of neighboring cells
            AbstractMap.SimpleEntry<Integer, Integer> nextCell = getRandomNeighbor();
            neighboringCells.remove(nextCell);
            int nextRow = nextCell.getKey();
            int nextCol = nextCell.getValue();
            // remove borders between random cell and whichever adjacent cell is already part of the maze
            // because a cell can be adjacent to two different cells that are part of the maze, one is chosen at random
            boolean canContinue = false;
            do {
                int direction = (int) Math.floor(Math.random() * 4);
                switch (direction) {
                    case 0:
                        if (nextRow > 0 && gridArray[nextRow - 1][nextCol].getVisited()) {
                            gridArray[nextRow][nextCol].removeBorder("top");
                            gridArray[nextRow - 1][nextCol].removeBorder("bottom");
                            canContinue = true;
                        }
                        break;
                    case 1:
                        if (nextCol > 0 && gridArray[nextRow][nextCol - 1].getVisited()) {
                            gridArray[nextRow][nextCol].removeBorder("left");
                            gridArray[nextRow][nextCol - 1].removeBorder("right");
                            canContinue = true;
                        }
                        break;
                    case 2:
                        if (nextRow < gridArray.length - 1 && gridArray[nextRow + 1][nextCol].getVisited()) {
                            gridArray[nextRow][nextCol].removeBorder("bottom");
                            gridArray[nextRow + 1][nextCol].removeBorder("top");
                            canContinue = true;
                        }
                        break;
                    case 3:
                        if (nextCol < gridArray[0].length - 1 && gridArray[nextRow][nextCol + 1].getVisited()) {
                            gridArray[nextRow][nextCol].removeBorder("right");
                            gridArray[nextRow][nextCol + 1].removeBorder("left");
                            canContinue = true;
                        }
                        break;
                }
            } while (!canContinue);
            helper(nextRow, nextCol);
        }
    }
    
    private AbstractMap.SimpleEntry<Integer, Integer> getRandomNeighbor() {
        int randomCellIndex = (int) Math.floor(Math.random() * neighboringCells.size());
        int index = 0;
        for (AbstractMap.SimpleEntry<Integer, Integer> cell : neighboringCells) {
            if (index == randomCellIndex) {
                return cell;
            }
            index++;
        }
        return null;
    }
}
