package vn.media.controller.bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.view.MainFrame;
import vn.media.view.bill.TableBillPanel;

public class ShowAllBillController {
	private JButton btnXemTatCa;
	private TableBillPanel tableBillPanel;
	private JTabbedPane tab;
	
	public ShowAllBillController(MainFrame mainFrame,DBConnector db) {
		btnXemTatCa = mainFrame.getFuncBillPanel().getBtnXemTatCa();
		tableBillPanel  = mainFrame.getTableBillPanel();
		
		btnXemTatCa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<HoaDon> list =mainFrame.getListBill();
				tableBillPanel.updateTable(list);
		
			}
		});
	}
}
