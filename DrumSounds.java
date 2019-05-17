package simpleTunes;

import java.io.File;
import java.util.HashMap;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;

	/**
	 * The Class represents the three drum-sounds which are stored
	 * in  its own HashMap and accessed by the controller.
	 *
	 */

public class DrumSounds {
	private HashMap<String, Media> drumSounds = new HashMap<String, Media>();

	public DrumSounds() {

		File kick = new File("DrumSounds/HiHat2.wav");
		drumSounds.put("Orange1", new Media(kick.toURI().toString()));

		File snare = new File("DrumSounds/Kick3.wav");
		drumSounds.put("Orange2", new Media(snare.toURI().toString()));

		File hiHat = new File("DrumSounds/Snare3.wav");
		drumSounds.put("Orange3", new Media(hiHat.toURI().toString()));
		
		File hiHat2 = new File("DrumSounds/Snare3.wav");
		drumSounds.put("Orange4", new Media(hiHat2.toURI().toString()));
		
		File hiHat3 = new File("DrumSounds/Snare3.wav");
		drumSounds.put("Orange5", new Media(hiHat3.toURI().toString()));
	}

	public synchronized Media getDrumSound(String color) {
		return drumSounds.get(color);
	}
}
