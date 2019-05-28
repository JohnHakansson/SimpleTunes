package simpleTunes;

import java.net.URISyntaxException;
import java.util.HashMap;
import javafx.scene.media.Media;

	/**
	 * The Class represents the three piano-sounds which are stored
	 * in its own HashMap and accessed by the controller.
	 *
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized Media getSynthNotes(String color) {
		return synthNotesMap.get(color);
	}

}