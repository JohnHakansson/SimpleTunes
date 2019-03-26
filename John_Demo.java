package simpleTunes;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;

public class John_Demo extends Application {
	private Stage window;
	private Color black = Color.BLACK;
	private Color white = Color.WHITE;
	private Color aquamarine = Color.AQUAMARINE;
	private Color violet = Color.BLUEVIOLET;
	private Rectangle rect = new Rectangle(150, 200, 100, 100);
	private Circle circle = new Circle(200, 365, 50, violet);
	private Polygon triangle = new Polygon();
	private Line line500 = new Line(500, 0, 500, 600);
	private Scene scene;
	private MediaPlayer mediaPlayer;
	private String musicFile1 = "sounds/17569__danglada__c-major.wav";
	private String musicFile2 = "sounds/17567__danglada__a-major.wav";
	private String musicFile3 = "sounds/17568__danglada__b-major.wav";
	private Media[] soundFiles = { new Media(musicFile1),
			new Media(musicFile2), new Media(musicFile3)};
	private double orgSceneX;
	private double orgSceneY;
	private double orgTranslateX;
	private double orgTranslateY;
	
	private double newTranslateX;
	private double newTranslateY;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		window = stage;
		window.setTitle("Demo - John");

		rect.setFill(aquamarine);
		line500.setStroke(white);
		triangle.getPoints().addAll(new Double[] { 200.0, 75.0, 250.0, 180.0, 150.0, 180.0 });
		triangle.setFill(Color.CHARTREUSE);

		Group root = new Group();

		scene = new Scene(root, 1200, 600);
		scene.setFill(black);

		rect.setOnMousePressed(getMouseEvent());
		rect.setOnMouseDragged(getMouseDragged(0));

		triangle.setOnMousePressed(getMouseEvent());
		triangle.setOnMouseDragged(getMouseDragged(1));

		circle.setOnMousePressed(getMouseEvent());
		circle.setOnMouseDragged(getMouseDragged(2));

		root.getChildren().add(rect);
		root.getChildren().add(line500);
		root.getChildren().add(circle);
		root.getChildren().add(triangle);

		window.setScene(scene);
		window.show();
	}

	public EventHandler<MouseEvent> getMouseEvent() {
		EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				orgSceneX = t.getSceneX();
				orgSceneY = t.getSceneY();
				orgTranslateX = ((Shape) (t.getSource())).getTranslateX();
				orgTranslateY = ((Shape) (t.getSource())).getTranslateY();
			}
		};

		return circleOnMousePressedEventHandler;
	}

	public EventHandler<MouseEvent> getMouseDragged(int sound) {
		EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				double offsetX = t.getSceneX() - orgSceneX;
				double offsetY = t.getSceneY() - orgSceneY;
				newTranslateX = orgTranslateX + offsetX;
				newTranslateY = orgTranslateY + offsetY;

				((Shape) (t.getSource())).setTranslateX(newTranslateX);
				((Shape) (t.getSource())).setTranslateY(newTranslateY);
			}
		};
		
		if(newTranslateY > line500.getEndY()) {
			mediaPlayer = new MediaPlayer(soundFiles[sound]);
			mediaPlayer.play();
		}
		return circleOnMouseDraggedEventHandler;

	}
}