package vn.media.controller.music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.DiaNhac;
import vn.media.view.MainFrame;
import vn.media.view.music.TableMusicPanel;

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
					
						List<DiaNhac> list = db.getAllMusic();
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getId().equals(id)) {
								list.remove(i);
								break;
							}
						}
						
						tableMusicPanel.updateTable(list);
						mainFrame.setListMusic(list);
						db.deleteMusic(id);
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
