package simpleTunes;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class TestUI extends Application {
	private Stage window;
	private BorderPane layout;
	private Group poolGroup = new Group();
	private Pane poolPane = new Pane(poolGroup);
	private Pane gridPane;
	private VBox vbox;
	private ToolBar toolbar;
	private Scene mainScene;
	private Controller controller;
	private double orgSceneX;
	private double orgSceneY;
	private double orgTranslateX;
	private double orgTranslateY;

	private Rectangle[] shapeInsertions = new Rectangle[8];
	private Rectangle[][] squares = new Rectangle[8][4];
	
	private Group shapeGroup;
	private Line movingLine = new Line();
	private TranslateTransition lineTransition = new TranslateTransition();

	public void start(Stage primaryStage) throws Exception {

		controller = new Controller(this);
		window = primaryStage;
		//window.initStyle(StageStyle.UNDECORATED);

		poolPane.setPrefSize(600, 848);
		//poolPane.setStyle("-fx-background-color: Black");

		shapeGroup = new Group();
		gridPane = new Pane(shapeGroup);
		gridPane.setPrefSize(400, 848);
		//gridPane.setStyle("-fx-background-color: White");

		for (int i = 0; i < shapeInsertions.length; i++) {
			shapeInsertions[i] = new Rectangle(695, 1.0f + (i * 100), 100, 100);

			//shapeInsertions[i].setStroke(Color.WHITESMOKE);
			shapeInsertions[i].setStrokeWidth(2);
			shapeInsertions[i].setId("shapeInsertion");
			//shapeInsertions[i].setStyle("-fx-stroke-dash-array: 1 10 10 1;");

			poolGroup.getChildren().add(shapeInsertions[i]);
		}

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				squares[i][j] = new Rectangle(j * 100, i * 100, 100, 100);
//				squares[i][j].setFill(Color.BLACK);
//				squares[i][j].setStroke(Color.GREEN);
				//squares[i][j].setStrokeWidth(3);
				squares[i][j].setId("squares");
				shapeGroup.getChildren().add(squares[i][j]);
			}
		}

		movingLine.setStartX(0);
		movingLine.setStartY(5);
		movingLine.setEndX(400);
		movingLine.setEndY(5);
		//movingLine.setStroke(Color.WHITE);
		movingLine.setStrokeWidth(5);
		movingLine.setId("movingLine");

		lineTransition.setDuration(Duration.seconds(4));
		lineTransition.setToY(800);
		lineTransition.setAutoReverse(false);
		lineTransition.setCycleCount(Animation.INDEFINITE);
		lineTransition.setNode(movingLine);

		shapeGroup.getChildren().add(movingLine);

		Image playImage = new Image(getClass().getResourceAsStream("/images/playButton.png"));
		Image refreshImage = new Image(getClass().getResourceAsStream("/images/refreshButton.png"));
		Image pauseImage = new Image(getClass().getResourceAsStream("/images/pauseButton.png"));
		Image clearImage = new Image(getClass().getResourceAsStream("/images/clearButton.png"));

		Button playButton = new Button();
		playButton.setId("playButton");
		playButton.setGraphic(new ImageView(playImage));
		playButton.setOnAction(e -> {
			startMovingLine();
			controller.startPlaying();
		});
		Button refreshButton = new Button();
		refreshButton.setGraphic(new ImageView(refreshImage));
		refreshButton.setOnAction(e -> {
			controller.removeShapesFromPool(poolGroup);
			controller.generateShape(10);

		});
		Button pauseButton = new Button();
		pauseButton.setGraphic(new ImageView(pauseImage));
		pauseButton.setOnAction(e -> {
			stopMovingLine();
			controller.stop();
		});
		Button resetButton = new Button();
		resetButton.setId("clearButton");
		resetButton.setGraphic(new ImageView(clearImage));
		resetButton.setOnAction(e -> {
			controller.removeShapesFromPool(poolGroup);
			controller.removeShapesFromGrid(shapeGroup);
		});
		
