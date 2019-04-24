package simpleTunes;

import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicTriangle extends MusicShape {

//	private Polygon triangle;

	public MusicTriangle(double x, double y, Color color, Media sound) {
		super(color, sound);
		
		shape = new Polygon();
		((Polygon) shape).getPoints().addAll(new Double[] { x, y - 50, x + 50, y + 50, x - 50, y + 50 });
		
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
		shape.setLayoutY(y - 50);

	}

	@Override
	public void nullifyEventHandlers() {
		shape.setOnMousePressed(null);
		shape.setOnMouseDragged(null);
		shape.setOnMouseClicked(null);
		shape.setOnMouseReleased(null);		
				
	}

	

}
