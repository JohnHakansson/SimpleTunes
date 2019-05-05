package simpleTunes;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements Runnable {
	private Thread thread = new Thread(this);
	private ServerSocket serverSocket;
	private ClientMap clientMap = new ClientMap();

	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void disconnectUser(String disconnectedUser) {

		clientMap.remove(disconnectedUser);

		UserDisconnectMessage udm = new UserDisconnectMessage(disconnectedUser);

		for (String user : clientMap.getKeySet()) {

			clientMap.get(user).send(udm);

		}

	}

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

						System.out.println("Sending UserConnectMessage to :" + onlineUser);
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
