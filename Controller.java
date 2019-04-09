package simpleTunes;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Controller {
	private DrumSounds drumSounds = new DrumSounds();
	private GuitarSounds guitarSounds = new GuitarSounds();
	private PianoSounds pianoSounds = new PianoSounds();
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private Shape[][] sounds = new Shape[8][4];
	private boolean playing = false;
	private Thread thread;
	private TestUI ui;
	private Random rand = new Random();
	private Color[] colors = { Color.RED, Color.BLUE, Color.GREEN };

	public Controller(TestUI ui) {
		this.ui = ui;
	}
	
	public Controller() {
		//For main-method testing
	}

	public void clearList() {

	}

	public void generateShape() {
		shapeList = new ArrayList<Shape>();

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
				randomShape = new MusicTriangle(56, 100, randomTrinagle, drumSounds.getDrumSounds(randomTrinagle));
				break;
			}

			nbrOfShapes++;
			shapeList.add(randomShape);
			randomShape.setOnMouseClicked(ui.getMouseEvent(randomShape));
		} while (nbrOfShapes < 10);

		ui.addShape(shapeList);
	}
	
	public void startPlaying() {
		if(thread == null) {
			playing = true;
			thread = new PlaySound();
			thread.start();
		}
	}
	
	public void stop() {
		if(thread != null) {
			playing = false;
			thread = null;
		}
	}
	
	public boolean checkRowSpace(Shape[] row) {
		int counter = 0;
		
		
		for(int i = 0; i < row.length; i++) {
			if(row[i] != null) {
				counter++;
			}
		}
		System.out.println();
		return (counter < 4);
	}
	
	public Shape[] getRow(int row) {
		Shape[] aRow = new Shape[4];
		
		for(int i = 0; i < aRow.length; i++) {
			if(sounds[row][i] != null) {
				aRow[i] = sounds[row][i];
			}
		}
		System.out.println("Getting row");
		return aRow;
	}
	
	public void addShapestoArray(Shape shape, int row) {
		boolean foundEmptySpace = false;
		
		for(int i = 0; i < sounds[row].length || foundEmptySpace; i++) {
			if(checkRowSpace(getRow(row))) {
				sounds[row][i] = shape;
				foundEmptySpace = true;
				System.out.println("Added to array");
			}
		}
	}
	
	public Shape[][] getSounds() {
		return sounds;
	}

	private class PlaySound extends Thread {

		public void run() {

			while (playing) {
				
				for(int i = 0; i < sounds.length; i++) {
					for(int j = 0; i < sounds[i].length; j++) {
						if(sounds[i][j] != null) {
							if(sounds[i][j] instanceof MusicTriangle) {
								((MusicTriangle)sounds[i][j]).play();
							}
							else if(sounds[i][j] instanceof MusicSquare) {
								((MusicSquare)sounds[i][j]).play();
							}
							else if(sounds[i][j] instanceof MusicCircle) {
								((MusicCircle)sounds[i][j]).play();
							}
						}
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	/*
	 * Only for testing
	 */
	
	public static void main(String[] args) {
		
		GuitarSounds gs = new GuitarSounds();
		Controller cont = new Controller();
		Rectangle rect = new Rectangle(100,100);
		cont.addShapestoArray(rect, 0);
		Shape[][] array = cont.getSounds();
		System.out.println(array[0][0].toString());
	}
}