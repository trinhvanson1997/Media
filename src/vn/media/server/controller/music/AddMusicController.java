package vn.media.server.controller.music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vn.media.server.controller.DBConnector;
import vn.media.server.view.MainFrame;
import vn.media.server.view.music.AddMusicView;
import vn.media.server.view.music.TableMusicPanel;

public class AddMusicController {
	private JButton btnThem;
	private TableMusicPanel tableMusicPanel;
	
	public AddMusicController(MainFrame mainFrame,DBConnector db) {
		btnThem = mainFrame.getFuncMusicPanel().getBtnThem();
		tableMusicPanel = mainFrame.getTabbedProduct().getTableMusicPanel();
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new AddMusicView(db, tableMusicPanel);
				
			}
		});
	}
}
