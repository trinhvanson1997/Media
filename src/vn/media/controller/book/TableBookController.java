package vn.media.controller.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.models.Sach;
import vn.media.view.MainFrame;

public class TableBookController {
private JButton btnBack,btnNext;
	
	public TableBookController(MainFrame mainFrame,DBConnector db) {
	btnBack = mainFrame.getTabbedProduct().getTableBookPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =mainFrame.getPageBook();
				
				if( page > 0) {
					System.out.println(page);
					mainFrame.setPageBook(page-1);
					List<Sach> list = db.getAllBook(page-1);
					mainFrame.getTabbedProduct().getTableBookPanel().updateTable(list);
				}
				
			}
		});
		
		btnNext = mainFrame.getTabbedProduct().getTableBookPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageBook();
				
				if(page< db.getCountBook()/20 ) {
					System.out.println(page);
					mainFrame.setPageBook(page+1);
					List<Sach> list = db.getAllBook(page+1);
					mainFrame.getTabbedProduct().getTableBookPanel().updateTable(list);
				}
				
			}
		});
	}
}
