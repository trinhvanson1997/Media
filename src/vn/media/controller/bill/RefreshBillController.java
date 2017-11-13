package vn.media.controller.bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.view.MainFrame;
import vn.media.view.bill.TableBillPanel;

public class RefreshBillController {
	private JButton btnRefresh;
	private TableBillPanel tableBillPanel;
	
	public RefreshBillController(MainFrame mainFrame,DBConnector db) {
		btnRefresh = mainFrame.getFuncBillPanel().getBtnRefresh();
		tableBillPanel = mainFrame.getTableBillPanel();
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<HoaDon> list = db.getAllBill(mainFrame.getPageBill());
				tableBillPanel.updateTable(list);
				mainFrame.setListBill(list);
				
			}
		});
	}
}
