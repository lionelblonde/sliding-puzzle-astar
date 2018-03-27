package search;

import heuristic.*;
import java.util.*;
import basicElements.*;

public class AStar extends GraphSearch implements UseHeuristic {

    public static class HeuristicComparator implements Comparator<Node>{

        private HeuristicFunction heuristic;

        public HeuristicComparator(HeuristicFunction heuristic) {
            this.heuristic = heuristic;
        }

        @Override
        public int compare(Node o1, Node o2) {
            int result = (o1.getCost() + heuristic.h(o1)) - (o2.getCost() + heuristic.h(o1));
            if (result == 0){
                // Ties among minimal f values are resolved in favor of the deepest node
                // in the search tree, i.e. the closest node to the goal
                result =  o2.getCost() - o1.getCost();
            }
            return result;
        }
    }

    private HeuristicFunction heuristic;

    @Override
    protected PriorityQueue<Node> createFrontier() {
        // Sort nodes by heuristics
        return new PriorityQueue<Node>(10000, new HeuristicComparator(heuristic));
    }

    @Override
    public void setHeuristic(HeuristicFunction h) {
        this.heuristic = h;
    }

}
