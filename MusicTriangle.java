package simpleTunes;

import javafx.scene.shape.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;

public class MusicTriangle extends Polygon {
	private AudioClip mediaPlayer;

	public MusicTriangle(double x, double y, Color color, Media sound) {
		getPoints().addAll(new Double[] { x, y - 50, x + 50, y + 50, x - 50, y + 50 });
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
