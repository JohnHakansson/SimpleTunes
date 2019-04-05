package simpleTunes;
import javafx.scene.shape.*;

import java.util.HashMap;

import javafx.scene.media.*;

import javafx.scene.paint.*;
import java.util.*;

public class MusicSquare extends Rectangle{
	private MediaPlayer mediaPlayer;
	private HashMap<Color, Media> basSounds;
	private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
	private Random rand = new Random();
	
	public MusicSquare(double x, double y) {
		super(x, y, 100, 100);
		
		int randomColor = rand.nextInt(3);
		mediaPlayer = new MediaPlayer(basSounds.get(colors[randomColor]));
		
	}
	

}
