package vn.media.controller.movies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.view.MainFrame;
import vn.media.view.book.AddBookView;
import vn.media.view.movies.AddMoviesView;
import vn.media.view.movies.TableMoviesPanel;

public class AddMoviesController {
	private JButton btnThem;
	private TableMoviesPanel tableMoviesPanel;
	
	public AddMoviesController(MainFrame mainFrame,DBConnector db) {
		btnThem = mainFrame.getFuncMoviesPanel().getBtnThem();
		tableMoviesPanel = mainFrame.getTabbedProduct().getTableMoviesPanel();
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new AddMoviesView(db, tableMoviesPanel);
				
			}
		});
	}
}
