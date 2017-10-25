package vn.media.controller.customer;

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
import vn.media.models.KhachHang;
import vn.media.view.MainFrame;
import vn.media.view.customer.TableCusPanel;

public class SearchCusController {
	private TableCusPanel tableCusPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	private DBConnector db;
	
	public SearchCusController(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		
		btnTimKiem = mainFrame.getFuncCusPanel().getBtnTimKiem();
		tfSearch = mainFrame.getFuncCusPanel().getTfSearch();
		cbType = mainFrame.getFuncCusPanel().getCbType();
		tableCusPanel = mainFrame.getTableCusPanel();
		
		
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(search.equals(null) ||search.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				else {
					tableCusPanel.updateTable(getSearchList(type, search));
				}
				
			}
		});
		
	}
	
	private List<KhachHang> getSearchList(int type,String search){
		List<KhachHang> list = db.getAllCus();
		
		List<KhachHang> tempList = new ArrayList<>();
		
		if(type == 0) {
			for(KhachHang nv:list) {
				if(nv.getId().trim().toLowerCase().contains(search)) {
					tempList.add(nv);
				}
			}
		}
		else if(type == 1) {
			for(KhachHang nv:list) {
				if(nv.getHoTen().trim().toLowerCase().contains(search)) {
					tempList.add(nv);
				}
			}
		}
		else if(type == 2) {
			for(KhachHang nv:list) {
				if(new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()).contains(search)) {
					tempList.add(nv);
				}
			}
		}
		else if(type == 3) {
			for(KhachHang nv:list) {
				if(nv.getDiaChi().trim().toLowerCase().contains(search)) {
					tempList.add(nv);
				}
			}
		}
		else if(type == 4) {
			for(KhachHang nv:list) {
				
				if(nv.getsDT().trim().toLowerCase().contains(search)) {
					tempList.add(nv);
				}
			}
		}
		else if(type == 5) {
			for(KhachHang nv:list) {
				long coin = nv.getCoin();
				if(coin == Long.parseLong(search)) {
					tempList.add(nv);
				}
			}
		}
		else if(type == 6) {
			for(KhachHang nv:list) {
				if(nv.getUsername().trim().toLowerCase().contains(search)) {
					tempList.add(nv);
				}
			}
		}
		return tempList;
	}
}
