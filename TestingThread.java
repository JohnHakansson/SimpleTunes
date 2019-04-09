package simpleTunes;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class TestingThread extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Controller cont = new Controller();
		
		MusicSquare square1 = new MusicSquare(Color.RED, cont.getPianoSounds().getPianoSound(Color.RED));
		MusicSquare square2 = new MusicSquare(Color.BLUE, cont.getPianoSounds().getPianoSound(Color.BLUE));
		MusicSquare square3 = new MusicSquare(Color.GREEN, cont.getPianoSounds().getPianoSound(Color.GREEN));
		MusicSquare square4 = new MusicSquare(Color.RED, cont.getPianoSounds().getPianoSound(Color.RED));
		MusicSquare square5 = new MusicSquare(Color.BLUE, cont.getPianoSounds().getPianoSound(Color.BLUE));
		MusicSquare square6 = new MusicSquare(Color.GREEN, cont.getPianoSounds().getPianoSound(Color.GREEN));
		MusicCircle circle1 = new MusicCircle(Color.RED, cont.getGuitarSounds().getGuitarSound(Color.RED));
		MusicCircle circle2 = new MusicCircle(Color.GREEN, cont.getGuitarSounds().getGuitarSound(Color.GREEN));
		
		cont.addShapestoArray(square1, 0);
		cont.addShapestoArray(square2, 1);
		cont.addShapestoArray(square3, 2);
		cont.addShapestoArray(square4, 3);
		cont.addShapestoArray(square5, 4);
		cont.addShapestoArray(square6, 5);
		cont.addShapestoArray(circle1, 0);
		cont.addShapestoArray(circle2, 0);
		cont.addShapestoArray(circle1, 1);
		cont.addShapestoArray(circle2, 2);
		cont.addShapestoArray(circle1, 3);
		cont.addShapestoArray(circle2, 4);
		cont.addShapestoArray(circle1, 5);
		cont.addShapestoArray(circle2, 6);
		cont.addShapestoArray(circle1, 7);
		
		
		cont.startPlaying();
		
	}
}
