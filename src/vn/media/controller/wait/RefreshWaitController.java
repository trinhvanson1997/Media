package vn.media.controller.wait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.view.MainFrame;
import vn.media.view.wait.TableWaitPanel;

public class RefreshWaitController {
	private JButton btnRefresh;
	private TableWaitPanel tableWaitPanel;
	
	public RefreshWaitController(MainFrame mainFrame,DBConnector db) {
		btnRefresh = mainFrame.getFuncWaitPanel().getBtnRefresh();
		tableWaitPanel = mainFrame.getTableWaitPanel();
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<HoaDon> list = db.getAllWait(mainFrame.getPageWait());
				tableWaitPanel.updateTable(list);
				mainFrame.setListWait(list);
				
			}
		});
	}
}