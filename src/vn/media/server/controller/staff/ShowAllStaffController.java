package vn.media.server.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.NhanVien;
import vn.media.server.view.MainFrame;
import vn.media.server.view.staff.FuncStaffPanel;
import vn.media.server.view.staff.TableStaffPanel;

public class ShowAllStaffController  {
//	private DBConnector db;
//	private MainFrame mainFrame;
	private FuncStaffPanel funcPanel;
	private TableStaffPanel tablePanel;
	private JButton btnShowAll;
	
	
	public ShowAllStaffController(MainFrame mainFrame,DBConnector db) {
//		this.mainFrame = mainFrame;
//		this.db=db;
		
		btnShowAll = mainFrame.getFuncStaffPanel().getBtnXemTatCa();
		tablePanel = mainFrame.getTableStaffPanel();
		
		btnShowAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<NhanVien> list = db.getAllStaff();
				tablePanel.updateTable(list);
				
			}
		});
	}

	

}
