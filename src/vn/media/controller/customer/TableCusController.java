package vn.media.controller.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.models.KhachHang;
import vn.media.view.MainFrame;

public class TableCusController {
private JButton btnBack,btnNext;
	
	public TableCusController(MainFrame mainFrame,DBConnector db) {
	btnBack = mainFrame.getTableCusPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =mainFrame.getPageCus();
				
				if( page > 0) {
					mainFrame.setPageBill(page-1);
					List<KhachHang> list = db.getAllCus(page-1);
					mainFrame.getTableCusPanel().updateTable(list);
				}
				
			}
		});
		
		btnNext = mainFrame.getTableCusPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageBill();
				if(page< db.getCountCus()/20 ) {
					
					mainFrame.setPageBill(page+1);
					List<KhachHang> list = db.getAllCus(page+1);
					mainFrame.getTableCusPanel().updateTable(list);
				}
				
			}
		});
	}
}
