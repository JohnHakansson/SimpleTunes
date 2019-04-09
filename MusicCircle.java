package simpleTunes;

import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicCircle extends Circle {
	private MediaPlayer mediaPlayer;
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };
	private Random rand = new Random();
	private Media sound;

	public MusicCircle(Color color, Media sound) {
		super(50);
		setFill(color);
		setSound(sound);
		setStroke(Color.WHITESMOKE);
	}

	private void setSound(Media sound) {
		mediaPlayer = new MediaPlayer(sound);
		this.sound = sound;
	}
	
	public Media getSounds() {
		return sound;
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
	
	public String toString() {
		return "MusicCirlce";
	}
}
