package vn.media.controller.staff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vn.media.controller.DBConnector;
import vn.media.models.NhanVien;
import vn.media.view.MainFrame;
import vn.media.view.staff.TableStaffPanel;

public class SearchStaffController {
	private TableStaffPanel tableStaffPanel;
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
		
		
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(search.equals(null) ||search.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				else {
					tableStaffPanel.updateTable(getSearchList(type, search));
				}
				
			}
		});
		
	}
	
	private List<NhanVien> getSearchList(int type,String search){
		List<NhanVien> list = db.getAllStaff();
		
		List<NhanVien> tempList = new ArrayList<>();
		
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
				if(new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()).contains(search)) {
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
				
				if(nv.getsDT().trim().toLowerCase().contains(search)) {
					tempList.add(nv);
				}
			}
		}
		else if(type == 5) {
			for(NhanVien nv:list) {
				long luong = nv.getLuong();
				if(luong == Long.parseLong(search)) {
					tempList.add(nv);
				}
			}
		}
		else if(type == 6) {
			for(NhanVien nv:list) {
				if(nv.getUsername().trim().toLowerCase().contains(search)) {
					tempList.add(nv);
				}
			}
		}
		return tempList;
	}
}
