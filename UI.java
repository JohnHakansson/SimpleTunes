package simpleTunes;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
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

	private LoginWindow login;

	private Rectangle[][] squares = new Rectangle[4][18];

	private Line movingLine = new Line();
	private TranslateTransition lineTransition = new TranslateTransition();

	private ChoiceBox<String> listOfUsers;

	private Button onlineButton;

	private String username;

	private ObservableList<String> listOfOnlineUser = FXCollections.observableList(new ArrayList<String>());

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
		window.setOnCloseRequest(e -> {
			System.exit(0);
		});

		poolPane.setStyle("-fx-background-color: Black");

		// Generating the cells used by the grid and placing them in the shapeGroup.
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				squares[i][j] = new Rectangle(j * 100, i * 100 + 600 - 50, 100, 100);
				squares[i][j].setFill(Color.BLACK);
				squares[i][j].setStroke(Color.GREEN);
				squares[i][j].setStrokeWidth(3);
				poolGroup.getChildren().add(squares[i][j]);

			}

		}

		movingLine.setStartX(5);
		movingLine.setStartY(550);
		movingLine.setEndX(5);
		movingLine.setEndY(995);
		movingLine.setStroke(Color.WHITE);
		movingLine.setStrokeWidth(5);

		lineTransition.setDuration(Duration.seconds(9));
		lineTransition.setToX(1795);
		lineTransition.setAutoReverse(false);
		lineTransition.setCycleCount(Animation.INDEFINITE);
		lineTransition.setNode(movingLine);
		lineTransition.setRate(1.0);

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
			controller.refreshShapesFromPool(poolGroup);
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

		onlineButton = new Button("Go Online");
		onlineButton.setOnAction(e -> {

			login = new LoginWindow(controller);
			login.display();

		});

		listOfUsers = new ChoiceBox<String>();

		toolbar = new ToolBar(playButton, pauseButton, new Separator(), refreshButton, resetButton, new Separator(),
				onlineButton);
		toolbar.setPrefHeight(48);

		vbox = new VBox();
		vbox.getChildren().addAll(toolbar);

		layout = new BorderPane();
		layout.setCenter(poolPane);
		layout.setTop(vbox);

		mainScene = new Scene(layout, 1800, 1000);
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
		lineTransition.setFromX(5);

	}

	public EventHandler<MouseEvent> getMouseRemove(MusicShape shape) {
		EventHandler<MouseEvent> OnMouseClicked = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				poolGroup.getChildren().remove(shape.getShape());
				controller.removeSound(shape.getRow(), shape.getColumn());

			}
		};

		return OnMouseClicked;
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

		shape.setLayoutX(rand.nextInt((int) ((mainScene.getWidth() - 100))));
		shape.setLayoutY(rand.nextInt((int) ((mainScene.getHeight() - toolbar.getHeight()) - 550)));

		poolGroup.getChildren().add(shape.getShape());

	}

	/*
	 * Method for removing the shape from the poolGroup and adding the shape to the
	 * shapeGroup. Also generating one new shape in the poolGroup each time one is
	 * removed.
	 * 
	 * @param shape the random shape generated in the controller.
	 */
	public void setGridPlacement(MusicShape shape, int row, int column) {

		shape.getShape().setTranslateX(0);
		shape.getShape().setTranslateY(0);

		shape.setLayoutX(squares[row][column].getX());

		shape.setLayoutY(squares[row][column].getY());

		shape.nullifyEventHandlers();

		shape.getShape().setOnMousePressed(getMouseRemove(shape));

		controller.generateShape(1);

	}

	public void setShapeFromOnline(MusicShape shape, int row, int column) {

		shape.getShape().setTranslateX(0);
		shape.getShape().setTranslateY(0);

		System.out.println("ROw : " + row);
		System.out.println("Column : " + column);

		shape.setLayoutX(squares[row][column].getX());

		shape.setLayoutY(squares[row][column].getY());

		shape.nullifyEventHandlers();

		shape.getShape().setOnMousePressed(getMouseRemove(shape));

		Platform.runLater(new Runnable() {

			public void run() {
				poolGroup.getChildren().add(shape.getShape());
			}
		});
	}

//	public void updateUserList(ArrayList<String> list) {
//
//		listOfOnlineUser = list;
//
//		listOfUsers.setItems(FXCollections.observableList(listOfOnlineUser));
//
//	}

	public void updateUserList(String newOnlineUser) {

		listOfOnlineUser.add(newOnlineUser);

		System.out.println("Ny online user::: " + newOnlineUser);

		for (String x : listOfOnlineUser) {

			System.out.println(x);

		}

		listOfUsers.setItems(listOfOnlineUser);

	}

	public String getUsername() {

		return username;

	}

	public void openConnectMessage(ConnectRequestMessage crm) {

		Platform.runLater(new Runnable() {

			public void run() {
				boolean answer;

				answer = new ConnectWindow(crm.getMessage()).display();

				crm.setConnectRequest(answer);

				System.out.println("Answer ==== " + answer);

				controller.sendResponse(crm);
			}
		});

	}

	public void closeLogin() {

		login.closeStage();

		username = login.getUserName();

		Text usernameText = new Text(username);

		Circle onlineCircle = new Circle(5);

		onlineCircle.setFill(Color.GREEN);

		Button connectButton = new Button("Connect");
		connectButton.setOnAction(e -> {

			String str = listOfUsers.getSelectionModel().getSelectedItem();

			controller.connectToUser(str);

		});

		Platform.runLater(new Runnable() {

			public void run() {

				onlineButton.setText("Go offline");

				toolbar.getItems().add(listOfUsers);

				toolbar.getItems().add(connectButton);

				Separator sep = new Separator();
				sep.setHalignment(HPos.RIGHT);

				toolbar.getItems().add(sep);

				toolbar.getItems().add(usernameText);

				toolbar.getItems().add(onlineCircle);

			}
		});

	}

	public static void main(String[] args) {
		launch(args);

	}

}
