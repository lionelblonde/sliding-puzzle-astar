package basicElements;

import basicElements.CellLocation;

public enum Move {
    // Does not perform a motion by itself, but is used by an `Action` object to make one
    // From:  http://download.oracle.com/javase/tutorial/java/javaOO/enum.html
    //     The constructor for an enum type must be package-private or private access. It
    //     automatically creates the constants that are defined at the beginning of the enum body.
    //     You cannot invoke an enum constructor yourself.

    // Enumeration of all atomic units of motion
    UP(0, -1), DOWN(0, 1), RIGHT(1, 0), LEFT(-1, 0);

    private int horizontalMove;
    private int verticalMove;

    private Move(int horizontal, int vertical) {
        this.horizontalMove = horizontal;
        this.verticalMove = vertical;
    }

    public int getHorizontalMove() {
            return this.horizontalMove;
    }

    public int getVerticalMove() {
            return this.verticalMove;
    }

    public CellLocation getNextCellLocation(CellLocation currentLocation) {
        // Returns, from a CellLocation, the destination CellLocation after motion
        return new CellLocation(getNextRow(currentLocation.getRowIndex()),
                                getNextColumn(currentLocation.getColumnIndex()));
    }

    private int getNextRow(int currentRow) {
        return currentRow + verticalMove;
    }

    private int getNextColumn(int currentColumn) {
        return currentColumn + horizontalMove;
    }

    public Move getInverse() {
        switch (this) {
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        }
        return null;
    }
}
