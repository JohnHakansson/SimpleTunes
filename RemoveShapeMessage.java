package simpleTunes;

import java.io.Serializable;

/**
 * This class contains two Integers representing the row and column of the shape to
 * be removed.
 * 
 * @author John H�kansson
 *
 */

public class RemoveShapeMessage implements Serializable {
	private static final long serialVersionUID = 6117382832070187915L;
	private int row;
	private int column;

	public RemoveShapeMessage(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
