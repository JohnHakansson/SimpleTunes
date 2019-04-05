package simpleTunes;
import javafx.scene.shape.*;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicSquare extends Rectangle{
	private MediaPlayer mediaPlayer;
	private Color color;
	
	public MusicSquare(Color color, Media sound) {
		super(100, 100);
		this.color = color;
		setSound(sound);
		setFill(color);
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
