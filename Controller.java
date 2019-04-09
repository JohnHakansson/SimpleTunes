package simpleTunes;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Controller {
	private DrumSounds drumSounds = new DrumSounds();
	private GuitarSounds guitarSounds = new GuitarSounds();
	private PianoSounds pianoSounds = new PianoSounds();
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private TestUI ui;
	private Random rand = new Random();
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };

	public Controller(TestUI ui) {
		this.ui = ui;
	}

	public void clearList() {

	}

	public void generateShape() {
		shapeList = new ArrayList<Shape>();

		int nbrOfShapes = 0;
		Shape randomShape = null;

		do {
			switch (rand.nextInt(3)) {

			case 0:
				Color randomSquare = colors[rand.nextInt(3)];
				randomShape = new MusicSquare(randomSquare, pianoSounds.getPianoSound(randomSquare));
				break;

			case 1:
				Color randomCircle = colors[rand.nextInt(3)];
				randomShape = new MusicCircle(randomCircle, guitarSounds.getGuitarSound(randomCircle));
				break;

			case 2:
				Color randomTrinagle = colors[rand.nextInt(3)];
				randomShape = new MusicTriangle(56, 100, randomTrinagle, drumSounds.getDrumSounds(randomTrinagle));
				break;
			}

			nbrOfShapes++;
			shapeList.add(randomShape);
			randomShape.setOnMouseClicked(ui.getMouseEvent(randomShape));
		} while (nbrOfShapes < 10);

		ui.addShape(shapeList);
	}

}
