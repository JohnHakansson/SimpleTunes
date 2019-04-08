package simpleTunes;

import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicTriangle extends Polygon {
	private MediaPlayer mediaPlayer;
	private HashMap<Color, Media> guitarSounds;
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };
	private Random rand = new Random();

	public MusicTriangle(double x, double y, Color color, Media sound) {
		getPoints().addAll(new Double[] { x, y-50, x + 50, y + 50, x - 50, y + 50 });
		setSound(sound);
		setFill(color);
		setStroke(Color.WHITESMOKE);

		
	}
	
	private void setSound(Media sound) {
		mediaPlayer = new MediaPlayer(sound);
	}
	
	public void play() {
		mediaPlayer.seek(Duration.ZERO);
		mediaPlayer.play();
	}
	
	public void pause() {
		mediaPlayer.pause();
	}
	
	public void stop() {
		mediaPlayer.stop();
	}
}
