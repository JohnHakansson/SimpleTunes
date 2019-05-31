package simpleTunes;

import java.net.URISyntaxException;
import java.util.HashMap;
import javafx.scene.media.Media;

/**
 *  The class uses a hashMap to store all the different sounds from the single
 * synth notes.
 * 
 * @author Jesper Lindberg, Tom Lanhed Sivertsson
 */

public class SoundSynthNotes {
	private HashMap<String, Media> synthNotesMap = new HashMap<String, Media>();

	public SoundSynthNotes() {

		try {
			synthNotesMap.put("Orange1",
					new Media(getClass().getClassLoader().getResource("Synth1 - A#.wav").toURI().toString()));

			synthNotesMap.put("Orange2",
					new Media(getClass().getClassLoader().getResource("Synth1 - D#.wav").toURI().toString()));

			synthNotesMap.put("Orange3",
					new Media(getClass().getClassLoader().getResource("Synth1 - F.wav").toURI().toString()));

			synthNotesMap.put("Orange4",
					new Media(getClass().getClassLoader().getResource("Synth1 - G.wav").toURI().toString()));

			synthNotesMap.put("Orange5",
					new Media(getClass().getClassLoader().getResource("Synth1 - G#.wav").toURI().toString()));
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		}
		
	}

	public synchronized Media getSynthNotes(String color) {
		return synthNotesMap.get(color);
		
	}

}