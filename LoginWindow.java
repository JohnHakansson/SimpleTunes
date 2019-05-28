package simpleTunes;

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

/**
 * 
 * The class handles the showing of the login window. Will be displayed when a
 * user presses the go-online button
 * 
 * @author Tom Lanhed Sivertsson, Matilda Frimodig
 *
 */
public class LoginWindow {
	private Controller controller;

	private Text actiontarget = new Text();

	private Stage stage;

	private TextField userTextField;

	private TextField ipAddress;

	/**
	 * 
	 * @param controller Controller to be called
	 */
	public LoginWindow(Controller controller) {
		this.controller = controller;

	}

	/**
	 * Displays the window
	 */
	public void display() {

		stage = new Stage();

		stage.setTitle("simpleTunes");
		stage.initModality(Modality.APPLICATION_MODAL);
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Login");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 4, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label lblIpAddress = new Label("Ip-Address");
		grid.add(lblIpAddress, 0, 2);

		ipAddress = new TextField();
		grid.add(ipAddress, 1, 2);

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
				controller.sendUsername(userTextField.getText(), ipAddress.getText());

			}
		});

		Scene scene = new Scene(grid, 500, 275);
		stage.setScene(scene);
		stage.showAndWait();

	}

	/**
	 * 
	 * Is called when the username is not accepted by the server
	 * 
	 * @param info Information to be displayed
	 */
	public void userNameNotOK(String info) {

		actiontarget.setFill(Color.RED);
		actiontarget.setText(info);

	}

	/**
	 * Closes the window
	 */
	public void closeStage() {

		Platform.runLater(new Runnable() {

			public void run() {
				stage.close();
			}
		});

	}

	/**
	 * 
	 * Gathers the String from the text input field
	 * 
	 * @return the username
	 */
	public String getUserName() {

		return userTextField.getText();

	}




}