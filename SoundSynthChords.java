package simpleTunes;

import java.net.URISyntaxException;
import java.util.HashMap;
import javafx.scene.media.Media;

/**
 *  The class uses a hashMap to store all the different sounds from the
 * synth-chords.
 * 
 * @author Roland Askelöf, Tom Lanhed Sivertsson
 */

public class SoundSynthChords {
	private HashMap<String, Media> synthChordsSounds = new HashMap<String, Media>();

	public SoundSynthChords() {

		try {
			synthChordsSounds.put("Green1",
					new Media(getClass().getClassLoader().getResource("Chord - Ab Major.wav").toURI().toString()));

			synthChordsSounds.put("Green2",
					new Media(getClass().getClassLoader().getResource("Chord - Bb Major.wav").toURI().toString()));

			synthChordsSounds.put("Green3",
					new Media(getClass().getClassLoader().getResource("Chord - Db Major.wav").toURI().toString()));

			synthChordsSounds.put("Green4",
					new Media(getClass().getClassLoader().getResource("Chord - Eb Major.wav").toURI().toString()));

			synthChordsSounds.put("Green5",
					new Media(getClass().getClassLoader().getResource("Chord - F Minor.wav").toURI().toString()));
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		}
		
	}

	public synchronized Media getSynthChordSound(String color) {
		return synthChordsSounds.get(color);

	}

}
