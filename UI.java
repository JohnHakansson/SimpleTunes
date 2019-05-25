package simpleTunes;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
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
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/*
 * This is the main User Interface class. It displays the actual widow,
 * handle event handlers and add/remove shapes from the window.
 * 
 * @author Jesper Lindberg, Matilda Frimodig, Roland Askelöf, Tom Lanhed Sivertsson, John H�kansson
 *  
 */

public class UI extends Application {
	private Controller controller;

	private Stage window;
	private BorderPane layout;

	private Group poolGroup = new Group();

	private Pane poolPane = new Pane(poolGroup);
	private Pane rightSpacer = new Pane();

	private ToolBar toolbar;
	private Scene mainScene;

	private LoginWindow login;

	private Rectangle[][] squares = new Rectangle[4][16];

	private Line movingLine = new Line();
	private TranslateTransition lineTransition = new TranslateTransition();

	private ChoiceBox<String> listOfUsers;

	private Button onlineButton;
	private Button connectButton;
	private Button exitButton;

	private String username;

	private Label connectMessage;

	private Circle onlineCircle;

	private Text usernameText;

	private double xOffset;
	private double yOffset;

//	private Cursor deleteCursor = new ImageCursor(new Image("images/trashCanImage.png"));
//	private Cursor handCursor = new ImageCursor(new Image("images/handClick.png"));
	private Separator onlineSeperator;

	private ObservableList<String> listOfOnlineUser = FXCollections.observableList(new ArrayList<String>());

	/*
	 * Start method for the javaFX application. Here some components are created and
	 * placed onto the stage.
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
		poolPane.setId("poolPane");

		// Generating the cells used by the grid and adding them to the UI.
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				squares[i][j] = new Rectangle(j * 100, i * 100 + 600 - 50, 100, 100);
				squares[i][j].setFill(Color.BLACK);
				squares[i][j].setStroke(Color.GREEN);
				squares[i][j].setStrokeWidth(3);
				squares[i][j].setId("shapeInsertion");
				poolGroup.getChildren().add(squares[i][j]);

			}

		}

		movingLine.setStartX(5);
		movingLine.setStartY(550);
		movingLine.setEndX(5);
		movingLine.setEndY(995);
		movingLine.setStroke(Color.WHITE);
		movingLine.setStrokeWidth(5);

		lineTransition.setDuration(Duration.seconds(8));
		lineTransition.setToX(1595);
		lineTransition.setAutoReverse(false);
		lineTransition.setCycleCount(Animation.INDEFINITE);
		lineTransition.setNode(movingLine);
		lineTransition.setRate(1.0);

		lineTransition.setInterpolator(Interpolator.LINEAR);

		poolGroup.getChildren().add(movingLine);

		Image playImage = new Image(getClass().getResourceAsStream("/images/playButton.png"));
		Image refreshImage = new Image(getClass().getResourceAsStream("/images/generateShapesButton.png"));
		Image pauseImage = new Image(getClass().getResourceAsStream("/images/stopButton.png"));
		Image clearImage = new Image(getClass().getResourceAsStream("/images/clearButton.png"));

		Button playButton = new Button();
		playButton.setGraphic(new ImageView(playImage));
		playButton.setTooltip(new Tooltip("Start the timeline and play music!"));
		playButton.setOnAction(e -> {
			startMovingLine();
			controller.startPlaying();

		});

		Button refreshButton = new Button();
		refreshButton.setGraphic(new ImageView(refreshImage));
		refreshButton.setTooltip(new Tooltip("Generate new shapes."));
		refreshButton.setOnAction(e -> {
			controller.refreshShapesFromPool(poolGroup);
			controller.generateShape(10);

		});

		Button stopButton = new Button();
		stopButton.setGraphic(new ImageView(pauseImage));
		stopButton.setTooltip(new Tooltip("Stop the timeline."));
		stopButton.setOnAction(e -> {
			stopMovingLine();
			controller.stopPlaying();
		});

		Button resetButton = new Button();
		resetButton.setGraphic(new ImageView(clearImage));
		resetButton.setTooltip(new Tooltip("Reset the window."));
		resetButton.setOnAction(e -> {
			controller.removeShapesFromPool(poolGroup);

		});

		exitButton = new Button("Exit");
		exitButton.setId("exitButton");
		exitButton.setOnAction(e -> {
			System.exit(0);
		});

		onlineButton = new Button("Go Online");
		onlineButton.setOnAction(e -> {

			login = new LoginWindow(controller);
			login.display();

		});

		listOfUsers = new ChoiceBox<String>();

		toolbar = new ToolBar(playButton, stopButton, new Separator(), refreshButton, resetButton, new Separator(),
				onlineButton, rightSpacer, exitButton);
		toolbar.setPrefHeight(48);

		layout = new BorderPane();
		layout.setCenter(poolPane);
		layout.setTop(toolbar);

		mainScene = new Scene(layout, 1600, 1000);
		mainScene.setFill(Color.BLACK);
		mainScene.getStylesheets().add(getClass().getResource("SimpleTunes.css").toExternalForm());

		window.setScene(mainScene);
		window.setResizable(false);
		window.initStyle(StageStyle.UNDECORATED);
		window.show();

		controller.startShapeGenerator();

		HBox.setHgrow(rightSpacer, Priority.SOMETIMES);

		toolbar.setId("toolbar");
		toolbar.setOnMousePressed(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				xOffset = primaryStage.getX() - event.getScreenX();
				yOffset = primaryStage.getY() - event.getScreenY();
			}
		});

		toolbar.setOnMouseDragged(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() + xOffset);
				primaryStage.setY(event.getScreenY() + yOffset);
			}
		});
	}

	/*
	 * Method for starting the moving line.
	 * 
	 */
	public void startMovingLine() {
		lineTransition.play();

	}

