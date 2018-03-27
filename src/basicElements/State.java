package basicElements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {

    public static final State GOAL_3_3 = createGoalState(3);
    public static final State GOAL_4_4 = createGoalState(4);

    private int dimension;
    private byte[] allCells;
    private int hashCode = -1;

    public State() {}

    public State(byte[] cells) {
        allCells = new byte[cells.length];
        for (int i = 0; i < allCells.length; i++) {
        	allCells[i] = cells[i];
        }
        dimension = (int) Math.sqrt(cells.length);
    }

    public static State createGoalState(int dimension) {
        int nbrOfCells = dimension * dimension;
        byte[] goalCells = new byte[nbrOfCells];
        for (byte i = 1; i < goalCells.length; i++) {
            goalCells[i - 1] = i;
        }
        goalCells[nbrOfCells - 1] = 0;

        return new State(goalCells);
    }

    public byte getCellValue(CellLocation cell) {
        return getCellValue(cell.getRowIndex(), cell.getColumnIndex());
    }

    public byte getCellValue(int rowIndex, int columnIndex){
        return allCells[rowIndex * dimension + columnIndex];
    }

    public void setCellValue(CellLocation cell, byte value) {
        allCells[cell.getRowIndex() * dimension + cell.getColumnIndex()] = value;
        reset();
    }

    public byte[] getAllCells() {
        return allCells;
    }

    private CellLocation getEmptyCellLocation() {
        for (int i = 0; i < allCells.length; i++) {
            if (allCells[i] == 0) {
                return new CellLocation(i / dimension, i % dimension);
            }
        }
        throw new RuntimeException("no empty cell found");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            State s2 = (State)obj;
            return Arrays.equals(allCells, s2.allCells);
        }

        return false;
    }

    @Override
    // On applique un algorithme securise de changement de hashcode
    public int hashCode() {
        // Change the hashCode of a state
        if (hashCode == -1){
            // state's hashCode will only be redefined once after state creation (-1 upon creation)
            // Prime numbers (e.g. 11, 13) are used to build the hashCode as it increases the
            // chance of uniqueness, making the resulting hashCode more secure
            int result = 11;
            for (int i = 0; i < allCells.length; i++) {
                result = 13 * result + allCells[i];
            }
            hashCode = result;
        }
        return hashCode;
    }

    public List<Action> getPossibleActions() {

        List<Action> actions = new ArrayList<Action>();

        CellLocation emptyCell = getEmptyCellLocation();

        if (emptyCell.getRowIndex() > 0) {
            CellLocation upCell = new CellLocation(emptyCell.getRowIndex() - 1,
                                                   emptyCell.getColumnIndex());
            actions.add(new Action(upCell, Move.DOWN));
        }

        if (emptyCell.getRowIndex() < dimension - 1) {
            CellLocation downCell = new CellLocation(emptyCell.getRowIndex() + 1,
                                                   emptyCell.getColumnIndex());
            actions.add(new Action(downCell, Move.UP));
        }

        if (emptyCell.getColumnIndex() > 0) {
            CellLocation leftCell = new CellLocation(emptyCell.getRowIndex(),
                                                   emptyCell.getColumnIndex() - 1);
            actions.add(new Action(leftCell, Move.RIGHT));
        }

        if (emptyCell.getColumnIndex() < dimension - 1) {
            CellLocation rightCell = new CellLocation(emptyCell.getRowIndex(),
                                                   emptyCell.getColumnIndex() + 1);
            actions.add(new Action(rightCell, Move.LEFT));
        }

        return actions;
    }

    @Override
    public String toString() {
        return Arrays.toString(allCells);
    }

    private void reset(){
        hashCode = -1;
    }

    public int getDimension() {
        return dimension;
    }
}