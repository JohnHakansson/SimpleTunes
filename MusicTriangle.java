package simpleTunes;

import javafx.scene.shape.*;
import javafx.scene.media.*;

/**
 * This class represents a Triangle that has a color and the corresponding sound
 * from the color.
 * 
 * @author John H�kansson, Tom Lanhed Sivertsson
 *
 */

public class MusicTriangle extends MusicShape {
	private double x = 50;
	private double y = 100;
	
	/**
	 * Constructs a MusicTriangle
	 * 
	 * @param color a String representing the color of the triangle
	 * @param sound a mediafile the sound of the triangle
	 */
	public MusicTriangle(String color, Media sound) {
		super(color, sound);

		shape = new Polygon();
		((Polygon) shape).getPoints().addAll(new Double[] { x, y - 50, x + 50, y + 50, x - 50, y + 50 });

		shape.setFill(getColor());

		shape.setOnMouseClicked(getMouseEvent());
		shape.setOnMousePressed(getMouseEventPressed());
		shape.setOnMouseDragged(getMouseEventDragged());
		
//		shape.setCursor(handCursor);

	}

	public Shape getShape() {
		return shape;
		
	}

	public void setLayoutX(double x) {
		shape.setLayoutX(x);

	}

	public void setLayoutY(double y) {
		shape.setLayoutY(y - 50);

	}
	
	/**
	 * Turns of the eventhandlers for the shape when it's placed in grid.
	 */
	public void nullifyEventHandlers() {
		shape.setOnMousePressed(null);
		shape.setOnMouseDragged(null);
		shape.setOnMouseClicked(null);
		shape.setOnMouseReleased(null);

	}

	public String toString() {
		return "triangle";

	}

}
