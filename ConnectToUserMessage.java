package simpleTunes;

import java.io.Serializable;

/**
 * This class represents a connect message from the sending user.
 * 
 * @author John H�kansson, Matilda Frimodig, Roland Askel�f
 *
 */

public class ConnectToUserMessage implements Serializable {
	private static final long serialVersionUID = -989060912576444555L;
	private String senderUsername;
	private String receiverUsername;

	public ConnectToUserMessage(String senderUsername, String receiverUsername) {
		this.senderUsername = senderUsername;
		this.receiverUsername = receiverUsername;
		
	}
	
	/**
	 * Returns the username of the user sending the connect message
	 * @return a String username of the user sending the connect message
	 */

	public String getSenderUsername() {
		return this.senderUsername;
		
	}
	
	/**
	 * Returns the  username of the user revceiveing the connect message
	 * @return a String the username of the user revceiveing the connect message
	 */
	
	public String getReceiverUsername() {
		return this.receiverUsername;
		
	}

}
