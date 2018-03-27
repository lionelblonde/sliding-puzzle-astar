package basicElements;

public class Path {

    private Node[] nodes;

    public Path() {}

    public Path(int length){
        nodes = new Node[length];
    }

    public static Path fromEndNode(Node endNode){
        if (endNode == null){
            return null;
        }
        Path p = new Path(endNode.getCost() + 1);
        Node[] nodes = p.getNodes();
        nodes[endNode.getCost()] = endNode;
        Node parent = endNode.getParent();
        Node current = null;
        while (parent != null){
            current = parent;
            nodes[current.getCost()] = current;
            parent = current.getParent();
        }
        return p;
    }

    public int length(){
        return this.nodes.length;
    }

    public Node[] getNodes() {
        return this.nodes;
    }
}
