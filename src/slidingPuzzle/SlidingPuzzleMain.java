package slidingPuzzle;

import search.*;
import basicElements.*;
import heuristic.*;

public class SlidingPuzzleMain {

    public static Path runAStar(byte[] testData, State goal) {
        AStar algo = new AStar();
        algo.setHeuristic(new MisplacedElements());
        State ns = new State(testData);
        // Set time-origin
        long startTime = System.currentTimeMillis();
        Path solution = algo.resolve(ns, goal).getPath();
        if (solution == null) {
        	System.out.println("No solution.");
        	System.exit(0);
        }
        // Print execution duration
        System.out.println(System.currentTimeMillis()-startTime);

        return solution;
    }

    public static void main(String[] args) {
    	int dim = new Integer(args[0]);
    	String delimiter = ",";
    	String[] tabStr = args[1].split(delimiter);
    	int l = (int)Math.pow(dim, 2);
    	byte[] tabByte = new byte[l];
    	for (int i=0; i<l; i++) {
    		tabByte[i]=new Byte(tabStr[i]);
    	}
    	State s = State.createGoalState(dim);
    	Path solution_ = runAStar(tabByte,s);
        // Setup the graphical interface
    	SlidingPuzzle sl = new SlidingPuzzle(tabByte,solution_);
    }

}