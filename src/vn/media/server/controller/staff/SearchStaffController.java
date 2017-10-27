package vn.media.server.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.NhanVien;
import vn.media.server.view.MainFrame;
import vn.media.server.view.staff.TableEmployeePanel;

public class SearchStaffController {
	private TableEmployeePanel tableStaffPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	private DBConnector db;
	public SearchStaffController(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		
		btnTimKiem = mainFrame.getFuncStaffPanel().getBtnTimKiem();
		tfSearch = mainFrame.getFuncStaffPanel().getTfSearch();
		cbType = mainFrame.getFuncStaffPanel().getCbType();
		tableStaffPanel = mainFrame.getTableStaffPanel();
		
	
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<NhanVien> list = db.getAllStaff();
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if(tfSearch.getText().length()==0) {
					tableStaffPanel.updateTable(list);
				}
				else {
					showSearchList();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				showSearchList();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				showSearchList();
			}
			
			public void showSearchList() {
				List<NhanVien> tempList = new ArrayList<>();
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(type == 0) {
					for(NhanVien nv:list) {
						if(nv.getId().trim().toLowerCase().contains(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 1) {
					for(NhanVien nv:list) {
						if(nv.getHoTen().trim().toLowerCase().contains(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 2) {
					for(NhanVien nv:list) {
						if(new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()).startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 3) {
					for(NhanVien nv:list) {
						if(nv.getDiaChi().trim().toLowerCase().contains(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 4) {
					for(NhanVien nv:list) {
						
						if(nv.getsDT().trim().toLowerCase().startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 5) {
					for(NhanVien nv:list) {
						long luong = nv.getLuong();
						if(String.valueOf(luong).startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 6) {
					for(NhanVien nv:list) {
						if(nv.getUsername().trim().toLowerCase().startsWith(search)) {
							tempList.add(nv);
						}
					}
			}
				
				tableStaffPanel.updateTable(tempList);
				
		};
		
	});
	
}
}