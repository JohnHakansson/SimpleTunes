package simpleTunes;

import java.io.Serializable;

/**
 * This class represents a shape when it's used to send to another user using a
 * TCP-server.
 * 
 * @author John Håkansson, Roland Askelöf, Matilda Frimodig, Tom Lanhed
 *         Sivertsson
 *
 */

public class MusicShapeMessage implements Serializable {
	private static final long serialVersionUID = 4494869902849409822L;
	private String shape;

	private String color;

	private int row;
	private int column;

	public MusicShapeMessage(String shape, String color, int row, int column) {
		this.shape = shape;
		this.row = row;
		this.column = column;
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public String getShape() {
		return shape;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
