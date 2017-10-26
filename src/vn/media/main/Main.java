package vn.media.main;

import vn.media.server.common.IOFile;
import vn.media.server.controller.DBConnector;
import vn.media.server.view.LoginBox;

public class Main {
	
	public static void main(String[] args) {
		DBConnector db = new DBConnector();
		IOFile ioFile = new IOFile();
		
		
		ioFile.readFile();
		
		LoginBox login = new LoginBox(db);
		
		
//		 MainFrame  mainFrame = new MainFrame("son",db);
//		 
//		 /*          FUNCTIONS OF STAFF        */
//		 new ShowAllStaffController(mainFrame,db);
//		 new AddStaffController(mainFrame, db);
//		 new EditPassStaffController(mainFrame, db);
//		 new DeleteStaffController(mainFrame, db);
//		 new SearchStaffController(mainFrame, db);
//		 
//		 /*			CHANGE MAINPANEL     		*/
//		new ChangeTableController(mainFrame,db);
//		
//		
//		/*			FUNCTIONS OF CUSTOMER 		*/
//		new ShowAllCusController(mainFrame, db);
//		new EditPassCusController(mainFrame, db);
//		new SearchCusController(mainFrame, db);
//		
//		
//		/*			FUNCTIONS OF BOOKS			*/
//		new ShowAllBookController(mainFrame, db);
//		new AddBookController(mainFrame, db);
//		new SearchBookController(mainFrame, db);
//		new DeleteBookController(mainFrame, db);
//		new EditBookController(mainFrame, db);
//		
//		
//		/*			FUNCTIONS OF MOVIES			*/
//		new ShowAllMoviesController(mainFrame, db);
//		new AddMoviesController(mainFrame, db);
//		new SearchMoviesController(mainFrame, db);
//		new DeleteMoviesController(mainFrame, db);
//		new EditMoviesController(mainFrame, db);
//		
//		/*			FUNCTIONS OF MUSIC			*/
//		new ShowAllMusicController(mainFrame, db);
//		new AddMusicController( mainFrame, db);
//		new SearchMusicController( mainFrame, db);
//		new DeleteMusicController(mainFrame, db);
//		new EditMusicController(mainFrame, db);
//		
		
	}
}
