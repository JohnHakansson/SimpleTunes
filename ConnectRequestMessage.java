package simpleTunes;

import java.io.Serializable;

public class ConnectRequestMessage implements Serializable {
	private static final long serialVersionUID = 3860405176225664345L;
	private boolean connectRequest = false;
	private String message;
	private String recieverUsername;
	private String senderUsername;

	public ConnectRequestMessage(String senderUsername, String recieverUsername) {
		this.senderUsername = senderUsername;
		message = senderUsername + " wants to connect with you. Do you accept?";
		this.recieverUsername = recieverUsername;

	}

	public boolean getConnectRequest() {
		return connectRequest;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setConnectRequest(boolean connectRequest) {
		this.connectRequest = connectRequest;
	}

	public String getMessage() {
		return message;
	}

	public String getReceiverUsername() {
		return recieverUsername;

	}

}
