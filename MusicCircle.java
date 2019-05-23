package simpleTunes;

import javafx.scene.shape.*;
import javafx.scene.media.*;

/**
 * This class represents a circle that has a color and the corresponding sound
 * from the color.
 * 
 * @author John Håkansson, Tom Lanhed Sivertsson
 *
 */

public class MusicCircle extends MusicShape {

	/**
	 * Constructs a MusicCircle
	 * 
	 * @param color a color-object the color of the circle
	 * @param sound a mediafile the sound of the circle
	 */

	public MusicCircle(String color, Media sound) {
		super(color, sound);

		shape = new Circle(50);

		shape.setFill(getColor());

		shape.setOnMouseClicked(getMouseEvent());
		shape.setOnMousePressed(getMouseEventPressed());
		shape.setOnMouseDragged(getMouseEventDragged());
		
		shape.setCursor(handCursor);

	}
	
	public Shape getShape() {
		return shape;
	}

	@Override
	public void setLayoutX(double x) {
		shape.setLayoutX(x + 50);
	}

	@Override
	public void setLayoutY(double y) {
		shape.setLayoutY(y + 50);

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

		return "circle";
	}

}
