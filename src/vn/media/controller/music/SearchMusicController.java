package vn.media.controller.music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vn.media.controller.DBConnector;
import vn.media.models.DiaNhac;
import vn.media.models.DiaNhac;
import vn.media.view.MainFrame;
import vn.media.view.music.TableMusicPanel;

public class SearchMusicController {
	private TableMusicPanel tableMusicPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	private DBConnector db;
	
	public SearchMusicController(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		
		btnTimKiem = mainFrame.getFuncMusicPanel().getBtnTimKiem();
		tfSearch = mainFrame.getFuncMusicPanel().getTfSearch();
		cbType = mainFrame.getFuncMusicPanel().getCbType();
		tableMusicPanel = mainFrame.getTabbedProduct().getTableMusicPanel();
		
		
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int type 		= cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(search.equals(null) ||search.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				else {
					tableMusicPanel.updateTable(getSearchList(type, search));
				}
				
			}
		});
		
	}
	
	private List<DiaNhac> getSearchList(int type,String search){
		List<DiaNhac> list = db.getAllMusic();
		
		List<DiaNhac> tempList = new ArrayList<>();
		
		if(type == 0) {
			for(DiaNhac DiaNhac:list) {
				if(DiaNhac.getId().trim().toLowerCase().contains(search)) {
					tempList.add(DiaNhac);
				}
			}
		}
		else if(type == 1) {
			for(DiaNhac DiaNhac:list) {
				if(DiaNhac.getTenSP().trim().toLowerCase().contains(search)) {
					tempList.add(DiaNhac);
				}
			}
		}
		else if(type == 2) {
			for(DiaNhac DiaNhac:list) {
				if(DiaNhac.getTheLoai().toString().trim().toLowerCase().contains(search)) {
					tempList.add(DiaNhac);
				}
			}
		}
		else if(type == 3) {
			for(DiaNhac DiaNhac:list) {
				for(String s:DiaNhac.getCaSi()) {
					if(s.trim().toLowerCase().contains(search)) {
						tempList.add(DiaNhac);
					}
				}
			}
		}
		else if(type == 4) {
			for(DiaNhac DiaNhac:list) {
				int soluong = DiaNhac.getSoLuongTonKho();
				if(soluong == Integer.parseInt(search)) {
					tempList.add(DiaNhac);
				}
			}
		}
		else if(type == 5) {
			for(DiaNhac DiaNhac:list) {
				long giamua = DiaNhac.getGiaMua();
			
				if(giamua == Long.parseLong(search)) {
					tempList.add(DiaNhac);
				}
			}
		}
		else if(type == 6) {
			for(DiaNhac DiaNhac:list) {
				
				long giaban = DiaNhac.getGiaBan();
				if(giaban == Long.parseLong(search)) {
					tempList.add(DiaNhac);
				}
			}
		}
		else if(type == 7) {
			for(DiaNhac DiaNhac:list) {
				String date = DiaNhac.getNgayNhapHangCuoi().toString();
				
				if(date.equals(search)) {
					tempList.add(DiaNhac);
				}
			}
		}
		return tempList;
	}
}
