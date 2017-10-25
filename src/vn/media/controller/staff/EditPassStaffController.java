package vn.media.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.view.MainFrame;
import vn.media.view.staff.EditPassStaffView;
import vn.media.view.staff.TableStaffPanel;

public class EditPassStaffController {
	private JButton btnSua;
	private TableStaffPanel tableStaffPanel;
	
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
