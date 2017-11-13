package vn.media.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import vn.media.controller.DBConnector;
import vn.media.view.LoginBox;

public class Server {
	public ServerSocket serverSocket;
	public DBConnector db;
	public LoginBox login;
	
	public Server(DBConnector db,LoginBox login) {
		this.db = db;
		this.login =login;
	}
	
	public void serve()  {
		try {
			serverSocket = new ServerSocket(2000);
		
			System.out.println("Server is ready!");
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("connected");
				
				ClientThread thread = new ClientThread(login,socket,db, this);
				thread.start();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
}
