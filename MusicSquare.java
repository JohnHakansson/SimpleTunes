package simpleTunes;

import javafx.scene.shape.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;

/**
 * This class represents a square that has a color and the corresponding sound
 * from the color.
 * 
 * @author John H�kansson, Tom Lanhed Sivertsson
 *
 */

public class MusicSquare extends MusicShape {

	/**
	 * Constructs a MusicSquare
	 * 
	 * @param color a color-object the color of the circle
	 * @param sound a mediafile the sound of the circle
	 */

	public MusicSquare(Color color, Media sound) {
		super(color, sound);

		shape = new Rectangle(100, 100);

		if (color.equals(Color.RED)) {

			shape.setId("shapeRed");

		} else if (color.equals(Color.BLUE)) {

			shape.setId("shapeBlue");

		} else {

			shape.setId("shapeGreen");

		}
		
		shape.setOnMouseClicked(getMouseEvent());
		shape.setOnMousePressed(getMouseEventPressed());
		shape.setOnMouseDragged(getMouseEventDragged());

	}

	public Shape getShape() {
		return shape;
	}

	public void setLayoutX(double x) {
		shape.setLayoutX(x);
	}

	public void setLayoutY(double y) {
		shape.setLayoutY(y);
	}

	/**
	 * Turns of the eventhandlers for the shape when it's placed in grid.
	 */

	@Override
	public void nullifyEventHandlers() {
		shape.setOnMousePressed(null);
		shape.setOnMouseDragged(null);
		shape.setOnMouseClicked(null);
		shape.setOnMouseReleased(null);
	}

	public String toString() {

		return "square";
	}

}
