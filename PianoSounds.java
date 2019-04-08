package simpleTunes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.paint.Color;

public class PianoSounds {
	private HashMap<Color, Media> pianoSounds = new HashMap<Color, Media>();
	
	public PianoSounds() {
		
		File kick = new File("PianoSounds/C.wav");
		pianoSounds.put(Color.RED, new Media(kick.toURI().toString()));
		
		File snare = new File("PianoSounds/E.wav");
		pianoSounds.put(Color.GREEN, new Media(snare.toURI().toString()));
		
		File hiHat = new File("PianoSounds/G.wav");
		pianoSounds.put(Color.BLUE, new Media(hiHat.toURI().toString()));
	}
	
	public synchronized Media getPianoSound(Color color) {
		return pianoSounds.get(color);
	}
	
	
}
