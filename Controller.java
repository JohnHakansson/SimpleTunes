package simpleTunes;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Group;

/**
 * This class handles all the logic for the system.
 * 
 * @author John Hï¿½kansson, Tom Lanhed Sivertsson, Jesper Lindberg, Roland
 *         Askelöf, Matilda Frimodig
 *
 */

public class Controller {
	private UI ui;
	private Thread thread;
	private Thread shapeGenerator;

	private SoundBass soundBass = new SoundBass();
	private SoundSynthNotes soundSynthNotes = new SoundSynthNotes();
	private SoundArp soundArp = new SoundArp();
	private SoundSynthChords soundSynthChords = new SoundSynthChords();
	private SoundDrums soundDrums = new SoundDrums();

	private ArrayList<MusicShape> shapeList = new ArrayList<MusicShape>();
	private MusicShape[][] sounds = new MusicShape[4][16];

	private boolean playing = false;
	private Random rand = new Random();
	private String[] colors = { "Blue", "Orange", "Red", "Green", "Purple" };
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
		String color = "";

		do {
			switch (rand.nextInt(5)) {

			case 0:
				color = colors[0] + (rand.nextInt(5) + 1);
				randomShape = new MusicCircle(color, soundBass.getBassSound(color));
				break;

			case 1:
				color = colors[1] + (rand.nextInt(5) + 1);
				randomShape = new MusicTriangle(color, soundSynthNotes.getSynthNotes(color));
				break;
				
			case 2:
				color = colors[2] + (rand.nextInt(5) + 1);
				randomShape = new MusicDiamond(color, soundArp.getArpSound(color));
				break;
				
			case 3:
				color = colors[3] + (rand.nextInt(5) + 1);
				randomShape = new MusicRightTriangle(color, soundSynthChords.getSynthChordSound(color));
				break;
				
			case 4:
				color = colors[4] + (rand.nextInt(5) + 1);
				randomShape = new MusicPentagon(color, soundDrums.getDrumSounds(color));
				break;

			}

			nbrOfShapes++;
			randomShape.getShape().setId("randomShapes");
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
			thread.setPriority(Thread.MAX_PRIORITY);
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

	/**
	 * Stops the shape generator
	 */
	public void stopShapeGenerator() {

		if (shapeGenerator != null) {

			shapeGenerator.interrupt();
			shapeGenerator = null;

		}

	}

	/**
	 * Starts the shape generator
	 */
	public void startShapeGenerator() {

		if (shapeGenerator == null) {

			shapeGenerator = new ShapeGenerator();
			shapeGenerator.start();

		}

	}

	/**
	 * Returns a boolean true if the user is online otherwise false
	 * 
	 * @return a boolean if the user is online
	 */

	public boolean isOnline() {
		return online;
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
			ui.setGridPlacement(shape, row, column);
			shape.setPlaced(true);

			if (online) {

				MusicShapeMessage msm = new MusicShapeMessage(shape.toString(), shape.getColorName(), row, column);

				client.sendObject(msm);

			}

		} else {

			for (int i = 0; !shapePlaced; i++) {
				if (checkColumnSpace(shape.getColumn())) {
					if (sounds[i][column] == null) {
						sounds[i][column] = shape;
						shapePlaced = true;
						shape.setRow(i);
						ui.setGridPlacement(shape, i, column);
						shape.setPlaced(true);

						if (online) {

							MusicShapeMessage msm = new MusicShapeMessage(shape.toString(), shape.getColorName(), i,
									column);

							client.sendObject(msm);

						}

					}

				} else {
					ui.setRandomLocation(shape);
					return;

				}

			}

		}

	}

	/**
	 * 
	 * Removes a musicshape from the sound array
	 * 
	 * @param row    which row the shape is located in
	 * @param column which column the shape is located in
	 */
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

		if (online) {

			RemoveShapeMessage rsm = new RemoveShapeMessage(true);

			client.sendObject(rsm);
		}

		sounds = new MusicShape[4][16];

