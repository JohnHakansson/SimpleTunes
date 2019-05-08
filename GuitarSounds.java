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
	private HashMap<Color, Media> guitarSounds = new HashMap<Color, Media>();

	public GuitarSounds() {

		File C = new File("GuitarSounds/C.wav");
		guitarSounds.put(Color.RED, new Media(C.toURI().toString()));

		File E = new File("GuitarSounds/E.wav");
		guitarSounds.put(Color.GREEN, new Media(E.toURI().toString()));

		File G = new File("GuitarSounds/G.wav");
		guitarSounds.put(Color.BLUE, new Media(G.toURI().toString()));
	}

	public synchronized Media getGuitarSound(Color color) {
		return guitarSounds.get(color);
	}
}
