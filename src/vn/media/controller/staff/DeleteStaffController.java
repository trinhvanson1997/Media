package vn.media.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.NhanVien;
import vn.media.view.MainFrame;
import vn.media.view.staff.TableStaffPanel;

public class DeleteStaffController {
	private JButton btnXoa;
	private TableStaffPanel tableStaffPanel;
	
	public DeleteStaffController(MainFrame mainFrame,DBConnector db) {
		btnXoa = mainFrame.getFuncStaffPanel().getBtnXoa();
		tableStaffPanel = mainFrame.getTableStaffPanel();
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableStaffPanel.getTable().getSelectedRow();
				if(index >=0) {
					String id = tableStaffPanel.getTable().getModel().getValueAt(index, 0).toString();
					int click = JOptionPane.showConfirmDialog(tableStaffPanel, "Bạn muốn xóa nhân viên '"+id+"'?", "Cảnh báo", JOptionPane.YES_NO_OPTION);
					if(JOptionPane.YES_OPTION==click) {
						db.deleteStaff(id);
						List<NhanVien> list = mainFrame.getListStaff();
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getId().equals(id)) {
								list.remove(i);
							}
						}
						
						mainFrame.setListStaff(list);
						tableStaffPanel.updateTable(list);
						JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
					}
					
					if(JOptionPane.NO_OPTION == click) {
						return;
					
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa !","Thông báo",JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
	}
}
