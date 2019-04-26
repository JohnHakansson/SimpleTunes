package simpleTunes;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ClientMap clientMap;

	public ClientHandler(Socket socket, ObjectInputStream input, ObjectOutputStream output, ClientMap clientMap) {
		this.socket = socket;
		this.input = input;
		this.output = output;
		this.clientMap = clientMap;
	}

	public void send(Object obj) {
		try {
			output.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Object obj;

		while (true) {

			try {

				obj = input.readObject();

			} catch (IOException | ClassNotFoundException e) {

			}

		}
	}

}