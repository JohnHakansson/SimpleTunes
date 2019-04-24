package simpleTunes;

import java.util.ArrayList;
import java.util.Random;
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

	private DrumSounds drumSounds = new DrumSounds();
	private GuitarSounds guitarSounds = new GuitarSounds();
	private PianoSounds pianoSounds = new PianoSounds();

	private ArrayList<MusicShape> shapeList = new ArrayList<MusicShape>();
	private MusicShape[][] sounds = new MusicShape[8][4];

	private boolean playing = false;
	private Random rand = new Random();
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };

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

	public void generateShape(int limit) {
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

	/**
	 * Checks the incoming array for open slots, returns true if there is space and
	 * false if the row is full.
	 * 
	 * @param row an array of Shape-objects
	 * @return boolean true of there is space in the row or false if it's full.
	 */

	private boolean checkRowSpace(MusicShape[] row) {
		int counter = 0;

		for (int i = 0; i < row.length; i++) {
			if (row[i] != null) {
				counter++;

			}

		}

		return (counter < 4);

	}

	/**
	 * Returns an array of Shape-objects corresponding to the given row in the
	 * sounds-array.
	 * 
	 * @param row integer the row in the sounds-array that's going to be copied.
	 * @return aRow an array of Shape-objects.
	 */

	private MusicShape[] getRow(int row) {
		MusicShape[] aRow = new MusicShape[4];

		for (int i = 0; i < aRow.length; i++) {
			if (sounds[row][i] != null) {
				aRow[i] = sounds[row][i];

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

		if (sounds[row][column] == null) {
			sounds[row][column] = shape;
			ui.removeShape(shape, row, column);

		} else {
			
			for (int i = 0; !shapePlaced; i++) {
				if (checkRowSpace(getRow(row))) {
					if (sounds[row][i] == null) {
						sounds[row][i] = shape;
						shapePlaced = true;
						ui.removeShape(shape, row, i);

					}

				} else {
					System.out.println("Row is full");
					return;

				}

			}
		}

	}


	/**
	 * Removes all shapes from the FormPool in the UI.
	 * 
	 * @param group a Group-object
	 */

	public void removeShapesFromPool(Group group) {

		for (MusicShape ms : shapeList) {
			group.getChildren().remove(ms.getShape());
			
			sounds = new MusicShape[8][4];
		}

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

				while (playing) {

					for (int i = 0; i < sounds.length; i++) {
						for (int j = 0; j < sounds[i].length; j++) {

							if (sounds[i][j] != null) {

								sounds[i][j].play();

							}

						}

						Thread.sleep(500);
					}

				}

			} catch (InterruptedException e) {
				thread = null;

			}

		}

	}

}