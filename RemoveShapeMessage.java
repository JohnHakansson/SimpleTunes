package simpleTunes;

import java.io.Serializable;

/**
 * This class contains two Integers representing the row and column of the shape to
 * be removed.
 * 
 * @author John Håkansson
 *
 */

public class RemoveShapeMessage implements Serializable {
	private static final long serialVersionUID = 6117382832070187915L;
	private int row;
	private int column;
	
	private boolean removeAll = false;



	public RemoveShapeMessage(int row, int column) {
		this.row = row;
		this.column = column;
		
	}
	
	public RemoveShapeMessage(boolean removeAll) {
		this.removeAll = removeAll;
		
	}

	public int getRow() {
		return row;
		
	}

	public int getColumn() {
		return column;
		
	}
	
	public boolean isRemoveAll() {
		return removeAll;
	}
	
}
