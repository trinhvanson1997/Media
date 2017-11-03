package vn.media.controller.music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import vn.media.controller.DBConnector;
import vn.media.models.DiaNhac;
import vn.media.view.MainFrame;
import vn.media.view.music.TableMusicPanel;

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
				List<DiaNhac> list = mainFrame.getListMusic();
				tableMusicPanel.updateTable(list);
		
			}
		});
	}
}
