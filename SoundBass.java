package simpleTunes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;

public class SoundBass {
	private HashMap<String, Media> bassSounds = new HashMap<String, Media>();

	public SoundBass() {

		File One = new File("SoundBass/Bas - A#.wav");
		bassSounds.put("Blue1", new Media(One.toURI().toString()));

		File Two = new File("SoundBass/Bas - D#.wav");
		bassSounds.put("Blue2", new Media(Two.toURI().toString()));

		File Three = new File("SoundBass/Bas - F.wav");
		bassSounds.put("Blue3", new Media(Three.toURI().toString()));
		
		File Four = new File("SoundBass/Bas - G.wav");
		bassSounds.put("Blue4", new Media(Four.toURI().toString()));
		
		File Five = new File("SoundBass/Bas - G#.wav");
		bassSounds.put("Blue5", new Media(Five.toURI().toString()));
	}

	public synchronized Media getBassSound(String color) {
		return bassSounds.get(color);
	}
}
