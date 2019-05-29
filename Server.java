package simpleTunes;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The class represents the server part of the system.
 * The server runs on a single thread and instantiates
 * a new thread for each connected user. The different
 * users are handled with its own clienthandler.
 * users is
 *
 *@author John Håkansson, Roland Askelöf, Matilda Frimodig, Tom Lanhed Sivertsson, Jesper Lindberg
 */

public class Server implements Runnable {
	private Thread thread = new Thread(this);
	private ServerSocket serverSocket;
	private ClientMap clientMap = new ClientMap();

	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			thread.start();
			System.out.println("Server is Running");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * The method handles a disconnect-request from a user
	 * and removes the client from all of the other connected
	 * clients interfaces.
	 */
	
	public synchronized void disconnectUser(String disconnectedUser) {

		clientMap.remove(disconnectedUser);

		UserDisconnectMessage userDisconnectMessage = new UserDisconnectMessage(disconnectedUser);

		for (String user : clientMap.getKeySet()) {

			clientMap.get(user).send(userDisconnectMessage);
			
		}

	}

	/**
	 * The run method from where the server-thread is waiting for clients
	 * to connect. A connected client is given its own Clienthandler and
	 * the client thread is started.
	 */
	
	public void run() {

		while (true) {
			try {
				
				Socket socket = serverSocket.accept();
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

				String username = (String) input.readObject();
				ArrayList<String> onlineUsers = new ArrayList<String>(clientMap.getKeySet());

				if (clientMap.contains(username)) {
					String nameAlreadyExists = ("Username already exists, please choose another username ");
					output.writeObject(nameAlreadyExists);

				} else {

					InitialStateMessage initialStateMessage = new InitialStateMessage(onlineUsers);
					output.writeObject(initialStateMessage);

					UserConnectMessage userConnectMessage = new UserConnectMessage(username);

					for (String onlineUser : onlineUsers) {
						ClientHandler tempClient = clientMap.get(onlineUser);
						tempClient.send(userConnectMessage);

					}

					ClientHandler client = new ClientHandler(username, input, output, clientMap, this);
					clientMap.put(username, client);
					client.start();

				}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server(5555);
		
	}

}
