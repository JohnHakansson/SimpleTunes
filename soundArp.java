package simpleTunes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;

public class SoundArp {
	
	private HashMap<String, Media> arpSounds = new HashMap<String, Media>();

	public SoundArp() {
		
		File C = new File("SoundArp/Arp-Ab.wav");
		arpSounds.put("Red1", new Media(C.toURI().toString()));

		File E = new File("SoundArp/Arp-Bb.wav");
		arpSounds.put("Red2", new Media(E.toURI().toString()));

		File G = new File("SoundArp/Arp-Db.wav");
		arpSounds.put("Red3", new Media(G.toURI().toString()));
		
		File G2 = new File("SoundArp/Arp-Eb.wav");
		arpSounds.put("Red4", new Media(G2.toURI().toString()));
		
		File G3 = new File("SoundArp/Arp-G#.wav");
		arpSounds.put("Red5", new Media(G3.toURI().toString()));
	}

	public synchronized Media getArpSound(String color) {
		return arpSounds.get(color);
	}

}
