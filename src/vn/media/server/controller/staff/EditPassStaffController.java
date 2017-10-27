package vn.media.server.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.server.controller.DBConnector;
import vn.media.server.view.MainFrame;
import vn.media.server.view.staff.EditPassStaffView;
import vn.media.server.view.staff.TableEmployeePanel;

public class EditPassStaffController {
	private JButton btnSua;
	private TableEmployeePanel tableStaffPanel;
	
	public EditPassStaffController(MainFrame mainFrame,DBConnector db) {
		btnSua = mainFrame.getFuncStaffPanel().getBtnSua();
		tableStaffPanel = mainFrame.getTableStaffPanel();
		
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableStaffPanel.getTable().getSelectedRow();
				
				//if a row is chosen
				if(index >=0 ) {
				new EditPassStaffView(mainFrame,db);
				}
				else {
					
						JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa !"
								,"Thông báo",JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
		});
	}
}
