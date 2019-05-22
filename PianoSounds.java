package simpleTunes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.paint.Color;

	/**
	 * The Class represents the three piano-sounds which are stored
	 * in its own HashMap and accessed by the controller.
	 *
	 */

public class PianoSounds {
	private HashMap<String, Media> pianoSounds = new HashMap<String, Media>();

	public PianoSounds() {

		File C = new File("PianoSounds/C.wav");
		pianoSounds.put("Blue1", new Media(C.toURI().toString()));

		File E = new File("PianoSounds/E.wav");
		pianoSounds.put("Blue2", new Media(E.toURI().toString()));

		File G = new File("PianoSounds/G.wav");
		pianoSounds.put("Blue3", new Media(G.toURI().toString()));
		
		File G2 = new File("PianoSounds/G.wav");
		pianoSounds.put("Blue4", new Media(G2.toURI().toString()));
		
		File G3 = new File("PianoSounds/G.wav");
		pianoSounds.put("Blue5", new Media(G3.toURI().toString()));
	}

	public synchronized Media getPianoSound(String color) {
		return pianoSounds.get(color);
	}

}
