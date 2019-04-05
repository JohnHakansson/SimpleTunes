package simpleTunes;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {
	private boolean playing = false;
	private MediaPlayer player;
	private MediaPlayer player2;
	private MediaPlayer player3;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GuitarSounds gs = new GuitarSounds();
		PianoSounds ps = new PianoSounds();
		DrumSounds ds = new DrumSounds();
		player = new MediaPlayer(ps.getPianoSound(Color.BLUE));
		player2 = new MediaPlayer(ps.getPianoSound(Color.RED));
		player3 = new MediaPlayer(ps.getPianoSound(Color.GREEN));
		
		new InnerClass().start();
	}

	private class InnerClass extends Thread {

		public void run() {
			
			while(!playing) {
				try {
					player.stop();
					player2.stop();
					player3.stop();
					player.play();
					Thread.sleep(50);
					player2.play();
					Thread.sleep(50);
					player3.play();
					Thread.sleep(50);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
