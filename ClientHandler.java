package simpleTunes;

import java.io.*;

/**
 * Every client connected to the server has a ClientHandler handling the flow of
 * information between client and server
 * 
 * @author John H�kansson, Roland Askel�f, Matilda Frimodig
 *
 */
public class ClientHandler extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ClientMap clientMap;
	private String receivingUser;

	private String username;
	private Server server;

	/**
	 * 
	 * @param username  The username of the connected to the ClientHandler
	 * @param input     The ObjectInputStream connected to the client
	 * @param output    The ObjectOutputStream connected to the client
	 * @param clientMap
	 * @param server    instance of Server
	 */
	public ClientHandler(String username, ObjectInputStream input, ObjectOutputStream output, ClientMap clientMap,
			Server server) {
		this.input = input;
		this.output = output;
		this.clientMap = clientMap;
		this.username = username;
		this.server = server;
	}

	/**
	 * Sending an object to the client
	 * 
	 * @param obj Object
	 */
	public void send(Object obj) {
		
		if(obj instanceof UserDisconnectMessage) {
			UserDisconnectMessage userDisconnectMessage = (UserDisconnectMessage)obj;
			
			if(userDisconnectMessage.getUsername().equals(receivingUser)) {
				receivingUser = null;
			}
		}
		
		try {
			output.writeObject(obj);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Sets the receiving user
	 * 
	 * @param receivingUser The receiving user
	 */
	public void setReceivingUser(String receivingUser) {
		this.receivingUser = receivingUser;

	}
	
	/**
	 * Returns the receivingUser
	 * @return receivingUser a String
	 */
	
	public String getReceivingUser() {
		return receivingUser;
	}

	/**
	 * Overrides Thread.run 
	 * reads incoming objects and does an action depending on the type of object.
	 * 
	 * 
	 */
	public void run() {
		Object obj;

		while (true) {

			try {

				obj = input.readObject();

				if (obj instanceof ConnectToUserMessage) {

					ConnectToUserMessage connectToUserMessage = (ConnectToUserMessage) obj;

					ClientHandler tempReceiver = clientMap.get(connectToUserMessage.getReceiverUsername());
					
					if(tempReceiver.getReceivingUser() == null) {
						
						ConnectRequestMessage connectRequestMessage = new ConnectRequestMessage(connectToUserMessage.getSenderUsername(),
								connectToUserMessage.getReceiverUsername());

						connectRequestMessage.setIsResponse(true);
						tempReceiver.send(connectRequestMessage);
					}

					else {
						send(new String("User is already making music with someone else"));
						
					}

				} else if (obj instanceof ConnectRequestMessage) {

					ConnectRequestMessage response = (ConnectRequestMessage) obj;

					if (response.getConnectRequest()) {

						setReceivingUser(response.getSenderUsername());

						clientMap.get(response.getSenderUsername()).setReceivingUser(response.getReceiverUsername());

					}

					ClientHandler tempReceiver = clientMap.get(response.getSenderUsername());
					response.setIsResponse(false);
					tempReceiver.send(response);

				}

				else if (receivingUser != null){

					clientMap.get(receivingUser).send(obj);

				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();

			} catch (IOException e1) {

				server.disconnectUser(username);
				break;
				
			}

		}

	}

}