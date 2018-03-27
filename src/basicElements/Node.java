package basicElements;

public class Node {

    private State state;
    private int cost = 0;
    private int heuristic = -1;
    private Node parent = null;
    private Action nextAction;

    public Node() {}

    public Node(State state) {
            this.state = state;
    }

    public State getState() {
        return this.state;
    }

    @Override
    public boolean equals(Object obj) {
        // Equality based on state
        if (obj instanceof Node){
            return this.state.equals(((Node)obj).state);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    public void setParent(Node parent) {
        this.parent = parent;
        this.cost = this.parent.getCost() + 1;
    }

    public Node getParent() {
        return this.parent;
    }

    public int getCost() {
        return this.cost;
    }

    public Action getAction() {
        return this.nextAction;
    }

    public void setAction(Action next){
        this.nextAction = next;
    }

    public int getHeuristic() {
        return this.heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}
