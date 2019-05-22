package simpleTunes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;

public class SoundDrums {
	private HashMap<String, Media> drumSounds = new HashMap<String, Media>();

	public SoundDrums() {

		File clap = new File("SoundDrums/Clap.wav");
		drumSounds.put("Purple1", new Media(clap.toURI().toString()));

		File hiHat = new File("SoundDrums/Hi-Hat.wav");
		drumSounds.put("Purple2", new Media(hiHat.toURI().toString()));

		File kick = new File("SoundDrums/Kick.wav");
		drumSounds.put("Purple3", new Media(kick.toURI().toString()));

		File openHat = new File("SoundDrums/Open-Hat.wav");
		drumSounds.put("Purple4", new Media(openHat.toURI().toString()));

		File snare = new File("SoundDrums/Snare.wav");
		drumSounds.put("Purple5", new Media(snare.toURI().toString()));
	}

	public synchronized Media getDrumSounds(String color) {
		return drumSounds.get(color);
	}

}
