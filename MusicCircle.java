package simpleTunes;

import javafx.scene.shape.*;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicCircle extends Circle {
	private MediaPlayer mediaPlayer;
	private HashMap<Color, Media> drumSounds;
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };
	private Random rand = new Random();

	public MusicCircle(double x, double y) {
		super(x, y, 56);

		int randomColor = rand.nextInt(3);
		mediaPlayer = new MediaPlayer(drumSounds.get(colors[randomColor]));
		setFill(colors[randomColor]);

	}

}
