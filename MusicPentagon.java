package simpleTunes;

import javafx.scene.media.Media;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class MusicPentagon extends MusicShape {
	
	public MusicPentagon(String color, Media sound) {
		super(color, sound);

		shape = new Polygon();
		((Polygon) shape).getPoints().addAll(new Double[] { 25.0, 0.0, 75.0 , 0.0, 100.0, 50.0, 75.0, 100.0, 25.0, 100.0, 0.0, 50.0});
		
		
		shape.setFill(getColor());

		shape.setOnMouseClicked(getMouseEvent());
		shape.setOnMousePressed(getMouseEventPressed());
		shape.setOnMouseDragged(getMouseEventDragged());

		shape.setCursor(handCursor);
		
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
		return "pentagon";

	}

}



