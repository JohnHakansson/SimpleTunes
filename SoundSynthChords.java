package simpleTunes;

import java.io.File;
import java.util.HashMap;
import javafx.scene.media.Media;

public class SoundSynthChords {
	private HashMap<String, Media> synthChordsSounds = new HashMap<String, Media>();
	
	public SoundSynthChords() {

		File one = new File("SoundSynthChords/Chord - Ab Major.wav");
		synthChordsSounds.put("Green1", new Media(one.toURI().toString()));

		File two = new File("SoundSynthChords/Chord - Bb Major.wav");
		synthChordsSounds.put("Green2", new Media(two.toURI().toString()));

		File three = new File("SoundSynthChords/Chord - Db Major.wav");
		synthChordsSounds.put("Green3", new Media(three.toURI().toString()));
		
		File four = new File("SoundSynthChords/Chord - Eb Major.wav");
		synthChordsSounds.put("Green4", new Media(four.toURI().toString()));
		
		File five = new File("SoundSynthChords/Chord - F Minor.wav");
		synthChordsSounds.put("Green5", new Media(five.toURI().toString()));
	}
	
	public synchronized Media getSynthChordSound(String color) {
		return synthChordsSounds.get(color);
		
	}

}
