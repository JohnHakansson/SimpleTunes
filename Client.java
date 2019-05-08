package simpleTunes;

import java.io.*;
import java.net.*;

public class Client {
	private String ip, username;
	private Thread thread;
	private int port;
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Controller controller;

	public Client(String ip, int port, String username, Controller controller) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.controller = controller;

		try {

			socket = new Socket(ip, port);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			thread = new ClientThread();
			thread.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendRemoveShape(MusicShape musicShape) {

		MusicShapeMessage msm = new MusicShapeMessage(musicShape.toString(),
				NamedColors.getColorString(musicShape.getColor()), musicShape.getRow(), musicShape.getColumn());

		RemoveShapeMessage removeShapeMessage = new RemoveShapeMessage(msm);

		try {
			output.writeObject(removeShapeMessage);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendShape(MusicShapeMessage msm) {
		try {

			output.writeObject(msm);
			output.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendUsername() {
		try {

			output.writeObject(username);
			output.flush();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void sendObject(Object obj) {

		try {

			output.writeObject(obj);
			output.flush();

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public void stopThread() {

		if (thread != null) {
			thread.interrupt();
			thread = null;
			
			System.out.println("Client Thread stopped");

		}

	}

	private class ClientThread extends Thread {
		public void run() {
			try {

				while (true) {
					Object object = input.readObject();
					controller.update(object);

					System.out.println("Kleinten har mottagit objekt");
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				stopThread();
			}

		}

	}

}
