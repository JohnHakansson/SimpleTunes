package simpleTunes;

import java.util.*;

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
	
	public synchronized Set getKeySet() {
		return onlineUsers.keySet();
	}

}
