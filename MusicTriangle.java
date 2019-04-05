package simpleTunes;

import javafx.scene.shape.*;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicTriangle extends Polygon {
	private MediaPlayer mediaPlayer;
	private HashMap<Color, Media> guitarSounds;
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };
	private Random rand = new Random();

	public MusicTriangle(double x, double y) {
		getPoints().addAll(new Double[] { x, y-50, x + 50, y + 50, x - 50, y + 50 });

		int randomColor = rand.nextInt(3);
		mediaPlayer = new MediaPlayer(guitarSounds.get(colors[randomColor]));
		setFill(colors[randomColor]);

	}
	
	

}
