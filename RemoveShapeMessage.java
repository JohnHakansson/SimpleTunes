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
	
	private boolean removeAll = false;
	
	/**
	 * 
	 * @param row Which row the shape was placed in
	 * @param column Which column the shape was placed in
	 */
	public RemoveShapeMessage(int row, int column) {
		this.row = row;
		this.column = column;
		
	}
	
	/**
	 * 
	 * @param removeAll Boolean to indicate whether to remove all shapes from the grid
	 */
	public RemoveShapeMessage(boolean removeAll) {
		this.removeAll = removeAll;
		
	}
	
	/**
	 * 
	 * @return The row the shape was placed in
	 */
	public int getRow() {
		return row;
		
	}
	
	/**
	 * 
	 * @return The column the shape was placed in
	 */
	public int getColumn() {
		return column;
		
	}
	
	/**
	 * 
	 * @return The remove all boolean
	 */
	public boolean isRemoveAll() {
		return removeAll;
		
	}
	
}
