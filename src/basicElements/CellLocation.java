package basicElements;

public class CellLocation {
    // Provide a proxy representation of a cell's position on the board

	private int rowIndex;
	private int columnIndex;

	public CellLocation() {
	}

	public CellLocation(int rowIndex, int columnIndex) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	public int getRowIndex() {
		return this.rowIndex;
	}

	public int getColumnIndex() {
		return this.columnIndex;
	}
}
