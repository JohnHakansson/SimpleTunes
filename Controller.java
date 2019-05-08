package simpleTunes;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * This class handles all the logic for the system.
 * 
 * @author John H�kansson, Tom Lanhed Sivertsson, Jesper Lindberg
 *
 */

public class Controller {
	private UI ui;
	private Thread thread;
	private Thread shapeGenerator;

	private DrumSounds drumSounds = new DrumSounds();
	private GuitarSounds guitarSounds = new GuitarSounds();
	private PianoSounds pianoSounds = new PianoSounds();

	private ArrayList<MusicShape> shapeList = new ArrayList<MusicShape>();
	private MusicShape[][] sounds = new MusicShape[4][18];

	private boolean playing = false;
	private Random rand = new Random();
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };
	private Client client;

	private boolean online = false;

	/**
	 * Gives the UI-reference a value
	 * 
	 * @param ui an instance of TestUI
	 */

	public Controller(UI ui) {
		this.ui = ui;

	}

	/**
	 * Generates random shapes(Triangle, Circle or Square) of a random color(Red,
	 * Green or Blue) until the total number of shapes in the UI is 10. Adds the
	 * shape to a shapelist and set Mouse-events for when Pressed,Dragged,Clicked
	 * and Released. Then adds the shape to the UI.
	 * 
	 * @param limit an integer the number of shapes that needs to be generated
	 */

	public synchronized void generateShape(int limit) {
		int nbrOfShapes = 0;
		MusicShape randomShape = null;

		do {
			switch (rand.nextInt(3)) {

			case 0:
				Color randomSquare = colors[rand.nextInt(3)];
				randomShape = new MusicSquare(randomSquare, pianoSounds.getPianoSound(randomSquare));
				break;

			case 1:
				Color randomCircle = colors[rand.nextInt(3)];
				randomShape = new MusicCircle(randomCircle, guitarSounds.getGuitarSound(randomCircle));
				break;

			case 2:
				Color randomTrinagle = colors[rand.nextInt(3)];
				randomShape = new MusicTriangle(50, 100, randomTrinagle, drumSounds.getDrumSounds(randomTrinagle));
				break;

			}

			nbrOfShapes++;
			shapeList.add(randomShape);
			randomShape.getShape().setOnMouseReleased(ui.getMouseEventReleased(randomShape));
			ui.setRandomLocation(randomShape);
			ui.addShape(randomShape);

		} while (nbrOfShapes < limit);

	}

	/**
	 * Starts the Thread PlaySound.
	 */

	public void startPlaying() {

		if (thread == null) {
			playing = true;
			thread = new PlaySound();
			thread.start();

		}

	}

	/**
	 * Stops the Thread PlaySound.
	 */

	public void stopPlaying() {

		if (thread != null) {
			playing = false;
			thread.interrupt();

		}

	}

	public void stopShapeGenerator() {

		if (shapeGenerator != null) {

			shapeGenerator.interrupt();

		}

	}

	public void startShapeGenerator() {

		if (shapeGenerator == null) {

			shapeGenerator = new ShapeGenerator();
			shapeGenerator.start();

		}

	}

	/**
	 * Checks the incoming array for open slots, returns true if there is space and
	 * false if the row is full.
	 * 
	 * @param column an array of Shape-objects
	 * @return boolean true of there is space in the row or false if it's full.
	 */

	private boolean checkColumnSpace(int column) {
		int counter = 0;

		for (int i = 0; i < sounds.length; i++) {
			if (sounds[i][column] != null) {
				counter++;

			}

		}

		return (counter < 4);

	}

	/**
	 * Returns an array of Shape-objects corresponding to the given row in the
	 * sounds-array.
	 * 
	 * @param column integer the row in the sounds-array that's going to be copied.
	 * @return aRow an array of Shape-objects.
	 */

	private MusicShape[] getColumn(int column) {
		MusicShape[] aRow = new MusicShape[4];

		for (int i = 0; i < aRow.length; i++) {
			if (sounds[i][column] != null) {
				aRow[i] = sounds[i][column];

			}

		}

		return aRow;

	}

	/**
	 * Adds the incoming Shape-object on the given 'row' of the sounds-array, if
	 * there is no space in the array "Row is full" is written to the console.
	 * 
	 * @param shape a Shape-object to be added to the sounds-array.
	 * @param row   integer the row where it shape is going to be added
	 */

	public void addShapestoArray(MusicShape shape, int row, int column) {
		boolean shapePlaced = false;

		shape.setColumn(column);

		if (sounds[row][column] == null) {
			sounds[row][column] = shape;
			shape.setRow(row);
			shape.setPlaced(true);
			ui.setGridPlacement(shape, row, column);

			if (online) {

				MusicShapeMessage msm = new MusicShapeMessage(shape.toString(),
						NamedColors.getColorString(shape.getColor()), row, column);

				client.sendShape(msm);

				System.out.println("Sending shape to client");

			}

			System.out.println("Added to row: " + row + " Added to column: " + column);

		} else {

			for (int i = 0; !shapePlaced; i++) {
				if (checkColumnSpace(shape.getColumn())) {
					if (sounds[i][column] == null) {
						sounds[i][column] = shape;
						shapePlaced = true;
						shape.setPlaced(true);
						shape.setRow(i);
						ui.setGridPlacement(shape, i, column);
						System.out.println("Added to row: " + row + " Added to column: " + column);

						if (online) {

							MusicShapeMessage msm = new MusicShapeMessage(shape.toString(),
									NamedColors.getColorString(shape.getColor()), i, column);

							client.sendShape(msm);

							System.out.println("Sending shape to client");

						}

					}

				} else {
					ui.setRandomLocation(shape);
					return;

				}

			}

		}

	}

	public void removeSound(int row, int column) {
		sounds[row][column] = null;
	}

	/**
	 * Refreshes and removes all shapes from the FormPool in the UI.
	 * 
	 * @param group a Group-object
	 */

	public void refreshShapesFromPool(Group group) {

		stopShapeGenerator();

		for (MusicShape ms : shapeList) {

			if (!ms.getPlaced()) {
				group.getChildren().remove(ms.getShape());

			}

		}

	}

	/**
	 * Removes all shapes from the FormPool and Grid in the UI.
	 * 
	 * @param group a Group-object
	 */

	public void removeShapesFromPool(Group group) {

		for (MusicShape ms : shapeList) {
			group.getChildren().remove(ms.getShape());

		}

		sounds = new MusicShape[4][18];

		shapeList.clear();

		startShapeGenerator();

	}

	public void sendUsername(String userName) {

		client = new Client("localhost", 5555, userName, this);
		client.sendUsername();

	}

	public void disconnect() {

		client.disconnect();

	}

	public void update(Object obj) {

		if (obj instanceof InitialStateMessage) {

			online = true;

			InitialStateMessage ism = (InitialStateMessage) obj;

			for (String x : ism.getConnectedUsers()) {

				ui.updateUserList(x);

			}

			ui.closeLogin();

			System.out.println("iniialStateMessage mottagits");

		}

		if (obj instanceof MusicShapeMessage) {

			MusicShapeMessage msm = (MusicShapeMessage) obj;

			System.out.println("Color: " + msm.getColor());
			System.out.println("Shape: " + msm.getShape());
			System.out.println("Row: " + msm.getRow());
			System.out.println("Column: " + msm.getColumn());

			if (msm.getShape().equals("square")) {

				Color color = NamedColors.get(msm.getColor());

				MusicSquare ms = new MusicSquare(color, pianoSounds.getPianoSound(color));

				ui.setShapeFromOnline(ms, msm.getRow(), msm.getColumn());

				sounds[msm.getRow()][msm.getColumn()] = ms;

			}

			if (msm.getShape().equals("triangle")) {

				Color color = NamedColors.get(msm.getColor());

				MusicTriangle ms = new MusicTriangle(50, 100, color, drumSounds.getDrumSounds(color));

				ui.setShapeFromOnline(ms, msm.getRow(), msm.getColumn());

				sounds[msm.getRow()][msm.getColumn()] = ms;

			}

			if (msm.getShape().equals("circle")) {

				Color color = NamedColors.get(msm.getColor());

				MusicCircle ms = new MusicCircle(color, guitarSounds.getGuitarSound(color));

				ui.setShapeFromOnline(ms, msm.getRow(), msm.getColumn());

				sounds[msm.getRow()][msm.getColumn()] = ms;

			}

//			ui.setGridPlacement(musicShape, musicShape.getRow(), musicShape.getColumn());
//
//			sounds[musicShape.getRow()][musicShape.getColumn()] = musicShape;

			System.out.println("MusicShape har kommit till controller");

		}

		if (obj instanceof ConnectRequestMessage) {

			ConnectRequestMessage crm = (ConnectRequestMessage) obj;

			ui.openConnectMessage(crm);

		}

		if (obj instanceof UserConnectMessage) {

			System.out.println("Ett user connect message har mottagits i controller !!!");

			UserConnectMessage ucm = (UserConnectMessage) obj;

			ui.updateUserList(ucm.getUsername());

		}

		if (obj instanceof String) {

			String str = (String) obj;

			if (str.equals("Username already exists, please choose another username ")) {

				ui.loginNotOK(str);

			}

		}

		if (obj instanceof UserDisconnectMessage) {

			UserDisconnectMessage udm = (UserDisconnectMessage) obj;

			ui.removeFromUserList(udm.getUsername());

		}

	}

	public void update(MusicShape musicShape) {

		ui.setGridPlacement(musicShape, musicShape.getRow(), musicShape.getColumn());

		sounds[musicShape.getRow()][musicShape.getColumn()] = musicShape;

		System.out.println("MusicShape har kommit till controller");

	}

	public void connectToUser(String username) {

		ConnectToUserMessage message = new ConnectToUserMessage(ui.getUsername(), username);

		client.sendObject(message);

	}

	public void sendResponse(ConnectRequestMessage crm) {

		client.sendObject(crm);

	}

	/**
	 * An inner class that extends Thread and uses the sounds-array to play the
	 * soundclip connected to the shape. Then sleeps for 0.5 seconds before playing
	 * the next row of soundclips
	 * 
	 * @author John H�kansson
	 *
	 */

	private class PlaySound extends Thread {

		public void run() {

			try {

				int columns = 0;

				while (playing) {

					System.out.println("Kolumn : " + columns);

					for (int i = 0; i < sounds.length; i++) {

						if (sounds[i][columns] != null) {

							sounds[i][columns].play();

						}

					}

					Thread.sleep(500);

					columns++;

					if (columns == 18) {
						columns = 0;

					}

				}

			} catch (InterruptedException e) {
				thread = null;

			}

		}

	}

	private class ShapeGenerator extends Thread {

		public void run() {

			try {

				for (int i = 0; i < 10; i++) {

					Thread.sleep(1000);

					Platform.runLater(new Runnable() {

						public void run() {

							generateShape(1);

						}
					});

				}

				this.interrupt();

			} catch (InterruptedException e) {
				shapeGenerator = null;
			}

		}
	}

}