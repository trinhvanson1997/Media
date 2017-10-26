package vn.media.server.controller.movies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vn.media.server.controller.DBConnector;
import vn.media.server.view.MainFrame;
import vn.media.server.view.movies.AddMoviesView;
import vn.media.server.view.movies.TableMoviesPanel;

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
