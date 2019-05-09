package simpleTunes;

import java.io.*;
import java.util.*;

/**
 * The Class represents the message that is sent to the recently connected
 * client with information about other clients currently connected.
 * 
 * @author John Håkansson, Roland Askelöf, Matilda Frimodig
 *
 */

public class InitialStateMessage implements Serializable {
	private static final long serialVersionUID = -3100934356855903019L;
	private ArrayList<String> connectedUsers = new ArrayList<String>();

	public InitialStateMessage(ArrayList<String> connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	public ArrayList<String> getConnectedUsers() {
		return connectedUsers;
	}
}
