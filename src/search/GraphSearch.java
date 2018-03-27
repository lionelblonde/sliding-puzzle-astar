package search;

import java.util.*;
import basicElements.*;

public abstract class GraphSearch implements SearchAlgorithm {
    // Abstract class for graph search algorithms

    private Set<State> explored;
    private Set<Node> frontierSet;
    private Queue<Node> frontier;
    private State goalState;

    @Override
    public PuzzleSolution resolve(State start, State goal) {
        init(start, goal);
        Node finalNode = run();
        PuzzleSolution solution = new PuzzleSolution();
        solution.setPath(Path.fromEndNode(finalNode));
        solution.setExploredNode(explored.size());

        return solution;
    }

    protected abstract Queue<Node> createFrontier();

    protected void init(State start, State goal) {
        explored = new HashSet<State>();
        frontier = createFrontier();
        frontierSet = new HashSet<Node>();
        this.goalState = goal;
        addToFrontier(new Node(start));
    }

    public void addToFrontier(Node n){
        this.frontier.add(n);
        this.frontierSet.add(n);
    }

    protected Node removeChoice() {
        Node n = this.frontier.poll();
        this.frontierSet.remove(n);
        return n;
    }

    protected List<Action> getPossibleAction(State s) {
        return s.getPossibleActions();
    }

    protected Node run() {
        while (!frontier.isEmpty()) {
            Node n = removeChoice();
            if (n == null){
                continue;
            }
            State s = n.getState();
            // Check whether the goal has been reached
            if (isGoalState(s)) {
                System.out.println("Solved!");
                return n;
            }
            explored.add(s);
            List<Action> possibleActions = getPossibleAction(s);
            for (Action action : possibleActions) {
                State newState = action.applyTo(s);
                if (!explored.contains(newState)) {
                    Node newNode = new Node(newState);
                    newNode.setParent(n);
                    newNode.setAction(action);

                    if (!frontierSet.contains(newNode)) {
                        addToFrontier(newNode);
                    }
                }
            }
        }
        // No solution found
        return null;
    }

    protected boolean isGoalState(State s){
        return s.equals(this.goalState);
    }

}
