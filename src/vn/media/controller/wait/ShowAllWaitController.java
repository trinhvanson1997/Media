package vn.media.controller.wait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.view.MainFrame;
import vn.media.view.wait.TableWaitPanel;

public class ShowAllWaitController {
	private JButton btnXemTatCa;
	private TableWaitPanel tableWaitPanel;
	private JTabbedPane tab;
	
	public ShowAllWaitController(MainFrame mainFrame,DBConnector db) {
		btnXemTatCa = mainFrame.getFuncWaitPanel().getBtnXemTatCa();
		tableWaitPanel  = mainFrame.getTableWaitPanel();
		
		btnXemTatCa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<HoaDon> list =mainFrame.getListWait();
				tableWaitPanel.updateTable(list);
		
			}
		});
	}
}
