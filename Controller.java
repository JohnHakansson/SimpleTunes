package simpleTunes;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public class Controller {
	private TestUI ui;
	private Thread thread;

	private DrumSounds drumSounds = new DrumSounds();
	private GuitarSounds guitarSounds = new GuitarSounds();
	private PianoSounds pianoSounds = new PianoSounds();

	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private Shape[][] sounds = new Shape[8][4];

	private boolean playing = false;
	private Random rand = new Random();
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };

	public Controller(TestUI ui) {
		this.ui = ui;

	}

	public void generateShape(int limit) {
		int nbrOfShapes = 0;
		Shape randomShape = null;

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
			randomShape.setOnMousePressed(ui.getMouseEventPressed(randomShape));
			randomShape.setOnMouseDragged(ui.getMouseEventDragged(randomShape));
			randomShape.setOnMouseClicked(ui.getMouseEvent(randomShape));
			randomShape.setOnMouseReleased(ui.getMouseEventReleased(randomShape));

			ui.addShape(randomShape);
			
		} while (nbrOfShapes < limit);

	}

	public void startPlaying() {
		
		if (thread == null) {
			playing = true;
			thread = new PlaySound();
			thread.start();
			
		}
		
	}

	public void stop() {
		
		if (thread != null) {
			playing = false;
			thread.interrupt();
			
		}
		
	}

	private boolean checkRowSpace(Shape[] row) {
		int counter = 0;

		for (int i = 0; i < row.length; i++) {
			if (row[i] != null) {
				counter++;
				
			}
			
		}
		
		return (counter < 4);
		
	}

	private Shape[] getRow(int row) {
		Shape[] aRow = new Shape[4];

		for (int i = 0; i < aRow.length; i++) {
			if (sounds[row][i] != null) {
				aRow[i] = sounds[row][i];
				
			}
			
		}
		
		return aRow;
		
	}

	public void addShapestoArray(Shape shape, int row) {
		boolean shapePlaced = false;

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

	public void removeShapesFromGrid(Group group) {

		for (int i = 0; i < sounds.length; i++) {
			for (int j = 0; j < sounds[i].length; j++) {
				group.getChildren().remove(sounds[i][j]);
				sounds[i][j] = null;
				
			}
			
		}

	}

	public void removeShapesFromPool(Group group) {

		for (Shape ms : shapeList) {
			group.getChildren().remove(ms);
			
		}

	}

	private class PlaySound extends Thread {

		public void run() {
			
			try {
				
				while (playing) {

					for (int i = 0; i < sounds.length; i++) {
						for (int j = 0; j < sounds[i].length; j++) {

							if (sounds[i][j] != null) {

								if (sounds[i][j] instanceof MusicTriangle) {
									((MusicTriangle) sounds[i][j]).play();

								} else if (sounds[i][j] instanceof MusicSquare) {
									((MusicSquare) sounds[i][j]).play();

								} else if (sounds[i][j] instanceof MusicCircle) {
									((MusicCircle) sounds[i][j]).play();
									
								}

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