package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import grid.Grid;
import grid.Cell;

public class Maze extends JPanel {

    Grid grid;
    Cell[][] gridArray;
    private final Action upAction;
    private final Action leftAction;
    private final Action downAction;
    private final Action rightAction;
    
    public Maze(Grid grid) {
        this.grid = grid;
        gridArray = grid.getArray();

        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        setLayout(new GridLayout(gridArray.length, gridArray[0].length));
        addGrid();

        upAction = new UpAction();
        getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
        getActionMap().put("upAction", upAction);

        leftAction = new LeftAction();
        getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
        getActionMap().put("leftAction", leftAction);

        downAction = new DownAction();
        getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
        getActionMap().put("downAction", downAction);

        rightAction = new RightAction();
        getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
        getActionMap().put("rightAction", rightAction);
    }

    private void addGrid() {
        for (int row = 0; row < gridArray.length; row++) {
            for (int col = 0; col < gridArray[0].length; col++) {
                add(gridArray[row][col]);
            }
        }
    }

    public class UpAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int playerRow = grid.getPlayerRow();
            int playerCol = grid.getPlayerCol();
            if (gridArray[playerRow][playerCol].getBorder("top") == 0) {
                grid.setPlayer(gridArray[playerRow - 1][playerCol], gridArray[playerRow][playerCol]);
                grid.setPlayerRow(-1);
            }
        }

    }

    public class LeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int playerRow = grid.getPlayerRow();
            int playerCol = grid.getPlayerCol();
            if (gridArray[playerRow][playerCol].getBorder("left") == 0) {
                grid.setPlayer(gridArray[playerRow][playerCol - 1], gridArray[playerRow][playerCol]);
                grid.setPlayerCol(-1);
            }
        }

    }

    public class DownAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int playerRow = grid.getPlayerRow();
            int playerCol = grid.getPlayerCol();
            if (gridArray[playerRow][playerCol].getBorder("bottom") == 0) {
                grid.setPlayer(gridArray[playerRow + 1][playerCol], gridArray[playerRow][playerCol]);
                grid.setPlayerRow(1);
            }
        }

    }

    public class RightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int playerRow = grid.getPlayerRow();
            int playerCol = grid.getPlayerCol();
            if (gridArray[playerRow][playerCol].getBorder("right") == 0) {
                grid.setPlayer(gridArray[playerRow][playerCol + 1], gridArray[playerRow][playerCol]);
                grid.setPlayerCol(1);
            }
        }

    }

}
