package simpleTunes;

import java.io.Serializable;

/**
 * This is sent to all connected user when a user disconnects from the server.
 * 
 * @author Roland Askel�f, Matilda Frimodig, John H�kansson
 *
 */
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
