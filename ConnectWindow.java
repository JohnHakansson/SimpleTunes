package simpleTunes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectWindow {
	private Stage stage;
	private boolean answer = false;

	private String message;

	public ConnectWindow(String message) {
		this.message = message;

	}

	public boolean display() {

		stage = new Stage();
		stage.setTitle("JavaFX Welcome");
		stage.initModality(Modality.APPLICATION_MODAL);
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text(message);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Button btnAccept = new Button("Accept");
		Button btnDecline = new Button("Decline");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btnAccept);
		hbBtn.getChildren().add(btnDecline);
		grid.add(hbBtn, 1, 4);

		btnAccept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				answer = true;

				Platform.runLater(new Runnable() {

					public void run() {
						stage.close();
					}
				});

			}
		});

		btnDecline.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				answer = false;

				Platform.runLater(new Runnable() {

					public void run() {
						stage.close();
					}
				});

			}
		});

		Scene scene = new Scene(grid, 500, 275);
		stage.setScene(scene);
		stage.showAndWait();

		return answer;

	}

	public void closeStage() {

		Platform.runLater(new Runnable() {

			public void run() {
				stage.close();
			}
		});

	}

}
