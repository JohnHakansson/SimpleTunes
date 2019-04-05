package simpleTunes;

import java.io.File;
import java.util.HashMap;

import javafx.scene.media.Media;
import javafx.scene.paint.Color;

public class GuitarSounds {
	private HashMap<Color, Media> guitarSounds = new HashMap<Color, Media>();
	
	public GuitarSounds() {
		
			File C = new File("GuitarSounds/Cmp3.mp3");
			guitarSounds.put(Color.RED, new Media(C.toURI().toString()));
			
			File E = new File("GuitarSounds/Emp3.mp3");
			guitarSounds.put(Color.GREEN, new Media(E.toURI().toString()));
			
			File G = new File("GuitarSounds/Gmp3.mp3");
			guitarSounds.put(Color.BLUE, new Media(G.toURI().toString()));
	}
	
	public synchronized Media getGuitarSound(Color color) {
		return guitarSounds.get(color);
	}
	
	
}
