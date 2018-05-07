package vn.media.main;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vn.media.common.IOFile;
import vn.media.controller.DBConnector;
import vn.media.models.Store;
import vn.media.server.Server;
import vn.media.view.LoginBox;

public class MainServer {
	
	public static void main(String[] args) {
		DBConnector db = new DBConnector();
		IOFile ioFile = new IOFile();
		ioFile.readFile();
		Store store = new Store("MediaOne", Store.totalMoney, db);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
			catch(Exception ex) { }
		
		
		System.out.println("total money: "+ store.totalMoney);
		LoginBox login = new LoginBox(db,store);
		
		Server server = new Server(db,login);
		server.serve();
		
		
		
	}
}
