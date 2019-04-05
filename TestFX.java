package simpleTunes;

import com.jsyn.JSyn;

import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


/**
 * 
 * The class TestFX is a first draft of the basic estimated functionality of SimpleTunes
 * 
 * @author grupp 10
 *
 */
public class TestFX extends Application {
	private Synthesizer synth;
	private UnitOscillator osc;
	private LineOut lineOut;

	private Line line500;
	private Circle circleRed, circleGreen, circleBlue;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	
	/**
	 * Overshadows start method in Application. Is required in order to start JavaFX program
	 */
	public void start(Stage primaryStage) {

		// Create Circles
		circleRed = new Circle(50.0f, Color.RED);
		circleRed.setCursor(Cursor.HAND);
		circleRed.setOnMousePressed(circleOnMousePressedEventHandler);
		circleRed.setOnMouseDragged(circleOnMouseDraggedEventHandler);

		circleGreen = new Circle(50.0f, Color.GREEN);
		circleGreen.setCursor(Cursor.MOVE);
		circleGreen.setCenterX(150);
		circleGreen.setCenterY(150);
		circleGreen.setOnMousePressed(circleOnMousePressedEventHandler);
		circleGreen.setOnMouseDragged(circleOnMouseDraggedEventHandler);

		circleBlue = new Circle(50.0f, Color.BLUE);
		circleBlue.setCursor(Cursor.CROSSHAIR);
		circleBlue.setTranslateX(300);
		circleBlue.setTranslateY(100);
		circleBlue.setOnMousePressed(circleOnMousePressedEventHandler);
		circleBlue.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		
		//Create line
		line500 = new Line(500, 0, 500, 1080);
		line500.setStroke(Color.WHITESMOKE);
		
		//Add graphical components to scene
		Group root = new Group();
		root.getChildren().addAll(circleRed, circleGreen, circleBlue, line500);
		

		primaryStage.setResizable(false);
		Scene scene = new Scene(root, 1920, 1080);
		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);

		primaryStage.show();
		
		//Creates synthesizer to generate sounds
		synth = JSyn.createSynthesizer();
		synth.start();

		synth.add(osc = new SineOscillator());
		synth.add(lineOut = new LineOut());
		osc.output.connect(0, lineOut.input, 0);
		osc.output.connect(0, lineOut.input, 1);
		osc.frequency.set(345.0);
		osc.amplitude.set(0);
		lineOut.start();
	}
	
	//Starts JavaFX program
	public static void main(String[] args) {
		launch(args);
	}
	
	//Creates EventHandler that allows users to press the circles
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((Circle) (t.getSource())).getTranslateX();
			orgTranslateY = ((Circle) (t.getSource())).getTranslateY();

		}
	};
	
	//Creates EventHandler that allows users to drag the circles
	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((Circle) (t.getSource())).setTranslateX(newTranslateX);
			((Circle) (t.getSource())).setTranslateY(newTranslateY);
			
			//If circle dragged right of line: play sound
			if (newTranslateX > 500) {
				osc.amplitude.set(0.6f);

			}
			
			//If circle dragged left of line: stop sound
			else if (newTranslateX < 500) {
				osc.amplitude.set(0);
			}
		}
	};
}
