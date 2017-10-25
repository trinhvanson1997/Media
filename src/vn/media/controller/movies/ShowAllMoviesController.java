package vn.media.controller.movies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import vn.media.controller.DBConnector;
import vn.media.models.DiaPhim;
import vn.media.view.MainFrame;
import vn.media.view.movies.TableMoviesPanel;

public class ShowAllMoviesController {
	private JButton btnXemTatCa;
	private TableMoviesPanel tableMoviesPanel;

	
	public ShowAllMoviesController(MainFrame mainFrame,DBConnector db) {
		btnXemTatCa = mainFrame.getFuncMoviesPanel().getBtnXemTatCa();
		tableMoviesPanel  = mainFrame.getTabbedProduct().getTableMoviesPanel();
		
		btnXemTatCa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<DiaPhim> list = db.getAllMovies();
				tableMoviesPanel.updateTable(list);
		
			}
		});
	}
}