//		Label text = new Label("Show Sounds");
//		text.setId("text");
		Button showSoundButton = new Button("Show Sounds");
		//showSoundButton.setGraphic(text);
		showSoundButton.setId("showSoundButton");
		
		toolbar = new ToolBar(playButton, pauseButton, new Separator(), refreshButton, resetButton, new Separator(), showSoundButton);
		toolbar.setId("toolbar");

		vbox = new VBox();
		vbox.setId("vbox");
		vbox.getChildren().addAll(toolbar);

		layout = new BorderPane();
		layout.setCenter(poolPane);
		layout.setRight(gridPane);
		layout.setTop(vbox);

		mainScene = new Scene(layout, 1200, 855);
		//mainScene.setFill(Color.BLACK);
		
		mainScene.getStylesheets().add(getClass().getResource("SimpleTunes.css").toExternalForm());
		//s.getStylesheets().add(getClass().getResource("SimpleTunes.css").toExternalForm());
		window.setScene(mainScene);
		window.setResizable(false);
		window.setTitle("SimpleTunes");
		window.show();

	}

	// 4 sek och 8 1/4 noter ger 120bpm
	public void startMovingLine() {

		lineTransition.play();
	}

	public void stopMovingLine() {

		lineTransition.stop();

		lineTransition.setFromY(5);

	}

	public EventHandler<MouseEvent> getMouseEvent(Shape shape) {
		EventHandler<MouseEvent> OnMouseClicked = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				if (shape instanceof MusicSquare) {
					MusicSquare musicSquare = (MusicSquare) shape;
					System.out.println("Square clicked");
					musicSquare.play();
				}

				if (shape instanceof MusicCircle) {
					System.out.println("Circle clicked");
					MusicCircle musicCircle = (MusicCircle) shape;
					musicCircle.play();
				}

				if (shape instanceof MusicTriangle) {
					System.out.println("Triangle clicked");
					MusicTriangle musicTriangle = (MusicTriangle) shape;
					musicTriangle.play();
				}
			}
		};

		return OnMouseClicked;
	}

	public EventHandler<MouseEvent> getMouseEventPressed(Shape shape) {
		EventHandler<MouseEvent> onMousePressed = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {
				orgSceneX = t.getSceneX();
				orgSceneY = t.getSceneY();

				orgTranslateX = shape.getTranslateX();
				orgTranslateY = shape.getTranslateY();

			}
		};

		return onMousePressed;
	}

	public EventHandler<MouseEvent> getMouseEventDragged(Shape shape) {
		EventHandler<MouseEvent> onMouseDragged = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {
				double offsetX = t.getSceneX() - orgSceneX;
				double offsetY = t.getSceneY() - orgSceneY;
				double newTranslateX = orgTranslateX + offsetX;
				double newTranslateY = orgTranslateY + offsetY;

				shape.setTranslateX(newTranslateX);
				shape.setTranslateY(newTranslateY);

			}
		};

		return onMouseDragged;
	}

	public EventHandler<MouseEvent> getMouseEventReleased(Shape shape) {
		EventHandler<MouseEvent> onMouseReleased = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {
				orgSceneX = t.getSceneX();
				orgSceneY = t.getSceneY();

				orgTranslateX = shape.getTranslateX();
				orgTranslateY = shape.getTranslateY();

				for (int i = 0; i < shapeInsertions.length; i++) {
					if (shapeInsertions[i].contains(orgSceneX, orgSceneY - toolbar.getHeight())) {
						controller.addShapestoArray(shape, i);
						System.out.println("row " + i + "XXXX");
					}
				}

			}
		};

		return onMouseReleased;
	}

	public void addShape(Shape shape) {
		Random rand = new Random();

		shape.setLayoutX(rand.nextInt((int) (mainScene.getWidth() - gridPane.getWidth()) - 200));
		shape.setLayoutY(rand.nextInt((int) (mainScene.getHeight() - toolbar.getHeight()) - 200));
		
		poolGroup.getChildren().add(shape);
	}

	public void removeShape(Shape shape, int row, int column) {
		poolGroup.getChildren().remove(shape);
		shapeGroup.getChildren().add(shape);

		shape.setTranslateX(0);
		shape.setTranslateY(0);

		if (shape instanceof MusicCircle) {
			shape.setLayoutX(squares[row][column].getX() + 50);
			shape.setLayoutY(squares[row][column].getY() + 50);
		}

		if (shape instanceof MusicTriangle) {
			shape.setLayoutX(squares[row][column].getX());
			shape.setLayoutY(squares[row][column].getY() - 50);
		}

		if (shape instanceof MusicSquare) {
			shape.setLayoutX(squares[row][column].getX());
			shape.setLayoutY(squares[row][column].getY());
		}

		shape.setOnMousePressed(null);
		shape.setOnMouseDragged(null);
		shape.setOnMouseClicked(null);
		shape.setOnMouseReleased(null);

		controller.generateShape(1);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
