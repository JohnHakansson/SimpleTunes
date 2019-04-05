package simpleTunes;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Controller {
	private DrumSounds drumSounds = new DrumSounds();
	private GuitarSounds guitarSounds = new GuitarSounds();
	private PianoSounds pianoSounds = new PianoSounds();
	private TestUI ui;
	private int nbrOfShapes;
	
	private Random rand = new Random();
	private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
	
	public Controller(TestUI ui) {
		this.ui = ui;
		ui.setController(this);
		new AddShapes().start();
	}
	
	public void addShape() {
		
		Shape randomShape = null;
		int choice = rand.nextInt(3);
		Color shapeColor = colors[choice];
		
		switch(choice) {
			
		case 0: randomShape = new MusicSquare(shapeColor, pianoSounds.getPianoSound(shapeColor));
			break;
			
		case 1: randomShape = new MusicCircle(shapeColor, guitarSounds.getGuitarSound(shapeColor));
			break;
			
		case 2: randomShape = new MusicTriangle(56, 100, shapeColor, drumSounds.getDrumSounds(shapeColor));
			break;
		}
		
		ui.addShape(randomShape);
	}
	
	private class AddShapes extends Thread {
		
		public void run() {
			
			while(!Thread.interrupted()) {
				
				if(nbrOfShapes <= 10) {
					addShape();
					nbrOfShapes++;
				}
			}
		}
	}
}
