package simpleTunes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.paint.Color;

	/**
	 * The Class represents the three guitar-sounds which are stored
	 * in its own HashMap and accessed by the controller.
	 *
	 */

public class GuitarSounds {
	private HashMap<String, Media> guitarSounds = new HashMap<String, Media>();

	public GuitarSounds() {

		File C = new File("GuitarSounds/C.wav");
		guitarSounds.put("Purple1", new Media(C.toURI().toString()));

		File E = new File("GuitarSounds/E.wav");
		guitarSounds.put("Purple2", new Media(E.toURI().toString()));

		File G = new File("GuitarSounds/G.wav");
		guitarSounds.put("Purple3", new Media(G.toURI().toString()));
		
		File G2 = new File("GuitarSounds/G.wav");
		guitarSounds.put("Purple4", new Media(G2.toURI().toString()));

		File G3 = new File("GuitarSounds/G.wav");
		guitarSounds.put("Purple5", new Media(G3.toURI().toString()));

		
	}

	public synchronized Media getGuitarSound(String color) {
		return guitarSounds.get(color);
	}
}
