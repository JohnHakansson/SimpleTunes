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

public class TestFX extends Application {
	Synthesizer synth;
	UnitOscillator osc;
	LineOut lineOut;

	Line line500;
	Circle circle_Red, circle_Green, circle_Blue;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;

	public void start(Stage primaryStage) {

		// Create Circles
		circle_Red = new Circle(50.0f, Color.RED);
		circle_Red.setCursor(Cursor.HAND);
		circle_Red.setOnMousePressed(circleOnMousePressedEventHandler);
		circle_Red.setOnMouseDragged(circleOnMouseDraggedEventHandler);

		circle_Green = new Circle(50.0f, Color.GREEN);
		circle_Green.setCursor(Cursor.MOVE);
		circle_Green.setCenterX(150);
		circle_Green.setCenterY(150);
		circle_Green.setOnMousePressed(circleOnMousePressedEventHandler);
		circle_Green.setOnMouseDragged(circleOnMouseDraggedEventHandler);

		circle_Blue = new Circle(50.0f, Color.BLUE);
		circle_Blue.setCursor(Cursor.CROSSHAIR);
		circle_Blue.setTranslateX(300);
		circle_Blue.setTranslateY(100);
		circle_Blue.setOnMousePressed(circleOnMousePressedEventHandler);
		circle_Blue.setOnMouseDragged(circleOnMouseDraggedEventHandler);

		line500 = new Line(500, 0, 500, 1080);

		Group root = new Group();
		root.getChildren().addAll(circle_Red, circle_Green, circle_Blue, line500);

		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 1920, 1080));

		primaryStage.show();

		synth = JSyn.createSynthesizer();
		synth.start();

		synth.add(osc = new SawtoothOscillatorBL());
		synth.add(lineOut = new LineOut());
		osc.output.connect(0, lineOut.input, 0);
		osc.output.connect(0, lineOut.input, 1);
		osc.frequency.set(345.0);
		osc.amplitude.set(0);
		lineOut.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((Circle) (t.getSource())).getTranslateX();
			orgTranslateY = ((Circle) (t.getSource())).getTranslateY();

		}
	};

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((Circle) (t.getSource())).setTranslateX(newTranslateX);
			((Circle) (t.getSource())).setTranslateY(newTranslateY);

			if (newTranslateX > 500) {
				osc.amplitude.set(0.6f);

			}

			else if (newTranslateX < 500) {
				osc.amplitude.set(0);
			}
		}
	};
}
