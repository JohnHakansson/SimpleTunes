package simpleTunes;

import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicSquare extends Rectangle {
	private AudioClip mediaPlayer;
	private Media sound;

	public MusicSquare(Color color, Media sound) {
		super(100, 100);
		setSound(sound);
		setFill(color);
		//setStroke(Color.WHITESMOKE);
	}

	private void setSound(Media sound) {
		mediaPlayer = new AudioClip(sound.getSource());
		this.sound = sound;
	}
	
	public void play() {
		mediaPlayer.play();
	}

	public void stop() {
		mediaPlayer.stop();
	}
}