		shapeList.clear();

		startShapeGenerator();

	}

	/**
	 * Removes all the shapes from the grid and empties the sounds-array
	 */

	public void removeShapesFromGrid() {

		Platform.runLater(new Runnable() {
			public void run() {
				Group group = ui.getPoolGroup();

				for (int i = 0; i < sounds.length; i++) {
					for (int j = 0; j < sounds[i].length; j++) {
						if (sounds[i][j] != null) {

							group.getChildren().remove(sounds[i][j].getShape());
						}

					}

				}
				sounds = new MusicShape[4][16];
			}
		});
	}

	/**
	 * Creates a Client-object with the given ip and port. Sends the new user to the
	 * Server
	 * 
	 * @param userName the username of the connecting user
	 * @param ip       the ip of the Server
	 */
	public void sendUsername(String userName, String ip) {

		client = new Client(ip, 5555, userName, this);
		client.sendUsername();

	}

	/**
	 * disconnects the client from from the server
	 */
	public void disconnect() {

		client.disconnect();

	}

	/**
	 * 
	 * Handles incoming messages from the server
	 * 
	 * @param obj Object from the server via a client
	 */
	public void update(Object obj) {

		/**
		 * when the user connects to the server the user receives a InitialStateMessage
		 * containing already connected users
		 */

		if (obj instanceof InitialStateMessage) {

			online = true;

			InitialStateMessage ism = (InitialStateMessage) obj;

			for (String x : ism.getConnectedUsers()) {

				ui.updateUserList(x);

			}

			ui.closeLogin();

		}

		/**
		 * When a shape is between users it's broken down to be rebuilt in this section
		 * and is added to the UI
		 */

		if (obj instanceof MusicShapeMessage) {

			MusicShapeMessage msm = (MusicShapeMessage) obj;

			if (msm.getShape().equals("diamond")) {

				MusicDiamond md = new MusicDiamond(msm.getColor(), soundArp.getArpSound(msm.getColor()));

				md.setRow(msm.getRow());
				md.setColumn(msm.getColumn());

				ui.setShapeFromOnline(md, msm.getRow(), msm.getColumn());

				shapeList.add(md);

				sounds[msm.getRow()][msm.getColumn()] = md;

			}

			else if (msm.getShape().equals("triangle")) {

				MusicTriangle mt = new MusicTriangle(msm.getColor(), soundSynthNotes.getSynthNotes(msm.getColor()));

				mt.setRow(msm.getRow());
				mt.setColumn(msm.getColumn());

				ui.setShapeFromOnline(mt, msm.getRow(), msm.getColumn());

				shapeList.add(mt);

				sounds[msm.getRow()][msm.getColumn()] = mt;

			}

			else if (msm.getShape().equals("circle")) {

				MusicCircle mc = new MusicCircle(msm.getColor(), soundBass.getBassSound(msm.getColor()));

				mc.setRow(msm.getRow());
				mc.setColumn(msm.getColumn());

				ui.setShapeFromOnline(mc, msm.getRow(), msm.getColumn());

				shapeList.add(mc);

				sounds[msm.getRow()][msm.getColumn()] = mc;

			}

			else if (msm.getShape().equals("pentagon")) {

				MusicPentagon mp = new MusicPentagon(msm.getColor(), soundDrums.getDrumSounds(msm.getColor()));

				mp.setRow(msm.getRow());
				mp.setColumn(msm.getColumn());

				ui.setShapeFromOnline(mp, msm.getRow(), msm.getColumn());

				shapeList.add(mp);

				sounds[msm.getRow()][msm.getColumn()] = mp;
			}

			else if (msm.getShape().equals("righttriangle")) {

				MusicRightTriangle mrt = new MusicRightTriangle(msm.getColor(),
						soundSynthChords.getSynthChordSound(msm.getColor()));

				mrt.setRow(msm.getRow());
				mrt.setColumn(msm.getColumn());

				ui.setShapeFromOnline(mrt, msm.getRow(), msm.getColumn());

				shapeList.add(mrt);

				sounds[msm.getRow()][msm.getColumn()] = mrt;
			}

		}

		/**
		 * When a user receives a ConnectRequestMessage and it open a Window in the UI
		 * asking if the a connection is wanted
		 */

		if (obj instanceof ConnectRequestMessage) {

			ConnectRequestMessage crm = (ConnectRequestMessage) obj;

			if (crm.getIsResponse()) {

				ui.openConnectMessage(crm);

			} else {

				if (crm.getConnectRequest()) {

					ui.updateMenueConnected(crm.getReceiverUsername());
					removeShapesFromGrid();

				} else {

					ui.updateMenueDefault();
				}
			}

		}

		/**
		 * When a new user connects to the server the username is sent to all connected
		 * users
		 */

		if (obj instanceof UserConnectMessage) {

			UserConnectMessage ucm = (UserConnectMessage) obj;

			ui.updateUserList(ucm.getUsername());

		}

		/**
		 * if the name written is already taken this message is return to the UI
		 */

		if (obj instanceof String) {

			String str = (String) obj;

			if (str.equals("Username already exists, please choose another username ")) {

				ui.loginNotOK(str);

			}

		}

		/**
		 * When a user disconnects from the server a message is sent to all connected
		 * users.
		 */

		if (obj instanceof UserDisconnectMessage) {

			UserDisconnectMessage udm = (UserDisconnectMessage) obj;

			ui.removeFromUserList(udm.getUsername());

		}

		/**
		 * When a user removes a shape from the grid when connectede to another user
		 */

		if (obj instanceof RemoveShapeMessage) {

			RemoveShapeMessage rsm = (RemoveShapeMessage) obj;

			if (rsm.isRemoveAll()) {

				removeShapesFromGrid();

			} else {

				ui.removeShape(sounds[rsm.getRow()][rsm.getColumn()]);

				removeSound(rsm.getRow(), rsm.getColumn());

			}

		}

	}

	/**
	 * 
	 * Sends a new ConnectToUserMessage to the client
	 * 
	 * Called when one user wants to connect to another
	 * 
	 * @param username The receiving username
	 */
	public void connectToUser(String username) {

		ConnectToUserMessage message = new ConnectToUserMessage(ui.getUsername(), username);

		client.sendObject(message);

	}

	/**
	 * Sends a message to the server that a shape has been removed
	 * 
	 * @param row    the row the shape is in
	 * @param column the column the shape is in
	 */

	public void sendRemoveShape(int row, int column) {

		RemoveShapeMessage removeShapeMessage = new RemoveShapeMessage(row, column);

		client.sendObject(removeShapeMessage);

	}

	/**
	 * 
	 * Called when a user has either accepted or declined a connection request from
	 * another user
	 * 
	 * @param crm ConnectRequestMessage containing either a true or false response
	 *            boolean
	 */
	public void sendResponse(ConnectRequestMessage crm) {

		client.sendObject(crm);

	}

	/**
	 * An inner class that extends Thread and uses the sounds-array to play the
	 * soundclip connected to the shape. Then sleeps for 0.5 seconds before playing
	 * the next row of soundclips
	 * 
	 * @author John Hï¿½kansson
	 *
	 */

	private class PlaySound extends Thread {

		public void run() {

			try {

				int columns = 0;

				while (playing) {

					for (int i = 0; i < sounds.length; i++) {

						if (sounds[i][columns] != null) {

							sounds[i][columns].play();

						}

					}

					Thread.sleep(500);

					columns++;

					if (columns == 16) {
						columns = 0;

					}

				}

			} catch (InterruptedException e) {
				thread = null;

			}

		}

	}

	/**
	 * 
	 * Thread which handles the automatic generation of shapes
	 * 
	 * @author Tom Lanhed Sivertsson
	 *
	 */
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

				stopShapeGenerator();

			} catch (InterruptedException e) { }

		}
	}

}