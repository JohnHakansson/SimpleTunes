package simpleTunes;

import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicCircle extends MusicShape {

	public MusicCircle(Color color, Media sound) {
		super(color, sound);

		shape = new Circle(50);
		
		shape.setFill(color);
		shape.setStroke(Color.WHITESMOKE);

		shape.setOnMouseClicked(getMouseEvent());
		shape.setOnMousePressed(getMouseEventPressed());
		shape.setOnMouseDragged(getMouseEventDragged());

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

	@Override
	public void nullifyEventHandlers() {
		shape.setOnMousePressed(null);
		shape.setOnMouseDragged(null);
		shape.setOnMouseClicked(null);
		shape.setOnMouseReleased(null);		
		
	}

}
