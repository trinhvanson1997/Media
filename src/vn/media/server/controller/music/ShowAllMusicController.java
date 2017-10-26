package vn.media.server.controller.music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.DiaNhac;
import vn.media.server.view.MainFrame;
import vn.media.server.view.music.TableMusicPanel;

public class ShowAllMusicController {
	private JButton btnXemTatCa;
	private TableMusicPanel tableMusicPanel;
	private JTabbedPane tab;
	
	public ShowAllMusicController(MainFrame mainFrame,DBConnector db) {
		btnXemTatCa = mainFrame.getFuncMusicPanel().getBtnXemTatCa();
		tableMusicPanel  = mainFrame.getTabbedProduct().getTableMusicPanel();
		
		btnXemTatCa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<DiaNhac> list = db.getAllMusic();
				tableMusicPanel.updateTable(list);
		
			}
		});
	}
}
