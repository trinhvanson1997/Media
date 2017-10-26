package vn.media.server.controller.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.Sach;
import vn.media.server.view.MainFrame;
import vn.media.server.view.book.TableBookPanel;

public class ShowAllBookController {
	private JButton btnXemTatCa;
	private TableBookPanel tableBookPanel;
	private JTabbedPane tab;
	
	public ShowAllBookController(MainFrame mainFrame,DBConnector db) {
		btnXemTatCa = mainFrame.getFuncBookPanel().getBtnXemTatCa();
		tableBookPanel  = mainFrame.getTabbedProduct().getTableBookPanel();
		
		btnXemTatCa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Sach> list = db.getAllBook();
				tableBookPanel.updateTable(list);
		
			}
		});
	}
}
