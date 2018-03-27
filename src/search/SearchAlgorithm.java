package search;

import basicElements.*;

public interface SearchAlgorithm {
    // Interface for search algorithms
    public PuzzleSolution resolve(State start, State goal);
}
