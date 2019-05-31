package simpleTunes;

import java.io.Serializable;

/**
 * This class represents a shape when it's used to send to another user using a
 * TCP-server.
 * 
 * @author John H�kansson, Roland Askel�f, Matilda Frimodig, Tom Lanhed
 *         Sivertsson
 *
 */
public class MusicShapeMessage implements Serializable {
	private static final long serialVersionUID = 4494869902849409822L;
	
	private String shape;
	private String color;

	private int row;
	private int column;

	/**
	 * 
	 * @param shape String representing the type of shape
	 * @param color String representing the color of the shape
	 * @param row Which row this shape has been placed in
	 * @param column Which column this shape has been placed in
	 */
	public MusicShapeMessage(String shape, String color, int row, int column) {
		this.shape = shape;
		this.row = row;
		this.column = column;
		this.color = color;
		
	}
	
	/**
	 * 
	 * @return String representing the shapes color
	 */
	public String getColor() {
		return color;
		
	}
	
	/**
	 * 
	 * @return String representing the type of shape
	 */
	public String getShape() {
		return shape;
		
	}
	
	/**
	 * 
	 * @return The row the shape has been placed in
	 */
	public int getRow() {
		return row;
		
	}
	
	/**
	 * 
	 * @return The column this shape has been placed in
	 */
	public int getColumn() {
		return column;
		
	}

}
