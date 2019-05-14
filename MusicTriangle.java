package simpleTunes;

import javafx.scene.shape.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;

/**
 * This class represents a Triangle that has a color and the corresponding sound
 * from the color.
 * 
 * @author John H�kansson, Tom Lanhed Sivertsson
 *
 */

public class MusicTriangle extends MusicShape {

	public MusicTriangle(double x, double y, Color color, Media sound) {
		super(color, sound);

		shape = new Polygon();
		((Polygon) shape).getPoints().addAll(new Double[] { x, y - 50, x + 50, y + 50, x - 50, y + 50 });

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
		shape.setLayoutY(y - 50);

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
		return "triangle";

	}

}
