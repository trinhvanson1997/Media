package vn.media.controller.movies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.DiaPhim;
import vn.media.models.Sach;
import vn.media.view.MainFrame;

public class TableMoviesController {
private JButton btnBack,btnNext;
	
	public TableMoviesController(MainFrame mainFrame,DBConnector db) {
	btnBack = mainFrame.getTabbedProduct().getTableMoviesPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =mainFrame.getPageMovies();
				
				if( page > 0) {
					mainFrame.setPageMovies(page-1);
					List<DiaPhim> list = db.getAllMovies(page-1);
					mainFrame.getTabbedProduct().getTableMoviesPanel().updateTable(list);
				}
				
			}
		});
		
		btnNext = mainFrame.getTabbedProduct().getTableMoviesPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageMovies();
				
				if(page< db.getCountMovies()/20 ) {
					mainFrame.setPageMovies(page+1);
					List<DiaPhim> list = db.getAllMovies(page+1);
					mainFrame.getTabbedProduct().getTableMoviesPanel().updateTable(list);
				}
				
			}
		});
	}
}
