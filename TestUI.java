package simpleTunes;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class TestUI extends Application {
	private Stage window;
	private BorderPane layout;
	private Color color;
	private Controller controller;
	private Rectangle square1x1 = new Rectangle(800.0f, 30.0f, 100, 100);
	private Rectangle square1x2 = new Rectangle(900.0f, 30.0f, 100, 100);
	private Rectangle square1x3 = new Rectangle(1000.0f, 30.0f, 100, 100);
	private Rectangle square1x4 = new Rectangle(1100.0f, 30.0f, 100, 100);
	
	private Rectangle square2x1 = new Rectangle(800.0f, 130.0f, 100, 100);
	private Rectangle square2x2 = new Rectangle(900.0f, 130.0f, 100, 100);
	private Rectangle square2x3 = new Rectangle(1000.0f, 130.0f, 100, 100);
	private Rectangle square2x4 = new Rectangle(1100.0f, 130.0f, 100, 100);
	
	private Rectangle square3x1 = new Rectangle(800.0f, 230.0f, 100, 100);
	private Rectangle square3x2 = new Rectangle(900.0f, 230.0f, 100, 100);
	private Rectangle square3x3 = new Rectangle(1000.0f, 230.0f, 100, 100);
	private Rectangle square3x4 = new Rectangle(1100.0f, 230.0f, 100, 100);
	
	private Rectangle square4x1 = new Rectangle(800.0f, 330.0f, 100, 100);
	private Rectangle square4x2 = new Rectangle(900.0f, 330.0f, 100, 100);
	private Rectangle square4x3 = new Rectangle(1000.0f, 330.0f, 100, 100);
	private Rectangle square4x4 = new Rectangle(1100.0f, 330.0f, 100, 100);
	
	private Rectangle square5x1 = new Rectangle(800.0f, 430.0f, 100, 100);
	private Rectangle square5x2 = new Rectangle(900.0f, 430.0f, 100, 100);
	private Rectangle square5x3 = new Rectangle(1000.0f, 430.0f, 100, 100);
	private Rectangle square5x4 = new Rectangle(1100.0f, 430.0f, 100, 100);
	
	private Rectangle square6x1 = new Rectangle(800.0f, 530.0f, 100, 100);
	private Rectangle square6x2 = new Rectangle(900.0f, 530.0f, 100, 100);
	private Rectangle square6x3 = new Rectangle(1000.0f, 530.0f, 100, 100);
	private Rectangle square6x4 = new Rectangle(1100.0f, 530.0f, 100, 100);
	
	private Rectangle square7x1 = new Rectangle(800.0f, 630.0f, 100, 100);
	private Rectangle square7x2 = new Rectangle(900.0f, 630.0f, 100, 100);
	private Rectangle square7x3 = new Rectangle(1000.0f, 630.0f, 100, 100);
	private Rectangle square7x4 = new Rectangle(1100.0f, 630.0f, 100, 100);
	
	private Rectangle square8x1 = new Rectangle(800.0f, 730.0f, 100, 100);
	private Rectangle square8x2 = new Rectangle(900.0f, 730.0f, 100, 100);
	private Rectangle square8x3 = new Rectangle(1000.0f, 730.0f, 100, 100);
	private Rectangle square8x4 = new Rectangle(1100.0f, 730.0f, 100, 100);
	
	
	private Group poolGroup = new Group();
	private Pane poolPane = new Pane(poolGroup);
	

	public void start(Stage primaryStage) throws Exception {
		
		Controller controller = new Controller(this);
		
		window = primaryStage;
		
//		Pane poolPane = new Pane();
		poolPane.setPrefSize(600, 800);
		poolPane.setStyle("-fx-background-color: Black");
		
		Pane gridPane = new Pane();
		gridPane.setPrefSize(400, 800);
		gridPane.setStyle("-fx-background-color: White");
		
		Group shapeGroup = new Group();
		Pane menuPane = new Pane(shapeGroup);
		//Row #1
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
		
		menuPane.setPrefSize(1200, 30);
		menuPane.setStyle("-fx-background-color: Grey");
		
		Button playButton = new Button("Play");
		playButton.setOnAction(e -> {
			controller.addShape();
		});
		playButton.setLayoutX(600);
		playButton.setLayoutY(0);
		Button stopButton = new Button("Stop");
		stopButton.setLayoutX(650);
		stopButton.setLayoutY(0);
		Button resetButton = new Button("Reset");
		resetButton.setLayoutX(1135);
		resetButton.setLayoutY(0);
		
		menuPane.getChildren().add(playButton);
		menuPane.getChildren().add(stopButton);
		menuPane.getChildren().add(resetButton);
		
//		square1x1.setStroke(color.PINK);
//		square1x1.setLayoutX(800);
//		square1x1.setLayoutY(30);
//		square1x1.setFill(color.GREEN);
//		gridPane.getChildren().add(square1x1);
		
//		Pane gridPane = new Pane(shapeGroup);
//		gridPane.setPrefSize(400, 800);
//		gridPane.setStyle("-fx-background-color: White");
		
		layout = new BorderPane();
		layout.setCenter(poolPane);
		layout.setRight(gridPane);
		layout.setTop(menuPane);
		
		//layout.getChildren().add(square1x1);

		Scene mainScene = new Scene(layout, 1200, 830);
		mainScene.setFill(color.BLACK);
				
		window.setScene(mainScene);
		window.setResizable(false);
		window.show();
		
	}
	
	public void addShape(Shape shape) {
		Random rand = new Random();
		
		shape.setLayoutX(rand.nextInt(400));
		shape.setLayoutY(rand.nextInt(300));
		poolGroup.getChildren().add(shape);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
