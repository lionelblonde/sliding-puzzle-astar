package basicElements;

public class PuzzleSolution {

    private Path path;
    private long exploredNode;

    public PuzzleSolution() {}

    public Path getPath() {
        // Return the current best candidate optimal path to reach the goal
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public long getExploredNode() {
        // Return the number of nodes explored by the algorithm
        return exploredNode;
    }

    public void setExploredNode(long exploredNode) {
        this.exploredNode = exploredNode;
    }
}
