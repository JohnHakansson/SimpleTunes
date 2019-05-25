package simpleTunes;

import javafx.scene.shape.*;
import javafx.scene.media.*;

/**
 * This class represents a right-angeled triangle that has a color and the
 * corresponding sound from the color.
 * 
 * @author Matilda Frimodig
 *
 */

public class MusicRightTriangle extends MusicShape {

	public MusicRightTriangle(String color, Media sound) {
		super(color, sound);

		shape = new Polygon();
		((Polygon) shape).getPoints().addAll(new Double[] { 0.0, 0.0, 0.00, 100.0, 100.0, 100.0, 0.0, 0.0 });

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
		shape.setLayoutY(y);
	}

	/**
	 * Turns of the eventhandlers for the shape when it's placed in the grid.
	 */

	public void nullifyEventHandlers() {
		shape.setOnMousePressed(null);
		shape.setOnMouseDragged(null);
		shape.setOnMouseClicked(null);
		shape.setOnMouseReleased(null);

	}

	public String toString() {
		return "righttriangle";

	}

}
