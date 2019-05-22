package simpleTunes;

import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class MusicDiamond extends MusicShape {

	public MusicDiamond(String color, Media sound) {
		super(color, sound);

		shape = new Polygon();

		shape.setFill(getColor());
		shape.setStroke(Color.WHITESMOKE);

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
