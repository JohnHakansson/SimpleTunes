package simpleTunes;

import java.io.*;
import java.net.*;

public class Client {
	private String ip, username;
	private int port;
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Controller controller;
		
	
	public Client(String ip, int port, String username, Controller controller) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.controller = controller;
		
		try {
			
			socket = new Socket(ip, port);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void sendShape(MusicShape musicShape) {	
		try {
			
			output.writeObject(musicShape);
			output.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void sendUsername() {
		try {
			
			output.writeObject(username);
			output.flush();
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	private class ClientThread extends Thread {		
		public void run() {			
			try {
				
				Object object = input.readObject();
//				controller.update(object);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
