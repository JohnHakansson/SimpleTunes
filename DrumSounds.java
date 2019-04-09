package simpleTunes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.paint.Color;

public class DrumSounds {
	private HashMap<Color, Media> drumSounds = new HashMap<Color, Media>();

	public DrumSounds() {

		File kick = new File("DrumSounds/HiHat2.wav");
		drumSounds.put(Color.RED, new Media(kick.toURI().toString()));

		File snare = new File("DrumSounds/Kick3.wav");
		drumSounds.put(Color.GREEN, new Media(snare.toURI().toString()));

		File hiHat = new File("DrumSounds/Snare3.wav");
		drumSounds.put(Color.BLUE, new Media(hiHat.toURI().toString()));
	}

	public synchronized Media getDrumSounds(Color color) {
		return drumSounds.get(color);
	}
}
