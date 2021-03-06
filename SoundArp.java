package simpleTunes;

import java.net.URISyntaxException;
import java.util.HashMap;

import javafx.scene.media.Media;

public class SoundArp {
	
	/**
	 * The class uses a hashMap to store all the different sounds from the Arpeggiator.
	 * 
	 * @author Tom Lanhed Sivertsson
	 */
	private HashMap<String, Media> arpSounds = new HashMap<String, Media>();

	public SoundArp() {

		try {
			arpSounds.put("Red1", new Media(getClass().getClassLoader().getResource("Arp-Ab.wav").toURI().toString()));

			arpSounds.put("Red2", new Media(getClass().getClassLoader().getResource("Arp-Bb.wav").toURI().toString()));

			arpSounds.put("Red3", new Media(getClass().getClassLoader().getResource("Arp-Db.wav").toURI().toString()));

			arpSounds.put("Red4", new Media(getClass().getClassLoader().getResource("Arp-Eb.wav").toURI().toString()));

			arpSounds.put("Red5", new Media(getClass().getClassLoader().getResource("Arp-G#.wav").toURI().toString()));
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		}

	}

	public synchronized Media getArpSound(String color) {
		return arpSounds.get(color);
		
	}

}
