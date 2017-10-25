package vn.media.controller.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.KhachHang;
import vn.media.view.MainFrame;
import vn.media.view.customer.TableCusPanel;

public class ShowAllCusController {
	private JButton btnXemTatCa;
	private TableCusPanel tableCusPanel;
	
	public ShowAllCusController(MainFrame mainFrame,DBConnector db) {
		btnXemTatCa = mainFrame.getFuncCusPanel().getBtnXemTatCa();
		tableCusPanel = mainFrame.getTableCusPanel();
		
		btnXemTatCa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<KhachHang> list= db.getAllCus();
				
				tableCusPanel.updateTable(list);
			}
		});
	}
}