	/*
	 * Method for stopping the moving line.
	 * 
	 */
	public void stopMovingLine() {
		lineTransition.stop();
		lineTransition.setFromX(5);

	}

	/*
	 * Method for removing a shape when clicked.
	 * 
	 * @param shape the random shape generated in the controller.
	 * 
	 * @return the event handler.
	 */
	public EventHandler<MouseEvent> getMouseRemove(MusicShape shape, int row, int column) {
		EventHandler<MouseEvent> OnMouseClicked = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				System.out.println("removing shape from grid");

				poolGroup.getChildren().remove(shape.getShape());
				controller.removeSound(shape.getRow(), shape.getColumn());

				if (controller.isOnline()) {
					controller.sendRemoveShape(row, column);

				}

			}

		};

		return OnMouseClicked;
	}

	/*
	 * Method for adding the dragged shape in the sounds array, located in the
	 * controller class.
	 * 
	 * @param shape the random shape generated in the controller.
	 * 
	 * @return the event handler.
	 */
	public EventHandler<MouseEvent> getMouseEventReleased(MusicShape shape) {
		EventHandler<MouseEvent> onMouseReleased = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				System.out.println("Adding shape to grid");
//				shape.getShape().setCursor(handCursor);

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

	public EventHandler<MouseEvent> getMouseEventReleasedInGrid(MusicShape shape) {
		EventHandler<MouseEvent> onMouseReleased = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				System.out.println("Adding shape to grid");
//				shape.getShape().setCursor(handCursor);
				controller.removeSound(shape.getRow(), shape.getColumn());

				if (!shape.getHasBeenMoved()) {

					System.out.println("removing shape from grid");

					poolGroup.getChildren().remove(shape.getShape());

//					if (controller.isOnline()) {
//						controller.sendRemoveShape(row, column);
//
//					}

				}

				else {

					for (int i = 0; i < squares.length; i++) {

						for (int j = 0; j < squares[i].length; j++) {

							if (squares[i][j].contains(t.getSceneX(), t.getSceneY() - toolbar.getHeight())) {
								controller.addShapestoArray(shape, i, j);
								shape.setHasBeenMoved(false);

							}

						}
					}
				}

			}

		};

		return onMouseReleased;
	}

	public EventHandler<MouseEvent> getDragDetected(MusicShape shape) {
		EventHandler<MouseEvent> onDragDetected = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				shape.setHasBeenMoved(true);
				System.out.println("Set has been moved to : " + shape.getHasBeenMoved());
			}

		};

		return onDragDetected;

	}

	/*
	 * Method for adding the shape to the UI.
	 * 
	 * @param shape the random shape generated in the controller.
	 */
	public void addShape(MusicShape shape) {

		poolGroup.getChildren().add(shape.getShape());

	}

	public void removeShape(MusicShape shape) {

		Platform.runLater(new Runnable() {
			public void run() {

				System.out.println(shape.toString());

				poolGroup.getChildren().remove(shape.getShape());

			}

		});

	}

	/*
	 * Method for setting a random location for the incoming shape. If the incoming
	 * shape intersects with any shape in the poolGroup the method calls it self
	 * again, sets a new random position,
	 * 
	 * @param shape the random shape generated in the controller.
	 */
	public void setRandomLocation(MusicShape shape) {

		Random rand = new Random();

		shape.getShape().setTranslateX(0);
		shape.getShape().setTranslateY(0);

		shape.setLayoutX(rand.nextInt((int) ((mainScene.getWidth() - 100))));
		shape.setLayoutY(rand.nextInt((int) ((mainScene.getHeight() - toolbar.getHeight()) - 550)));

		for (Node x : poolGroup.getChildren()) {

			if (shape.getShape().getBoundsInParent().intersects(x.getBoundsInParent()) && !shape.getShape().equals(x)) {

				setRandomLocation(shape);

//				System.out.println("Redo location");

			}

		}

	}

	/*
	 * Method for adding the shape to the grid.
	 * 
	 * @param shape the random shape generated in the controller.
	 * 
	 * @param row the row in the sounds array.
	 * 
	 * @param column the column in the sounds array.
	 */
	public void setGridPlacement(MusicShape shape, int row, int column) {

		shape.getShape().setTranslateX(0);
		shape.getShape().setTranslateY(0);

		shape.setLayoutX(squares[row][column].getX());

		shape.setLayoutY(squares[row][column].getY());

//		shape.nullifyEventHandlers();

		shape.getShape().setOnMouseReleased(getMouseEventReleasedInGrid(shape));
		shape.getShape().setOnDragDetected(getDragDetected(shape));

		controller.generateShape(1);
//
//		Platform.runLater(new Runnable() {
//			public void run() {
//				shape.getShape().setCursor(deleteCursor);
//			}
//		});

	}

	/*
	 * Method for adding the shape to the grid when sent from another user online.
	 * 
	 * @param shape the shape sent from the other user.
	 * 
	 * @param row the row in the sounds array sent from the other user.
	 * 
	 * @param column the column in the sounds array sent from the other user.
	 */
	public void setShapeFromOnline(MusicShape shape, int row, int column) {

		shape.getShape().setTranslateX(0);
		shape.getShape().setTranslateY(0);

		shape.setLayoutX(squares[row][column].getX());

		shape.setLayoutY(squares[row][column].getY());

//		shape.nullifyEventHandlers();

		shape.getShape().setOnMousePressed(getMouseRemove(shape, row, column));

		Platform.runLater(new Runnable() {

			public void run() {
				poolGroup.getChildren().add(shape.getShape());
			}
		});
	}

	/*
	 * Method for updating the list of online users when a user comes online.
	 * 
	 * @param newOnlineUser the new online user.
	 */
	public void updateUserList(String newOnlineUser) {

		listOfOnlineUser.add(newOnlineUser);

		listOfUsers.setItems(listOfOnlineUser);

	}

	/*
	 * Method for updating the list of online users when a user disconnects.
	 * 
	 * @param disconnectedUser the disconnected user.
	 */
	public void removeFromUserList(String disconnectedUser) {

		Platform.runLater(new Runnable() {

			public void run() {
				listOfOnlineUser.remove(disconnectedUser);

			}

		});

		listOfUsers.setItems(listOfOnlineUser);

	}

	public String getUsername() {

		return username;

	}

	/*
	 * Method for updating the connect request message depending on the answer given
	 * from the ConnectWindow. Also altering the UI based on if the answer if true
	 * or false.
	 * 
	 * @param crm the ConnectRequestMessage sent from the online user.
	 */
	public void openConnectMessage(ConnectRequestMessage crm) {

		Platform.runLater(new Runnable() {

			public void run() {
				boolean answer;

				answer = new ConnectWindow(crm.getMessage()).display();

				crm.setConnectRequest(answer);

				controller.sendResponse(crm);

				if (answer) {

					updateMenueConnected(crm.getSenderUsername());
					controller.removeShapesFromGrid();
				}
			}
		});

	}

	/*
	 * Method for updating the UI when a user goes online (closing the login
	 * window). Adding the list of users, connect button and online indicator to the
	 * menu.
	 * 
	 */
	public void closeLogin() {

		login.closeStage();

		username = login.getUserName();

		usernameText = new Text(username);

		connectMessage = new Label("Connect with user:");

		connectMessage.setFont(new Font("Arial Bold", 14));

		onlineCircle = new Circle(5);

		onlineCircle.setFill(Color.GREEN);

		connectButton = new Button("Connect");
		connectButton.setOnAction(e -> {

			String selectedUsername = listOfUsers.getSelectionModel().getSelectedItem();

			if (selectedUsername != null) {

				controller.connectToUser(selectedUsername);

				updateMenueWaiting(selectedUsername);

			}
		});

		Platform.runLater(new Runnable() {

			public void run() {

				toolbar.getItems().remove(rightSpacer);
				toolbar.getItems().remove(exitButton);

				onlineButton.setText("Go offline");

				onlineButton.setOnAction(e -> {

					controller.disconnect();

					toolbar.getItems().remove(connectButton);
					toolbar.getItems().remove(listOfUsers);
					toolbar.getItems().remove(usernameText);
					toolbar.getItems().remove(onlineCircle);
					toolbar.getItems().remove(onlineSeperator);
					toolbar.getItems().remove(connectMessage);

					onlineButton.setText("Go online");
					onlineButton.setOnAction(e1 -> {

						login = new LoginWindow(controller);
						login.display();

					});

				});

				toolbar.getItems().add(connectMessage);

				toolbar.getItems().add(listOfUsers);

				toolbar.getItems().add(connectButton);

				onlineSeperator = new Separator();
				onlineSeperator.setHalignment(HPos.RIGHT);

				toolbar.getItems().add(onlineSeperator);

				toolbar.getItems().add(usernameText);

				toolbar.getItems().add(onlineCircle);

				toolbar.getItems().add(rightSpacer);
				toolbar.getItems().add(exitButton);

			}
		});

	}

	/*
	 * Method for updating the UI menu bar when a user connects with another user.
	 * 
	 * @param username the username of the selected user.
	 */
	public void updateMenueConnected(String username) {

		Platform.runLater(new Runnable() {

			public void run() {

				listOfUsers.setDisable(true);
				connectButton.setDisable(true);
				connectMessage.setText("Connected to: " + username);

			}
		});

	}

	/*
	 * Method for updating the UI menu bar when a user is waiting for the response
	 * from the request.
	 * 
	 * @param username the username of the selected user.
	 */
	public void updateMenueWaiting(String username) {

		listOfUsers.setDisable(true);
		connectButton.setDisable(true);
		connectMessage.setText("Waiting for response from: " + username);

	}

	/*
	 * Method for resetting the UI menu bar.
	 * 
	 */
	public void updateMenueDefault() {

		Platform.runLater(new Runnable() {

			public void run() {

				connectMessage.setText("Connect with user: ");
				listOfUsers.setDisable(false);
				connectButton.setDisable(false);

			}
		});

	}

	/*
	 * Method for updating the message in the log in window.
	 * 
	 * @param info the message sent when the log in is not accepted.
	 */
	public void loginNotOK(String info) {

		login.userNameNotOK(info);

	}

	public Group getPoolGroup() {
		return poolGroup;
	}

	public static void main(String[] args) {
		launch(args);

	}

}
