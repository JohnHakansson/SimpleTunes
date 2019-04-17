package simpleTunes;

import javafx.scene.shape.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;

public class MusicCircle extends Circle {
	private AudioClip mediaPlayer;

	public MusicCircle(Color color, Media sound) {
		super(50);
		setFill(color);
		setSound(sound);
		setStroke(Color.WHITESMOKE);
		
	}

	private void setSound(Media sound) {
		mediaPlayer = new AudioClip(sound.getSource());
		
	}

	public void play() {
		mediaPlayer.play();
		
	}

	public void stop() {
		mediaPlayer.stop();
		
	}
	
}
