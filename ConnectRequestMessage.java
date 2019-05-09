package simpleTunes;

import java.io.Serializable;

/**
 * This class is sent from client to server and server to client and contains
 * the names of the user that sent the connect-request and the recipient of the
 * connect-request. The recipient user can accept or decline the request and the
 * answer is stored in a boolean that is sent back to the sender.
 * 
 * @author John Håkansson, Jesper Lindberg, Tom Lanhed Sivertsson
 *
 */

public class ConnectRequestMessage implements Serializable {
	private static final long serialVersionUID = 3860405176225664345L;
	private boolean connectRequest = false;
	private boolean isResponse = false;

	private String message;
	private String recieverUsername;
	private String senderUsername;

	/**
	 * Constructs a COnnectRequestMessage with the names of the users involved
	 * 
	 * @param senderUsername   the sender of the connect-request
	 * @param recieverUsername the recipient of the connect-request
	 */

	public ConnectRequestMessage(String senderUsername, String recieverUsername) {
		this.senderUsername = senderUsername;
		message = senderUsername + " wants to connect with you. Do you accept?";
		this.recieverUsername = recieverUsername;

	}

	/**
	 * Returns a boolean if the recipient accepted the request and false if declined
	 * 
	 * @return a boolean true if the recipient accepted the request and false if
	 *         declined
	 */

	public boolean getConnectRequest() {
		return connectRequest;
	}

	/**
	 * Returns a boolean true if the message is on it's way back to the sender.
	 * 
	 * @return a boolean
	 */

	public boolean getIsResponse() {
		return isResponse;
	}
	
	public String getSenderUsername() {
		return senderUsername;
	}

	public void setConnectRequest(boolean connectRequest) {
		this.connectRequest = connectRequest;
	}

	public void setIsResponse(boolean isResponse) {
		this.isResponse = isResponse;
	}

	public String getMessage() {
		return message;
	}

	public String getReceiverUsername() {
		return recieverUsername;

	}

}
