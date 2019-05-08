package simpleTunes;

import java.util.*;

	/**
	 * The class represents the container component
	 * used by the server and clienthandler to store
	 * information about the users which are interacting
	 * with the server.
	 *
	 */

public class ClientMap {
	private HashMap<String, ClientHandler> onlineUsers = new HashMap<String, ClientHandler>();

	public synchronized void put(String username, ClientHandler clienthandler) {
		onlineUsers.put(username, clienthandler);
	}

	public synchronized ClientHandler get(String username) {
		return onlineUsers.get(username);
	}

	public synchronized boolean contains(String username) {
		return onlineUsers.containsKey(username);
	}

	public synchronized Set<String> getKeySet() {
		return onlineUsers.keySet();
	}

	public synchronized void remove(String user) {

		onlineUsers.remove(user);

	}

}
