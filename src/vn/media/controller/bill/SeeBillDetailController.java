package vn.media.controller.bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.view.MainFrame;
import vn.media.view.bill.BillDetailView;

public class SeeBillDetailController {
	private JButton btnThongTinChiTiet;
	
	public SeeBillDetailController(MainFrame mainFrame,DBConnector db) {
		btnThongTinChiTiet = mainFrame.getFuncBillPanel().getBtnThongTinChiTiet();
		
		btnThongTinChiTiet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = mainFrame.getTableBillPanel().getTable().getSelectedRow();
				
				if(index >= 0) {
					String idhoadon = (String) mainFrame.getTableBillPanel().getTable().getModel().getValueAt(index, 0);
					HoaDon hd = db.getBill(idhoadon);
					
					new BillDetailView(idhoadon, hd);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn đơn hàng cần xem !", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
	}
	
}
