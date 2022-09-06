package algorithms;

import java.util.*;
import grid.Cell;

public class RecursiveBacktracking implements Algorithm {

    private Cell[][] gridArray;
    
    public RecursiveBacktracking(Cell[][] gridArray) {
        this.gridArray = gridArray;
    }

    public void run() {
        Stack<AbstractMap.SimpleEntry<Integer, Integer>> visitedCells = new Stack<>();
        helper(visitedCells, 0, 0);
    }

    private void helper(Stack<AbstractMap.SimpleEntry<Integer, Integer>> visitedCells, int curRow, int curCol) {
        // push current cell onto stack
        visitedCells.push(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol));
        // add all unvisited neighbors to an ArrayList
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> unvisitedNeighbors = new ArrayList<>();
        if (curRow > 0 && !gridArray[curRow - 1][curCol].getVisited()) { // top neighbor
            unvisitedNeighbors.add(new AbstractMap.SimpleEntry<Integer, Integer>(curRow - 1, curCol));
        }
        if (curCol > 0 && !gridArray[curRow][curCol - 1].getVisited()) { // left neighbor
            unvisitedNeighbors.add(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol - 1));
        }
        if (curRow < gridArray[0].length - 1 && !gridArray[curRow + 1][curCol].getVisited()) { // bottom neighbor
            unvisitedNeighbors.add(new AbstractMap.SimpleEntry<Integer, Integer>(curRow + 1, curCol));
        }
        if (curCol < gridArray.length - 1 && !gridArray[curRow][curCol + 1].getVisited()) { // right neighbor
            unvisitedNeighbors.add(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol + 1));
        }
        // select a cell to visit next
        AbstractMap.SimpleEntry<Integer, Integer> nextToVisit;
        if (unvisitedNeighbors.size() == 0) { // if ArrayList is empty (meaning all neighbors are visited), backtrack
            visitedCells.pop(); // remove the current cell from stack
            nextToVisit = visitedCells.pop();
        } else { // if not, choose an unvisited neighbor at random
            nextToVisit = unvisitedNeighbors.get(getRandomNumber(0, unvisitedNeighbors.size()));
        }
        int nextRow = nextToVisit.getKey();
        int nextCol = nextToVisit.getValue();
        if (nextRow != 0 || nextCol != 0) { // if nextRow & nextCol == 0, we've backtracked to the starting cell
            // determine direction
            if (nextRow < curRow) { // going to top neigbhor
                setVisited(gridArray[curRow][curCol], "top");
                setCurrent(gridArray[nextRow][nextCol], "bottom");
            } else if (nextCol < curCol) { // going to left neighbor
                setVisited(gridArray[curRow][curCol], "left");
                setCurrent(gridArray[nextRow][nextCol], "right");
            } else if (nextRow > curRow) { // going to bottom neighbor
                setVisited(gridArray[curRow][curCol], "bottom");
                setCurrent(gridArray[nextRow][nextCol], "top");
            } else if (nextCol > curCol) { // going to right neighbor
                setVisited(gridArray[curRow][curCol], "right");
                setCurrent(gridArray[nextRow][nextCol], "left");
            }
            helper(visitedCells, nextRow, nextCol);
        }
    }

    private void setVisited(Cell cell, String directionTo) {
        cell.setVisited(true);
        cell.removeBorder(directionTo);
    }

    private void setCurrent(Cell cell, String directionFrom) {
        cell.removeBorder(directionFrom);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
