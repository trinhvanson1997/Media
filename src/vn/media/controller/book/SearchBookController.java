package vn.media.controller.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vn.media.controller.DBConnector;
import vn.media.models.Sach;
import vn.media.view.MainFrame;
import vn.media.view.book.TableBookPanel;

public class SearchBookController {
	private TableBookPanel tableBookPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	private DBConnector db;
	
	public SearchBookController(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		
		btnTimKiem = mainFrame.getFuncBookPanel().getBtnTimKiem();
		tfSearch = mainFrame.getFuncBookPanel().getTfSearch();
		cbType = mainFrame.getFuncBookPanel().getCbType();
		tableBookPanel = mainFrame.getTabbedProduct().getTableBookPanel();
		
		
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(search.equals(null) ||search.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				else {
					tableBookPanel.updateTable(getSearchList(type, search));
				}
				
			}
		});
		
	}
	
	private List<Sach> getSearchList(int type,String search){
		List<Sach> list = db.getAllBook();
		
		List<Sach> tempList = new ArrayList<>();
		
		if(type == 0) {
			for(Sach sach:list) {
				if(sach.getId().trim().toLowerCase().contains(search)) {
					tempList.add(sach);
				}
			}
		}
		else if(type == 1) {
			for(Sach sach:list) {
				if(sach.getTenSP().trim().toLowerCase().contains(search)) {
					tempList.add(sach);
				}
			}
		}
		else if(type == 2) {
			for(Sach sach:list) {
				if(sach.getNhaXB().toString().trim().toLowerCase().contains(search)) {
					tempList.add(sach);
				}
			}
		}
		else if(type == 3) {
			for(Sach sach:list) {
				for(String s:sach.getTacGia()) {
					if(s.trim().toLowerCase().contains(search)) {
						tempList.add(sach);
					}
				}
			}
		}
		else if(type == 4) {
			for(Sach sach:list) {
				int soluong = sach.getSoLuongTonKho();
				if(soluong == Integer.parseInt(search)) {
					tempList.add(sach);
				}
			}
		}
		else if(type == 5) {
			for(Sach sach:list) {
				long giamua = sach.getGiaMua();
			
				if(giamua == Long.parseLong(search)) {
					tempList.add(sach);
				}
			}
		}
		else if(type == 6) {
			for(Sach sach:list) {
			
				long giaban = sach.getGiaBan();
				if(giaban == Long.parseLong(search)) {
					tempList.add(sach);
				}
			}
		}
		else if(type == 7) {
			for(Sach sach:list) {
				String date = sach.getNgayNhapHangCuoi().toString();
				
				if(date.equals(search)) {
					tempList.add(sach);
				}
			}
		}
		return tempList;
	}
	
	

}
