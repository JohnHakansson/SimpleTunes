package simpleTunes;

import java.io.File;
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

		File A = new File("SoundSynthNotes/Synth1 - A#.wav");
		synthNotesMap.put("Orange1", new Media(A.toURI().toString()));

		File D1 = new File("SoundSynthNotes/Synth1 - D#.wav");
		synthNotesMap.put("Orange2", new Media(D1.toURI().toString()));

		File F = new File("SoundSynthNotes/Synth1 - F.wav");
		synthNotesMap.put("Orange3", new Media(F.toURI().toString()));
		
		File G = new File("SoundSynthNotes/Synth1 - G.wav");
		synthNotesMap.put("Orange4", new Media(G.toURI().toString()));
		
		File G2 = new File("SoundSynthNotes/Synth1 - G#.wav");
		synthNotesMap.put("Orange5", new Media(G2.toURI().toString()));
	}

	public synchronized Media getSynthNotes(String color) {
		return synthNotesMap.get(color);
	}

}