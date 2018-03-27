package heuristic;

import basicElements.*;

public abstract class AbstractHeuristic implements HeuristicFunction {

    @Override
    public int h(Node n) {
        int cachedValue = n.getHeuristic();
        if (cachedValue == -1){
            cachedValue = calculate(n.getState());
            n.setHeuristic(cachedValue);
        }
        return cachedValue;
    }

    protected abstract int calculate(State state);

}
