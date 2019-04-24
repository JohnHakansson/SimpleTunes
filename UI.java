package simpleTunes;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
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
import javafx.util.Duration;

/*
 * This is the main User Interface class. It displays the actual widow,
 * handle event handlers and add/remove shapes from the window.
 * 
 * @author Jesper Lindberg, Matilda Frimodig, Roland Askelöf, Tom Lanhed Sivertsson
 *  
 */

public class UI extends Application {
	private Controller controller;

	private Stage window;
	private BorderPane layout;

	private Group poolGroup = new Group();

	private Pane poolPane = new Pane(poolGroup);

	private VBox vbox;
	private ToolBar toolbar;
	private Scene mainScene;

	private Rectangle[][] squares = new Rectangle[8][4];

	private Line movingLine = new Line();
	private TranslateTransition lineTransition = new TranslateTransition();

	/*
	 * Start method for the javaFX application. Here the components are created and
	 * set placed onto the stage.
	 * 
	 * @param primaryStage the stage that holds all other components.
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) throws Exception {
		controller = new Controller(this);
		window = primaryStage;

		poolPane.setPrefSize(1200, 848);
		poolPane.setStyle("-fx-background-color: Black");

		// Generating the cells used by the grid and placing them in the shapeGroup.
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				squares[i][j] = new Rectangle(j * 100 + 800, i * 100, 100, 100);
				squares[i][j].setFill(Color.BLACK);
				squares[i][j].setStroke(Color.GREEN);
				squares[i][j].setStrokeWidth(3);
				poolGroup.getChildren().add(squares[i][j]);

			}

		}

		movingLine.setStartX(800);
		movingLine.setStartY(5);
		movingLine.setEndX(1200);
		movingLine.setEndY(5);
		movingLine.setStroke(Color.WHITE);
		movingLine.setStrokeWidth(5);

		lineTransition.setDuration(Duration.seconds(4));
		lineTransition.setToY(800);
		lineTransition.setAutoReverse(false);
		lineTransition.setCycleCount(Animation.INDEFINITE);
		lineTransition.setNode(movingLine);

		poolGroup.getChildren().add(movingLine);

		Image playImage = new Image(getClass().getResourceAsStream("/images/playButton.png"));
		Image refreshImage = new Image(getClass().getResourceAsStream("/images/refreshButton.png"));
		Image pauseImage = new Image(getClass().getResourceAsStream("/images/pauseButton.png"));
		Image clearImage = new Image(getClass().getResourceAsStream("/images/clearButton.png"));

		Button playButton = new Button();
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
			controller.stopPlaying();
		});

		Button resetButton = new Button();
		resetButton.setGraphic(new ImageView(clearImage));
		resetButton.setOnAction(e -> {
			controller.removeShapesFromPool(poolGroup);

		});

		toolbar = new ToolBar(playButton, pauseButton, new Separator(), refreshButton, resetButton);
		toolbar.setPrefHeight(48);

		vbox = new VBox();
		vbox.getChildren().addAll(toolbar);

		layout = new BorderPane();
		layout.setCenter(poolPane);
		layout.setTop(vbox);

		mainScene = new Scene(layout, 1200, 850);
		mainScene.setFill(Color.BLACK);

		window.setScene(mainScene);
		window.setResizable(false);
		window.setTitle("SimpleTunes");
		window.show();

	}

	public void startMovingLine() {
		lineTransition.play();
	}

	public void stopMovingLine() {
		lineTransition.stop();
		lineTransition.setFromY(5);

	}

	/*
	 * Method for placing the dragged shape in the sounds array located in the
	 * controller class.
	 * 
	 * @param shape the random shape generated in the controller.
	 * 
	 * @return the event handler.
	 */
	public EventHandler<MouseEvent> getMouseEventReleased(MusicShape shape) {
		EventHandler<MouseEvent> onMouseReleased = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				for (int i = 0; i < squares.length; i++) {
					for (int j = 0; j < squares[i].length; j++) {

						if (squares[i][j].contains(t.getSceneX(), t.getSceneY() - toolbar.getHeight())) {
							controller.addShapestoArray(shape, i, j);

						}

					}

				}

			}

		};

		return onMouseReleased;
	}

	/*
	 * Method for placing the random generated shape in the poolGroup.
	 * 
	 * @param shape the random shape generated in the controller.
	 */
	public void addShape(MusicShape shape) {
		Random rand = new Random();

		shape.setLayoutX(rand.nextInt((int) ((mainScene.getWidth() - 1000))));
		shape.setLayoutY(rand.nextInt((int) ((mainScene.getHeight() - toolbar.getHeight()) - 200)));

		poolGroup.getChildren().add(shape.getShape());
	}

	/*
	 * Method for removing the shape from the poolGroup and adding the shape to the
	 * shapeGroup. Also generating one new shape in the poolGroup each time one is
	 * removed.
	 * 
	 * @param shape the random shape generated in the controller.
	 */
	public void removeShape(MusicShape shape, int row, int column) {

		shape.getShape().setTranslateX(0);
		shape.getShape().setTranslateY(0);

		shape.setLayoutX(squares[row][column].getX());

		shape.setLayoutY(squares[row][column].getY());

		shape.nullifyEventHandlers();

		controller.generateShape(1);

	}

	public static void main(String[] args) {
		launch(args);

	}

}
