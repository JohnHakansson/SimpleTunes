package simpleTunes;

import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GuitarSounds gs = new GuitarSounds();
		MediaPlayer player = new MediaPlayer(gs.getGuitarSound(Color.RED));
		player.setVolume(1.0);
		player.play();
	}
	
}
