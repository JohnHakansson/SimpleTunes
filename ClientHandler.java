package simpleTunes;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ClientMap clientMap;
	private String receivingUser;
	private String username;
	private Server server;

	public ClientHandler(String username, ObjectInputStream input, ObjectOutputStream output, ClientMap clientMap,
			Server server) {
		this.input = input;
		this.output = output;
		this.clientMap = clientMap;
		this.username = username;
		this.server = server;
	}

	public void send(Object obj) {

		try {
			output.writeObject(obj);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setReceivingUser(String receivingUser) {
		this.receivingUser = receivingUser;

	}

	public void run() {
		Object obj;

		while (true) {

			try {

				obj = input.readObject();

				if (obj instanceof ConnectToUserMessage) {

					ConnectToUserMessage ctum = (ConnectToUserMessage) obj;

					ClientHandler tempReceiver = clientMap.get(ctum.getReceiverUsername());

					ConnectRequestMessage crm = new ConnectRequestMessage(ctum.getSenderUsername(),
							ctum.getReceiverUsername());

					tempReceiver.send(crm);

				} else if (obj instanceof ConnectRequestMessage) {

					ConnectRequestMessage respons = (ConnectRequestMessage) obj;

					if (respons.getConnectRequest()) {

						setReceivingUser(respons.getSenderUsername());

						clientMap.get(respons.getSenderUsername()).setReceivingUser(respons.getReceiverUsername());

					}

				}

				else {

					clientMap.get(receivingUser).send(obj);

				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (IOException e1) {

				server.disconnectUser(username);

			}

		}

	}

}