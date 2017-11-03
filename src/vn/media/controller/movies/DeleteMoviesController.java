package vn.media.controller.movies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.DiaPhim;
import vn.media.models.Sach;
import vn.media.view.MainFrame;
import vn.media.view.movies.TableMoviesPanel;

public class DeleteMoviesController {
	private JButton btnXoa;
	private TableMoviesPanel tableMoviesPanel;
	
	public DeleteMoviesController(MainFrame mainFrame,DBConnector db) {
		btnXoa = mainFrame.getFuncMoviesPanel().getBtnXoa();
		tableMoviesPanel = mainFrame.getTabbedProduct().getTableMoviesPanel();
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableMoviesPanel.getTable().getSelectedRow();
				
				if(index >=0) {
					String id = tableMoviesPanel.getTable().getModel().getValueAt(index, 0).toString();
					int click = JOptionPane.showConfirmDialog(tableMoviesPanel, "Bạn muốn xóa đĩa phim mã '"+id+"'?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
if(JOptionPane.YES_OPTION==click) {
						
						List<DiaPhim> list = mainFrame.getListMovie();
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getId().equals(id)) {
								list.remove(i);
								break;
							}
						}
						
						mainFrame.setListMovie(list);
						tableMoviesPanel.updateTable(list);
						
						db.deleteMovies(id);
						JOptionPane.showMessageDialog(null, "Xóa Dia phim thành công");
						
					}
					
					if(JOptionPane.NO_OPTION == click) {
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn đĩa phim cần xóa !","Thông báo",JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
	}
}
