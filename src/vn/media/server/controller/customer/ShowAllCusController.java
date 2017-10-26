package vn.media.server.controller.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.KhachHang;
import vn.media.server.view.MainFrame;
import vn.media.server.view.customer.TableCusPanel;

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
