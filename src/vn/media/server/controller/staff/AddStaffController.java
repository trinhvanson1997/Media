package vn.media.server.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vn.media.server.controller.DBConnector;
import vn.media.server.view.MainFrame;
import vn.media.server.view.staff.AddStaffView;
import vn.media.server.view.staff.TableStaffPanel;

public class AddStaffController {
	private JButton btnThem;
	private TableStaffPanel tablePanel;
	
	public AddStaffController(MainFrame mainFrame,DBConnector db) {
		btnThem = mainFrame.getFuncStaffPanel().getBtnThem();
		tablePanel = mainFrame.getTableStaffPanel();
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new AddStaffView(db, tablePanel);
				
			}
		});
	}
}
