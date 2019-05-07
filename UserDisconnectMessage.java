package simpleTunes;

import java.io.Serializable;

public class UserDisconnectMessage implements Serializable {
	private static final long serialVersionUID = -8161377873339573465L;
	private String username;

	public UserDisconnectMessage(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

}
