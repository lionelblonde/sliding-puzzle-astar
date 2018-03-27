package heuristic;

import basicElements.*;

public class MisplacedElements extends AbstractHeuristic {

    @Override
    protected int calculate(State s) {
        int counter = 0;
        byte[] allCells = s.getAllCells();
        int dimension = s.getDimension();

        State gs = State.createGoalState(dimension);

        for (int i = 0; i < allCells.length; i++) {
            int value = allCells[i];
            if (value == 0) {
                continue;
            }
            if (allCells[i] != gs.getAllCells()[i]) {
                counter++;
            }
        }
        return counter;
    }

}
