package vn.media.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import vn.media.controller.DBConnector;

public class Server {
	public ServerSocket serverSocket;
	public DBConnector db;
	
	public Server(DBConnector db) {
		this.db = db;
	}
	
	public void serve()  {
		try {
			serverSocket = new ServerSocket(2000);
			System.out.println("Server is ready!");
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("connected");
				ClientThread thread = new ClientThread(socket,db, this);
				thread.start();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
}
