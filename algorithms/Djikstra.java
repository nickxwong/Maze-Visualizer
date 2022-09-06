package algorithms;

import grid.Cell;
import java.util.*;
import java.awt.Color;

public class Djikstra implements Algorithm {

    private Cell[][] gridArray;
    private Map<AbstractMap.SimpleEntry<Integer, Integer>, Integer> map;
    
    public Djikstra(Cell[][] gridArray) {
        this.gridArray = gridArray;
        map = new HashMap<>();
    }

    public void run() {
        // add starting point
        int startRow = gridArray.length - 1, startCol = gridArray[0].length - 1;
        map.put(new AbstractMap.SimpleEntry<Integer, Integer>(startRow, startCol), 0);
        calculateDistance(startRow, startCol);
    }

    public void calculateDistance(int curRow, int curCol) {
        // due to the maze generation algorithm already marking each cell as visited, Djikstra will treat "visited" cells as unvisited
        gridArray[curRow][curCol].setVisited(false);
        int curDistance = map.get(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol));
        if (curRow == 0 && curCol == 0) {
            findShortestPath(0, 0, map.get(new AbstractMap.SimpleEntry<Integer, Integer>(0, 0)));
        }
        // once the algorithm has reached the endpoint, all other paths no longer need to be explored
        if (map.containsKey(new AbstractMap.SimpleEntry<Integer, Integer>(0, 0))) {
            return;
        }
        // add distances of unvisited neighbors
        // top neighbor
        if (curRow > 0 && gridArray[curRow][curCol].getBorder("top") == 0 && gridArray[curRow - 1][curCol].getVisited()) {
            map.put(new AbstractMap.SimpleEntry<Integer, Integer>(curRow - 1, curCol), curDistance + 1);
            calculateDistance(curRow - 1, curCol);
        }
        // left neighbor
        if (curCol > 0 && gridArray[curRow][curCol].getBorder("left") == 0 && gridArray[curRow][curCol - 1].getVisited()) {
            map.put(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol - 1), curDistance + 1);
            calculateDistance(curRow, curCol - 1);
        }
        // bottom neighbor
        if (curRow < gridArray.length - 1 && gridArray[curRow][curCol].getBorder("bottom") == 0 && gridArray[curRow + 1][curCol].getVisited()) {
            map.put(new AbstractMap.SimpleEntry<Integer, Integer>(curRow + 1, curCol), curDistance + 1);
            calculateDistance(curRow + 1, curCol);
        }
        // right neighbor
        if (curCol < gridArray[0].length - 1 && gridArray[curRow][curCol].getBorder("right") == 0 && gridArray[curRow][curCol + 1].getVisited()) {
            map.put(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol + 1), curDistance + 1);
            calculateDistance(curRow, curCol + 1);
        }
    }

    private void findShortestPath(int curRow, int curCol, int distance) {
        // add current cell to path
        addToPath(curRow, curCol);
        if (curRow != gridArray.length - 1 || curCol != gridArray[0].length - 1) {
            // find neighbor with smallest distance 
            String nextNeighbor = null;
            int smallestDistance = Integer.MAX_VALUE;
            // top neighbor
            if (curRow > 0 && gridArray[curRow][curCol].getBorder("top") == 0 && !gridArray[curRow - 1][curCol].getVisited()) {
                int curDistance = map.get(new AbstractMap.SimpleEntry<Integer, Integer>(curRow - 1, curCol));
                if (curDistance < smallestDistance) {
                    smallestDistance = curDistance;
                    nextNeighbor = "top";
                }
            }
            // left neighbor
            if (curCol > 0 && gridArray[curRow][curCol].getBorder("left") == 0 && !gridArray[curRow][curCol - 1].getVisited()) {
                int curDistance = map.get(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol - 1));
                if (curDistance < smallestDistance) {
                    smallestDistance = curDistance;
                    nextNeighbor = "left";
                }
            }
            // bottom neighbor
            if (curRow < gridArray.length - 1 && gridArray[curRow][curCol].getBorder("bottom") == 0 && !gridArray[curRow + 1][curCol].getVisited()) {
                int curDistance = map.get(new AbstractMap.SimpleEntry<Integer, Integer>(curRow + 1, curCol));
                if (curDistance < smallestDistance) {
                    smallestDistance = curDistance;
                    nextNeighbor = "bottom";
                }
            }
            // right neighbor
            if (curCol < gridArray[0].length - 1 && gridArray[curRow][curCol].getBorder("right") == 0 && !gridArray[curRow][curCol + 1].getVisited()) {
                int curDistance = map.get(new AbstractMap.SimpleEntry<Integer, Integer>(curRow, curCol + 1));
                if (curDistance < smallestDistance) {
                    smallestDistance = curDistance;
                    nextNeighbor = "right";
                }
            }
            // move to next neighbor
            switch (nextNeighbor) {
                case "top":
                    findShortestPath(curRow - 1, curCol, smallestDistance);
                    break;
                case "left":
                    findShortestPath(curRow, curCol - 1, smallestDistance);
                    break;
                case "bottom":
                    findShortestPath(curRow + 1, curCol, smallestDistance);
                    break;
                case "right":
                    findShortestPath(curRow, curCol + 1, smallestDistance);
                    break;
            }    
        }
    }

    private void addToPath(int row, int col) {
        gridArray[row][col].setBackground(Color.yellow);
    }

}
