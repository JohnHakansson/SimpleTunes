package simpleTunes;

import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicSquare extends MusicShape {

	public MusicSquare(Color color, Media sound) {
		super(color, sound);

		shape = new Rectangle(100, 100);

		shape.setFill(color);
		shape.setStroke(Color.WHITESMOKE);

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
