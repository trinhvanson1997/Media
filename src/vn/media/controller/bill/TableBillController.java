package vn.media.controller.bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.models.NhanVien;
import vn.media.view.MainFrame;

public class TableBillController {
	private JButton btnBack,btnNext;
	
	public TableBillController(MainFrame mainFrame,DBConnector db) {
	btnBack = mainFrame.getTableBillPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =mainFrame.getPageBill();
				
				if( page > 0) {
					mainFrame.setPageBill(page-1);
					List<HoaDon> list = db.getAllBill(page-1);
					mainFrame.getTableBillPanel().updateTable(list);
				}
				
			}
		});
		
		btnNext = mainFrame.getTableBillPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageBill();
				if(page< db.getCountBill()/20 ) {
					
					mainFrame.setPageBill(page+1);
					List<HoaDon> list = db.getAllBill(page+1);
					mainFrame.getTableBillPanel().updateTable(list);
				}
				
			}
		});
	}
}
