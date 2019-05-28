package simpleTunes;

import java.net.URISyntaxException;
import java.util.HashMap;

import javafx.scene.media.Media;

	/**
	 * The class uses a hashMap to store all the different sounds from the Drums.
	 * 
	 */

public class SoundDrums {
	private HashMap<String, Media> drumSounds = new HashMap<String, Media>();

	public SoundDrums() {

		try {
			drumSounds.put("Purple1",
					new Media(getClass().getClassLoader().getResource("Clap.wav").toURI().toString()));

			drumSounds.put("Purple2",
					new Media(getClass().getClassLoader().getResource("Hi-Hat.wav").toURI().toString()));

			drumSounds.put("Purple3",
					new Media(getClass().getClassLoader().getResource("Kick.wav").toURI().toString()));

			drumSounds.put("Purple4",
					new Media(getClass().getClassLoader().getResource("Open-Hat.wav").toURI().toString()));

			drumSounds.put("Purple5",
					new Media(getClass().getClassLoader().getResource("Snare.wav").toURI().toString()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized Media getDrumSounds(String color) {
		return drumSounds.get(color);
	}

}
