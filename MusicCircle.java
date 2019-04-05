package simpleTunes;

import javafx.scene.shape.*;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicCircle extends Circle {
	private MediaPlayer mediaPlayer;
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };
	private Random rand = new Random();

	public MusicCircle(Color color, Media sound) {
		super(56);
		setFill(color);
		setSound(sound);
	}
	
	private void setSound(Media sound) {
		mediaPlayer = new MediaPlayer(sound);
	}
	
	public void play() {
		mediaPlayer.play();
	}
	
	public void pause() {
		mediaPlayer.pause();
	}
	
	public void stop() {
		mediaPlayer.stop();
	}
}
