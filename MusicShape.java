package simpleTunes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * 
 * Abstract class containing one JavaFX shape, an audioplayer and media object
 * and methods for manipulating these.
 * 
 * @author Tom Lanhed Sivertsson, John Håkansson
 *
 */
public abstract class MusicShape {
	private AudioClip mediaPlayer;
	private Media sound;

	protected Shape shape;

	private double orgSceneX;
	private double orgSceneY;
	private double orgTranslateX;
	private double orgTranslateY;

	private boolean placedInGrid = false;

	private int row;
	private int column;

	private Color color;

	/**
	 * 
	 * @param color Color of the shape
	 * @param sound Sound to be connected with the shape
	 */
	public MusicShape(Color color, Media sound) {
		setSound(sound);
		this.color = color;

	}

	/**
	 * 
	 * Setter for the media object
	 * 
	 * @param sound The new sound
	 */
	private void setSound(Media sound) {
		mediaPlayer = new AudioClip(sound.getSource());
		this.sound = sound;
	}

	/**
	 * 
	 * Getter for the media object
	 * 
	 * @return sound
	 */
	public Media getSounds() {
		return sound;
	}

	/**
	 * plays the sound
	 */
	public void play() {
		mediaPlayer.play();
	}

	/**
	 * stops playing the sound
	 */
	public void stop() {
		mediaPlayer.stop();
	}

	/**
	 * @return the type of musicshape
	 */
	public String toString() {
		return "MusicShape";
	}

	/**
	 * 
	 * @return the shape object
	 */
	public abstract Shape getShape();

	/**
	 * 
	 * @param x The value of the shape objects setLayoutX
	 */
	public abstract void setLayoutX(double x);

	/**
	 * 
	 * @param y The value of the shape objects setLayoutY
	 */
	public abstract void setLayoutY(double y);

	/**
	 * 
	 * Generates an eventhandler for playing when clicking a shape
	 * 
	 * @return EventHandler
	 */
	public EventHandler<MouseEvent> getMouseEvent() {
		EventHandler<MouseEvent> OnMouseClicked = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {

				play();

			}
		};

		return OnMouseClicked;
	}

	/**
	 * 
	 * Generates an eventhandler for updating x, y coordinates when pressing a shape
	 * 
	 * @return EventHandler
	 */
	public EventHandler<MouseEvent> getMouseEventPressed() {
		EventHandler<MouseEvent> onMousePressed = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {
				orgSceneX = t.getSceneX();
				orgSceneY = t.getSceneY();

				orgTranslateX = shape.getTranslateX();
				orgTranslateY = shape.getTranslateY();

			}
		};

		return onMousePressed;
	}

	/**
	 * 
	 * Generates an eventhandler for updating x, y coordinates when dragging a shape
	 * 
	 * @return EventHandler
	 */
	public EventHandler<MouseEvent> getMouseEventDragged() {
		EventHandler<MouseEvent> onMouseDragged = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {
				double offsetX = t.getSceneX() - orgSceneX;
				double offsetY = t.getSceneY() - orgSceneY;
				double newTranslateX = orgTranslateX + offsetX;
				double newTranslateY = orgTranslateY + offsetY;

				shape.setTranslateX(newTranslateX);
				shape.setTranslateY(newTranslateY);

				shape.toFront();

			}
		};

		return onMouseDragged;
	}

	/**
	 * 
	 * Sets the boolean flagging whether or not a shape has been placed in the grid
	 * 
	 * @param bool
	 */
	public void setPlaced(boolean bool) {
		placedInGrid = bool;
	}

	/**
	 * 
	 * Returns the boolean flagging whether or not a shape has been placed in the
	 * grid
	 * 
	 * @return
	 */
	public boolean getPlaced() {
		return placedInGrid;
	}

	/**
	 * 
	 * Sets which row the shape has been placed in
	 * 
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * 
	 * Sets which column the shape has been placed in
	 * 
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * 
	 * Returns the row the shape has been placed in
	 * 
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 * Returns the row the shape has been placed in
	 * 
	 * @return column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * Returns the color of the shape
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Will set all event handlers to null
	 */
	public abstract void nullifyEventHandlers();

}
