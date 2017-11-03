package vn.media.controller.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.Sach;
import vn.media.view.MainFrame;
import vn.media.view.book.TableBookPanel;

public class DeleteBookController {
	private JButton btnXoa;
	private TableBookPanel tableBookPanel;
	
	public DeleteBookController(MainFrame mainFrame,DBConnector db) {
		btnXoa = mainFrame.getFuncBookPanel().getBtnXoa();
		tableBookPanel = mainFrame.getTabbedProduct().getTableBookPanel();
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableBookPanel.getTable().getSelectedRow();
				
				if(index >=0) {
					String id = tableBookPanel.getTable().getModel().getValueAt(index, 0).toString();
					int click = JOptionPane.showConfirmDialog(tableBookPanel, "Bạn muốn xóa sách mã '"+id+"'?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
					if(JOptionPane.YES_OPTION==click) {
						
						List<Sach> list = mainFrame.getListBook();
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getId().equals(id)) {
								list.remove(i);
								break;
							}
						}
						
						mainFrame.setListBook(list);
						tableBookPanel.updateTable(list);
						
						db.deleteBook(id);
						JOptionPane.showMessageDialog(null, "Xóa sách thành công");
						
					}
					
					if(JOptionPane.NO_OPTION == click) {
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần xóa !","Thông báo",JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
	}
}
