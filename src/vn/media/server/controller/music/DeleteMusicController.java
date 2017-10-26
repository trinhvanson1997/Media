package vn.media.server.controller.music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.DiaNhac;
import vn.media.server.view.MainFrame;
import vn.media.server.view.music.TableMusicPanel;

public class DeleteMusicController {
	private JButton btnXoa;
	private TableMusicPanel tableMusicPanel;
	
	public DeleteMusicController(MainFrame mainFrame,DBConnector db) {
		btnXoa = mainFrame.getFuncMusicPanel().getBtnXoa();
		tableMusicPanel = mainFrame.getTabbedProduct().getTableMusicPanel();
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableMusicPanel.getTable().getSelectedRow();
				
				if(index >=0) {
					String id = tableMusicPanel.getTable().getModel().getValueAt(index, 0).toString();
					int click =JOptionPane.showConfirmDialog(tableMusicPanel, "Bạn muốn xóa dĩa nhạc mã '"+id+"'?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
					if(JOptionPane.YES_OPTION==click) {
						db.deleteMusic(id);
						List<DiaNhac> list = db.getAllMusic();
						tableMusicPanel.updateTable(list);
						JOptionPane.showMessageDialog(null, "Xóa đĩa nhạc thành công");
					}
					if(JOptionPane.NO_OPTION == click) {
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn đĩa nhạc cần xóa !","Thông báo",JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
	}
}
