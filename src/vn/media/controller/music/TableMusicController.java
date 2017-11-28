package vn.media.controller.music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.view.MainFrame;

public class TableMusicController {
private JButton btnBack,btnNext;
	
	public TableMusicController(MainFrame mainFrame,DBConnector db) {
	btnBack = mainFrame.getTabbedProduct().getTableMusicPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =mainFrame.getPageMusic();
				
				if( page > 0) {
					System.out.println(page);
					mainFrame.setPageMusic(page-1);
					List<DiaNhac> list = db.getAllMusic(page-1);
					mainFrame.getTabbedProduct().getTableMusicPanel().updateTable(list);
				}
				
			}
		});
		
		btnNext = mainFrame.getTabbedProduct().getTableMusicPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageMusic();
				
				if(page< db.getCountMusic()/20 ) {
					System.out.println(page);
					mainFrame.setPageMusic(page+1);
					List<DiaNhac> list = db.getAllMusic(page+1);
					mainFrame.getTabbedProduct().getTableMusicPanel().updateTable(list);
				}
				
			}
		});
	}
}
