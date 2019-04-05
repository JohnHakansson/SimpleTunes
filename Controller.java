package simpleTunes;

import javafx.application.Application;

public class Controller {
//	private DrumSounds drumSounds = new DrumSounds();
//	private GuitarSounds guitarSounds = new GuitarSounds();
//	private PianoSounds pianoSounds = new PianoSounds();
	private TestUI ui;
	
	public Controller(TestUI ui) {
		this.ui = ui;
		ui.setController(this);
	}
	
	public void addShape() {
		MusicSquare square = new MusicSquare(200,400);
		ui.addShape(square);
	}
}
