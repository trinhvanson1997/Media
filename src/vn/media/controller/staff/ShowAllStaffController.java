package vn.media.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.NhanVien;
import vn.media.view.MainFrame;
import vn.media.view.staff.FuncStaffPanel;
import vn.media.view.staff.TableStaffPanel;

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
