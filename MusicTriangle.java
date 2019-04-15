package simpleTunes;

import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicTriangle extends Polygon {
	private AudioClip mediaPlayer;
	private Media sound;

	public MusicTriangle(double x, double y, Color color, Media sound) {
		getPoints().addAll(new Double[] { x, y - 50, x + 50, y + 50, x - 50, y + 50 });
		setSound(sound);
		setFill(color);
		setStroke(Color.WHITESMOKE);

	}

	private void setSound(Media sound) {
		mediaPlayer = new AudioClip(sound.getSource());
		this.sound = sound;
	}
	
	public Media getSounds() {
		return sound;
	}

	public void play() {
//		mediaPlayer.seek(Duration.ZERO);
		mediaPlayer.play();
	}

	public void pause() {
//		mediaPlayer.pause();
	}

	public void stop() {
		mediaPlayer.stop();
	}
	
	public String toString() {
		return "MusicTriangle";
	}
}
