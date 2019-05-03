package simpleTunes;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ClientMap clientMap;
	private String receivingUser;

	public ClientHandler(ObjectInputStream input, ObjectOutputStream output, ClientMap clientMap) {
		this.input = input;
		this.output = output;
		this.clientMap = clientMap;
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

//					ConnectRequestMessage respons = (ConnectRequestMessage) input.readObject();
//
//					if (respons.getConnectRequest()) {
//
//						setReceivingUser(ctum.getReceiverUsername());
//					}

					System.out.println("ConnectToUserMessage mottagits");

				} else if (obj instanceof ConnectRequestMessage) {

					ConnectRequestMessage respons = (ConnectRequestMessage) obj;

					if (respons.getConnectRequest()) {

						setReceivingUser(respons.getSenderUsername());
						
						clientMap.get(respons.getSenderUsername()).setReceivingUser(respons.getReceiverUsername());

					}

				}

				else {

					clientMap.get(receivingUser).send(obj);

					System.out.println("Handler is sending object");

				}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

}