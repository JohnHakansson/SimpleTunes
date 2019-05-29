package simpleTunes;

import java.net.URISyntaxException;
import java.util.HashMap;

import javafx.scene.media.Media;


/**
 * The class uses a hashMap to store all the different sounds from the Bass.
 * 
 * @author Matilda Frimodig, Tom Lanhed Sivertsson
 */
public class SoundBass {
	private HashMap<String, Media> bassSounds = new HashMap<String, Media>();

	public SoundBass() {

		try {
			bassSounds.put("Blue1",
					new Media(getClass().getClassLoader().getResource("Bas - A#.wav").toURI().toString()));

			bassSounds.put("Blue2",
					new Media(getClass().getClassLoader().getResource("Bas - D#.wav").toURI().toString()));

			bassSounds.put("Blue3",
					new Media(getClass().getClassLoader().getResource("Bas - F.wav").toURI().toString()));

			bassSounds.put("Blue4",
					new Media(getClass().getClassLoader().getResource("Bas - G.wav").toURI().toString()));

			bassSounds.put("Blue5",
					new Media(getClass().getClassLoader().getResource("Bas - G#.wav").toURI().toString()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		}
		
	}

	public synchronized Media getBassSound(String color) {
		return bassSounds.get(color);
		
	}
}
