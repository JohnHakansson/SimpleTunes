package simpleTunes;

import java.io.Serializable;

public class ConnectToUserMessage implements Serializable{
	private static final long serialVersionUID = -989060912576444555L;
	private String senderUsername;
	private String receiverUsername;
	
	
	public ConnectToUserMessage(String senderUsername, String receiverUsername) {
		this.senderUsername = senderUsername;
		this.receiverUsername = receiverUsername;
	}
	
	public String getSenderUsername() {
		return this.senderUsername;
	}
	
	public String getReceiverUsername() {
		return this.receiverUsername;
	}
	
	
	
}
