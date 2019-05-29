package simpleTunes;

import java.io.Serializable;

/**
 * This message is sent to all connected users when a new user connects.
 * 
 * @author John H�kansson, Matilda Frimodig, Roland Askel�f
 *
 */
public class UserConnectMessage implements Serializable {
	private static final long serialVersionUID = -989060912576444555L;
	private String username;

	public UserConnectMessage(String username) {
		this.username = username;
		
	}

	public String getUsername() {
		return this.username;
		
	}

}
