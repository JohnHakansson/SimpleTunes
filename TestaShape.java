package simpleTunes;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TestaShape extends Application{

	public void start(Stage primaryStage) throws Exception {
		Group group = new Group();
		
		MusicDiamond diamond = new MusicDiamond();
		Rectangle rect = new Rectangle(100, 100);
		rect.setFill(Color.BLACK);
		
		String res = "Orange" + (new Random().nextInt(5)+1);
		
		System.out.println(res);
		
		diamond.getShape().setFill(NewNamnedColours.get(res));
		
		group.getChildren().add(diamond.getShape());
		
		Scene scene = new Scene(group, 400, 400);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
