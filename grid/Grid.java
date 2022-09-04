package grid;

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

}
