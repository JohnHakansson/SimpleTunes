package simpleTunes;

import java.io.*;
import java.net.*;

/**
 * Connects to the given ip and port of a server. Sends and receives
 * information.
 * 
 * @author John Håkansson, Roland Askelöf, Matilda Frimodig
 *
 */
public class Client {
	private String ip, username;
	private Thread thread;
	private int port;
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Controller controller;

	/**
	 * Creates a Socket with a connection to the incoming ip and port. Starts the
	 * thread in the inner-class ClientThread
	 * 
	 * @param ip         The ip that the client is connected to
	 * @param port       The port that the client is listening to
	 * @param username   The username of the client
	 * @param controller instance of Controller
	 */
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

	/**
	 * Disconnects the client, closes the socket
	 */
	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sending the information about the shape to the Server.
	 * 
	 * @param msm Contains information about the shape
	 */
	public void sendShape(MusicShapeMessage msm) {
		try {

			output.writeObject(msm);
			output.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sending the clients username to the Server.
	 */
	public void sendUsername() {
		try {

			output.writeObject(username);
			output.flush();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * Sending an object to the Server.
	 * 
	 * @param obj Object
	 */
	public void sendObject(Object obj) {

		try {

			output.writeObject(obj);
			output.flush();

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Stops the thread
	 */
	public void stopThread() {

		if (thread != null) {
			thread.interrupt();
			thread = null;

		}

	}

	/**
	 * The class reads incoming objects from the server and sends the incoming
	 * objects to the controller.
	 * 
	 * @author John Håkansson, Roland Askelöf, Matilda Frimodig
	 *
	 */
	private class ClientThread extends Thread {
		public void run() {
			try {

				while (true) {
					Object object = input.readObject();
					controller.update(object);

				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				stopThread();
			}

		}

	}

}
