package simpleTunes;

import javafx.scene.media.Media;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
/**
 * This class represents a diamond that has a color and the
 * corresponding sound from the color.
 * 
 * @author Roland Askel�f och Tom Lanhed Sivertsson
 *
 */
public class MusicDiamond extends MusicShape {
	/**
	 * Constructs a MusicDiamond
	 * 
	 * @param color a String representing the color of the diamond
	 * @param sound a mediafile the sound of the diamond
	 */
	public MusicDiamond(String color, Media sound) {
		super(color, sound);

		shape = new Polygon();

		shape.setFill(getColor());

		((Polygon) shape).getPoints().addAll(new Double[] { 50.0, 0.0, 100.0, 50.0, 50.0, 100.0, 0.0, 50.0 });
		
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
	public void nullifyEventHandlers() {
		shape.setOnMousePressed(null);
		shape.setOnMouseDragged(null);
		shape.setOnMouseClicked(null);
		shape.setOnMouseReleased(null);
		
	}
	
	public String toString() {
		return "diamond";
		
	}

}
