package vn.media.main;

import vn.media.common.IOFile;
import vn.media.controller.DBConnector;
import vn.media.server.Server;
import vn.media.view.LoginBox;

public class Main {
	
	public static void main(String[] args) {
		DBConnector db = new DBConnector();
		IOFile ioFile = new IOFile();
		
		
		ioFile.readFile();
		
		LoginBox login = new LoginBox(db);
		
		Server server = new Server(db,login);
		server.serve();
		
		
		
	}
}
