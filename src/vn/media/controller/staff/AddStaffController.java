package vn.media.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.view.MainFrame;
import vn.media.view.staff.AddStaffView;
import vn.media.view.staff.TableStaffPanel;

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
