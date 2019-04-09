package simpleTunes;

import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicSquare extends Rectangle {
	private MediaPlayer mediaPlayer;
	private Color color;
	private Media sound;
	

	public MusicSquare(Color color, Media sound) {
		super(100, 100);
		this.color = color;
		setSound(sound);
		setFill(color);
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
