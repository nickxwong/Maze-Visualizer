package algorithms;

import java.util.*;
import grid.Cell;

public class EllersAlgorithm implements Algorithm {
    
    private Cell[][] gridArray;

    public EllersAlgorithm(Cell[][] gridArray) {
        this.gridArray = gridArray;
    }

    public void generate() {
        Map<Integer, Integer> cells = new HashMap<>(); // maps each cell to the set it belongs to 
        Map<Integer, Set<Integer>> sets = new HashMap<>(); // maps each set to an array of cells
        int setIndex = 0;
        int numCols = gridArray[0].length;
        // for each cell in the first row, randomly assign it to its own set or insert it into the set of the cell before it
        for (int col = 0; col < numCols; col++) {
            int setID;
            if (col != 0 && getRandom()) { // insert it into previous cell's set
                setID = cells.get(col - 1);
                mergeCell(gridArray[0][col], "left");
                mergeCell(gridArray[0][col - 1], "right");
            } else { // assign it to its own set
                setID = setIndex++;
            }
            cells.put(col, setID);
            Set<Integer> curSet = sets.getOrDefault(setID, new HashSet<Integer>());
            curSet.add(col);
            sets.put(setID, curSet);
        }
        // randomly add vertical connections to the next row (at least one per set)
        // cells that are not vertically connected are put into their own set
        for (int row = 1; row < gridArray.length; row++) {
            for (int col = 0; col < numCols; col++) {
                Set<Integer> curSet = sets.get(cells.get(col));
                if (curSet.size() == 1 || getRandom()) {
                    mergeCell(gridArray[row - 1][col], "bottom");
                    mergeCell(gridArray[row][col], "top");
                } else {
                    int setID = setIndex++;
                    cells.put(col, setID);
                    curSet.remove(Integer.valueOf(col)); // the list represents the cells of the current row for each set, so remove the cell from the set of its north neighbor
                    curSet = new HashSet<Integer>(); // create a list for its new set
                    curSet.add(col);
                    sets.put(setID, curSet);
                }
            }
            // iterate through row and randomly merge sets
            for (int col = 0; col < numCols; col++) {
                // if last row, merge all neighboring sets
                if (col != 0 && !cells.get(col).equals(cells.get(col - 1)) && (row == gridArray.length - 1 || getRandom())) {
                    mergeCell(gridArray[row][col], "left");
                    mergeCell(gridArray[row][col - 1], "right");
                    int setID = cells.get(col);
                    int prevID = cells.get(col - 1);
                    // get list of all cells in previous cell's set
                    Set<Integer> prevSet = sets.get(prevID);
                    for (int prevCell : prevSet) { // iterate through list and change their set to current cell's set
                        cells.put(prevCell, setID);
                    }
                    Set<Integer> curSet = sets.get(setID);
                    curSet.addAll(prevSet);
                    // remove previous cell's set from map and update current cell's set
                    sets.remove(prevID);
                    sets.put(setID, curSet);
                }
            }
        }
    }

    private void mergeCell(Cell cell, String direction) {
        cell.removeBorder(direction);
    }

    private boolean getRandom() {
        return Math.floor(Math.random() * 2 + 0) == 0.0;
    }

}
