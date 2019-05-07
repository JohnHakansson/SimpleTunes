package simpleTunes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginWindow {
	private Controller controller;

	private Text actiontarget = new Text();

	private Stage stage;
	
	private TextField userTextField;

	public LoginWindow(Controller controller) {
		this.controller = controller;

	}

	public void display() {

		stage = new Stage();

		stage.setTitle("JavaFX Welcome");
		stage.initModality(Modality.APPLICATION_MODAL);
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Inloggning");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		grid.add(actiontarget, 1, 6);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				actiontarget.setFill(Color.DARKGRAY);
				actiontarget.setText("Signing in...");
				controller.sendUsername(userTextField.getText());

			}
		});

		Scene scene = new Scene(grid, 500, 275);
		stage.setScene(scene);
		stage.showAndWait();

	}

	public void userNameNotOK(String info) {

		actiontarget.setFill(Color.RED);
		actiontarget.setText(info);

	}

	public void closeStage() {

		Platform.runLater(new Runnable() {

			public void run() {
				stage.close();
			}
		});

	}
	
	public String getUserName() {
		
		return userTextField.getText();
		
	}
	
}	