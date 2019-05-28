package simpleTunes;

import java.util.*;

	/**
	 * The class represents the container component
	 * used by the server and clienthandler to store
	 * information about the users which are interacting
	 * with the server.
	 * 
	 * @author Matilda Frimodig, Roland Askelöf, John Håkansson
	 *
	 */

public class ClientMap {
	private HashMap<String, ClientHandler> onlineUsers = new HashMap<String, ClientHandler>();
	
	/**
	 * 
	 * @param username the key of the hashmap
	 * @param clienthandler the value of the hashsmap
	 */
	public synchronized void put(String username, ClientHandler clienthandler) {
		onlineUsers.put(username, clienthandler);
	}
	
	/**
	 * 
	 * @param username key needed to return the associated clienthandler
	 * @return ClientHandler for the specific user
	 */
	public synchronized ClientHandler get(String username) {
		return onlineUsers.get(username);
	}
	
	/**
	 * 
	 * @param username The username to be checked
	 * @return a boolean value. True if the hashmap keyset contains the username
	 */
	public synchronized boolean contains(String username) {
		return onlineUsers.containsKey(username);
	}
	
	/**
	 * 
	 * @return the hashmaps keyset
	 */
	public synchronized Set<String> getKeySet() {
		return onlineUsers.keySet();
	}

	/**
	 * 
	 * @param user Username to be removed with its associated clienthandler
	 */
	public synchronized void remove(String user) {

		onlineUsers.remove(user);

	}

}
