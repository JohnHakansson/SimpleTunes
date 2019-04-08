package simpleTunes;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Controller {
//	private DrumSounds drumSounds = new DrumSounds();
//	private GuitarSounds guitarSounds = new GuitarSounds();
//	private PianoSounds pianoSounds = new PianoSounds();
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private TestUI ui;
	
	public Controller(TestUI ui) {
		this.ui = ui;
	}
	
	public void clearList() {

	}
	
	public void generateShape() {
		shapeList = new ArrayList<Shape>();
		
		MusicSquare square = new MusicSquare(100,100);
		MusicCircle circle = new MusicCircle(100,100, Color.RED, null);
		MusicCircle circle2 = new MusicCircle(100,100, Color.PINK, null);
		shapeList.add(circle);
		shapeList.add(square);
		shapeList.add(circle2);
		
		ui.addShape(shapeList);
	}
}
