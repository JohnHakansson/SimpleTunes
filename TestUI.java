package simpleTunes;


import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class TestUI extends Application {
	private Stage window;
	private BorderPane layout;
	private Color color;
	private Group poolGroup = new Group();
	private Pane poolPane = new Pane(poolGroup);
	private Pane gridPane;
	private VBox vbox;
	private ToolBar toolbar;
	private Scene mainScene;
	private Controller controller;
	private Rectangle square1x1 = new Rectangle(0.0f, 1.0f, 100, 100);
	private Rectangle square1x2 = new Rectangle(101.0f, 1.0f, 100, 100);
	private Rectangle square1x3 = new Rectangle(200.0f, 1.0f, 100, 100);
	private Rectangle square1x4 = new Rectangle(300.0f, 1.0f, 100, 100);

	private Rectangle square2x1 = new Rectangle(0.0f, 101.0f, 100, 100);
	private Rectangle square2x2 = new Rectangle(100.0f, 101.0f, 100, 100);
	private Rectangle square2x3 = new Rectangle(200.0f, 101.0f, 100, 100);
	private Rectangle square2x4 = new Rectangle(300.0f, 101.0f, 100, 100);

	private Rectangle square3x1 = new Rectangle(0.0f, 201.0f, 100, 100);
	private Rectangle square3x2 = new Rectangle(100.0f, 201.0f, 100, 100);
	private Rectangle square3x3 = new Rectangle(200.0f, 201.0f, 100, 100);
	private Rectangle square3x4 = new Rectangle(300.0f, 201.0f, 100, 100);

	private Rectangle square4x1 = new Rectangle(0.0f, 301.0f, 100, 100);
	private Rectangle square4x2 = new Rectangle(100.0f, 301.0f, 100, 100);
	private Rectangle square4x3 = new Rectangle(200.0f, 301.0f, 100, 100);
	private Rectangle square4x4 = new Rectangle(300.0f, 301.0f, 100, 100);

	private Rectangle square5x1 = new Rectangle(0.0f, 401.0f, 100, 100);
	private Rectangle square5x2 = new Rectangle(100.0f, 401.0f, 100, 100);
	private Rectangle square5x3 = new Rectangle(200.0f, 401.0f, 100, 100);
	private Rectangle square5x4 = new Rectangle(300.0f, 401.0f, 100, 100);

	private Rectangle square6x1 = new Rectangle(0.0f, 501.0f, 100, 100);
	private Rectangle square6x2 = new Rectangle(100.0f, 501.0f, 100, 100);
	private Rectangle square6x3 = new Rectangle(200.0f, 501.0f, 100, 100);
	private Rectangle square6x4 = new Rectangle(300.0f, 501.0f, 100, 100);

	private Rectangle square7x1 = new Rectangle(00.0f, 601.0f, 100, 100);
	private Rectangle square7x2 = new Rectangle(100.0f, 601.0f, 100, 100);
	private Rectangle square7x3 = new Rectangle(200.0f, 601.0f, 100, 100);
	private Rectangle square7x4 = new Rectangle(300.0f, 601.0f, 100, 100);

	private Rectangle square8x1 = new Rectangle(0.0f, 701.0f, 100, 100);
	private Rectangle square8x2 = new Rectangle(100.0f, 701.0f, 100, 100);
	private Rectangle square8x3 = new Rectangle(200.0f, 701.0f, 100, 100);
	private Rectangle square8x4 = new Rectangle(300.0f, 701.0f, 100, 100);

	public void start(Stage primaryStage) throws Exception {
		
		controller = new Controller(this);
		window = primaryStage;

		poolPane.setPrefSize(600, 848);
		poolPane.setStyle("-fx-background-color: Black");

		Group shapeGroup = new Group();
		gridPane = new Pane(shapeGroup);
		gridPane.setPrefSize(400, 848);
		gridPane.setStyle("-fx-background-color: White");

		// Row #1
		square1x1.setFill(color.BLACK);
		square1x1.setStroke(color.GREEN);
		square1x1.setStrokeWidth(3);
		square1x2.setFill(color.BLACK);
		square1x2.setStroke(color.GREEN);
		square1x2.setStrokeWidth(3);
		square1x3.setFill(color.BLACK);
		square1x3.setStroke(color.GREEN);
		square1x3.setStrokeWidth(3);
		square1x4.setFill(color.BLACK);
		square1x4.setStroke(color.GREEN);
		square1x4.setStrokeWidth(3);

		// Row #2
		square2x1.setFill(color.BLACK);
		square2x1.setStroke(color.GREEN);
		square2x1.setStrokeWidth(3);
		square2x2.setFill(color.BLACK);
		square2x2.setStroke(color.GREEN);
		square2x2.setStrokeWidth(3);
		square2x3.setFill(color.BLACK);
		square2x3.setStroke(color.GREEN);
		square2x3.setStrokeWidth(3);
		square2x4.setFill(color.BLACK);
		square2x4.setStroke(color.GREEN);
		square2x4.setStrokeWidth(3);

		// Row #3
		square3x1.setFill(color.BLACK);
		square3x1.setStroke(color.GREEN);
		square3x1.setStrokeWidth(3);
		square3x2.setFill(color.BLACK);
		square3x2.setStroke(color.GREEN);
		square3x2.setStrokeWidth(3);
		square3x3.setFill(color.BLACK);
		square3x3.setStroke(color.GREEN);
		square3x3.setStrokeWidth(3);
		square3x4.setFill(color.BLACK);
		square3x4.setStroke(color.GREEN);
		square3x4.setStrokeWidth(3);

		// Row #4
		square4x1.setFill(color.BLACK);
		square4x1.setStroke(color.GREEN);
		square4x1.setStrokeWidth(3);
		square4x2.setFill(color.BLACK);
		square4x2.setStroke(color.GREEN);
		square4x2.setStrokeWidth(3);
		square4x3.setFill(color.BLACK);
		square4x3.setStroke(color.GREEN);
		square4x3.setStrokeWidth(3);
		square4x4.setFill(color.BLACK);
		square4x4.setStroke(color.GREEN);
		square4x4.setStrokeWidth(3);

		// Row #5
		square5x1.setFill(color.BLACK);
		square5x1.setStroke(color.GREEN);
		square5x1.setStrokeWidth(3);
		square5x2.setFill(color.BLACK);
		square5x2.setStroke(color.GREEN);
		square5x2.setStrokeWidth(3);
		square5x3.setFill(color.BLACK);
		square5x3.setStroke(color.GREEN);
		square5x3.setStrokeWidth(3);
		square5x4.setFill(color.BLACK);
		square5x4.setStroke(color.GREEN);
		square5x4.setStrokeWidth(3);

		// Row #6
		square6x1.setFill(color.BLACK);
		square6x1.setStroke(color.GREEN);
		square6x1.setStrokeWidth(3);
		square6x2.setFill(color.BLACK);
		square6x2.setStroke(color.GREEN);
		square6x2.setStrokeWidth(3);
		square6x3.setFill(color.BLACK);
		square6x3.setStroke(color.GREEN);
		square6x3.setStrokeWidth(3);
		square6x4.setFill(color.BLACK);
		square6x4.setStroke(color.GREEN);
		square6x4.setStrokeWidth(3);

		// Row #7
		square7x1.setFill(color.BLACK);
		square7x1.setStroke(color.GREEN);
		square7x1.setStrokeWidth(3);
		square7x2.setFill(color.BLACK);
		square7x2.setStroke(color.GREEN);
		square7x2.setStrokeWidth(3);
		square7x3.setFill(color.BLACK);
		square7x3.setStroke(color.GREEN);
		square7x3.setStrokeWidth(3);
		square7x4.setFill(color.BLACK);
		square7x4.setStroke(color.GREEN);
		square7x4.setStrokeWidth(3);

		// Row #8
		square8x1.setFill(color.BLACK);
		square8x1.setStroke(color.GREEN);
		square8x1.setStrokeWidth(3);
		square8x2.setFill(color.BLACK);
		square8x2.setStroke(color.GREEN);
		square8x2.setStrokeWidth(3);
		square8x3.setFill(color.BLACK);
		square8x3.setStroke(color.GREEN);
		square8x3.setStrokeWidth(3);
		square8x4.setFill(color.BLACK);
		square8x4.setStroke(color.GREEN);
		square8x4.setStrokeWidth(3);

		shapeGroup.getChildren().add(square1x1);
		shapeGroup.getChildren().add(square1x2);
		shapeGroup.getChildren().add(square1x3);
		shapeGroup.getChildren().add(square1x4);

		shapeGroup.getChildren().add(square2x1);
		shapeGroup.getChildren().add(square2x2);
		shapeGroup.getChildren().add(square2x3);
		shapeGroup.getChildren().add(square2x4);

		shapeGroup.getChildren().add(square3x1);
		shapeGroup.getChildren().add(square3x2);
		shapeGroup.getChildren().add(square3x3);
		shapeGroup.getChildren().add(square3x4);

		shapeGroup.getChildren().add(square4x1);
		shapeGroup.getChildren().add(square4x2);
		shapeGroup.getChildren().add(square4x3);
		shapeGroup.getChildren().add(square4x4);

		shapeGroup.getChildren().add(square5x1);
		shapeGroup.getChildren().add(square5x2);
		shapeGroup.getChildren().add(square5x3);
		shapeGroup.getChildren().add(square5x4);

		shapeGroup.getChildren().add(square6x1);
		shapeGroup.getChildren().add(square6x2);
		shapeGroup.getChildren().add(square6x3);
		shapeGroup.getChildren().add(square6x4);

		shapeGroup.getChildren().add(square7x1);
		shapeGroup.getChildren().add(square7x2);
		shapeGroup.getChildren().add(square7x3);
		shapeGroup.getChildren().add(square7x4);

		shapeGroup.getChildren().add(square8x1);
		shapeGroup.getChildren().add(square8x2);
		shapeGroup.getChildren().add(square8x3);
		shapeGroup.getChildren().add(square8x4);
		
		Image playImage = new Image(getClass().getResourceAsStream("/images/playButton.png"));
		Image pauseImage = new Image(getClass().getResourceAsStream("/images/pauseButton.png"));
		Image clearImage = new Image(getClass().getResourceAsStream("/images/clearButton.png"));
		
		Button playButton = new Button();
		playButton.setGraphic(new ImageView(playImage));
		playButton.setOnAction(e -> {
			poolGroup.getChildren().clear();
			controller.generateShape();

		});
		Button pauseButton = new Button();
		pauseButton.setGraphic(new ImageView(pauseImage));
		Button resetButton = new Button();
		resetButton.setGraphic(new ImageView(clearImage));
		resetButton.setOnAction(e -> {
			poolGroup.getChildren().clear();
		});

		toolbar = new ToolBar(playButton, pauseButton, new Separator(), resetButton);
		toolbar.setPrefHeight(48);

		vbox = new VBox();
		vbox.getChildren().addAll(toolbar);
		
		layout = new BorderPane();
		layout.setCenter(poolPane);
		layout.setRight(gridPane);
		layout.setTop(vbox);

		mainScene = new Scene(layout, 1200, 850);
		mainScene.setFill(color.BLACK);

		window.setScene(mainScene);
		window.setResizable(false);
		window.setTitle("SimpleTunes");
		window.show();

	}
	
	public void addShape(ArrayList<Shape> list) {
		Random rand = new Random();
		ArrayList<Shape> shapeList = list;
		
		for (Shape shape : shapeList) {
			shape.setLayoutX(rand.nextInt((int) (mainScene.getWidth() - gridPane.getWidth()) - 200));
			shape.setLayoutY(rand.nextInt((int) (mainScene.getHeight() - toolbar.getHeight()) - 200));
		}
		
		poolGroup.getChildren().addAll(shapeList);
	}
	 
	public static void main(String[] args) {
		launch(args);
	}

}
