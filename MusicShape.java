package simpleTunes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

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

	public MusicShape(Color color, Media sound) {
		setSound(sound);
		this.color = color;
		System.out.println(color.getBlue());

	}

	private void setSound(Media sound) {
		mediaPlayer = new AudioClip(sound.getSource());
		this.sound = sound;
	}

	public Media getSounds() {
		return sound;
	}

	public void play() {
		mediaPlayer.play();
	}

	public void stop() {
		mediaPlayer.stop();
	}

	public String toString() {
		return "MusicShape";
	}

	public abstract Shape getShape();

	public abstract void setLayoutX(double x);

	public abstract void setLayoutY(double y);

	public EventHandler<MouseEvent> getMouseEvent() {
		EventHandler<MouseEvent> OnMouseClicked = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {

				play();

			}
		};

		return OnMouseClicked;
	}

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

	public void setPlaced(boolean bool) {
		placedInGrid = bool;
	}

	public boolean getPlaced() {
		return placedInGrid;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Color getColor() {
		return color;
	}

	public abstract void nullifyEventHandlers();

}
