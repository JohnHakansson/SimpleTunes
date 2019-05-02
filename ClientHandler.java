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
				
				if(obj instanceof String) {
					setReceivingUser((String)obj);
				}
				
				else {
					clientMap.get(receivingUser).send(obj);
					
				}
				

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		
	}

}