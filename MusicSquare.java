package simpleTunes;

import javafx.scene.shape.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;

public class MusicSquare extends Rectangle {
	private AudioClip mediaPlayer;

	public MusicSquare(Color color, Media sound) {
		super(100, 100);
		setSound(sound);
		setFill(color);
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
