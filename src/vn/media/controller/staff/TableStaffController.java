package vn.media.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.NhanVien;
import vn.media.view.MainFrame;

public class TableStaffController {
	private JButton btnBack,btnNext;
	
	public TableStaffController(MainFrame mainFrame,DBConnector db) {
		btnBack = mainFrame.getTableStaffPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =mainFrame.getPageStaff();
				
				if( page > 0) {
					mainFrame.setPageStaff(page-1);
					List<NhanVien> list = db.getAllStaff(page-1);
					mainFrame.getTableStaffPanel().updateTable(list);
				}
				
			}
		});
		
		btnNext = mainFrame.getTableStaffPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageStaff();
				if(page< db.getCountStaff()/20 ) {
				
					mainFrame.setPageStaff(page+1);
					List<NhanVien> list = db.getAllStaff(page+1);
					mainFrame.getTableStaffPanel().updateTable(list);
				}
				
			}
		});
	}
}
