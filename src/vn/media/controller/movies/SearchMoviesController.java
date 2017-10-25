package vn.media.controller.movies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vn.media.controller.DBConnector;
import vn.media.models.DiaPhim;
import vn.media.models.DiaPhim;
import vn.media.view.MainFrame;
import vn.media.view.movies.TableMoviesPanel;

public class SearchMoviesController {
	private TableMoviesPanel tableMoviesPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	private DBConnector db;
	
	public SearchMoviesController(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		
		btnTimKiem = mainFrame.getFuncMoviesPanel().getBtnTimKiem();
		tfSearch = mainFrame.getFuncMoviesPanel().getTfSearch();
		cbType = mainFrame.getFuncMoviesPanel().getCbType();
		tableMoviesPanel = mainFrame.getTabbedProduct().getTableMoviesPanel();
		
		
		btnTimKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(search.equals(null) ||search.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				else {
					tableMoviesPanel.updateTable(getSearchList(type, search));
				}
				
			}
		});
		
	}
	
	private List<DiaPhim> getSearchList(int type,String search){
		List<DiaPhim> list = db.getAllMovies();
		
		List<DiaPhim> tempList = new ArrayList<>();
		
		if(type == 0) {
			for(DiaPhim DiaPhim:list) {
				if(DiaPhim.getId().trim().toLowerCase().contains(search)) {
					tempList.add(DiaPhim);
				}
			}
		}
		else if(type == 1) {
			for(DiaPhim DiaPhim:list) {
				if(DiaPhim.getTenSP().trim().toLowerCase().contains(search)) {
					tempList.add(DiaPhim);
				}
			}
		}
		else if(type == 2) {
			for(DiaPhim DiaPhim:list) {
				if(DiaPhim.getDaoDien().toString().trim().toLowerCase().contains(search)) {
					tempList.add(DiaPhim);
				}
			}
		}
		else if(type == 3) {
			for(DiaPhim DiaPhim:list) {
				for(String s:DiaPhim.getDienVien()) {
					if(s.trim().toLowerCase().contains(search)) {
						tempList.add(DiaPhim);
					}
				}
			}
		}
		else if(type == 4) {
			for(DiaPhim DiaPhim:list) {
				int soluong = DiaPhim.getSoLuongTonKho();
				if(soluong == Integer.parseInt(search)) {
					tempList.add(DiaPhim);
				}
			}
		}
		else if(type == 5) {
			for(DiaPhim DiaPhim:list) {
				long giamua = DiaPhim.getGiaMua();
				
				if(giamua == Long.parseLong(search)) {
					tempList.add(DiaPhim);
				}
			}
		}
		else if(type == 6) {
			for(DiaPhim DiaPhim:list) {
			
				long giaban = DiaPhim.getGiaBan();
				if(giaban == Long.parseLong(search)) {
					tempList.add(DiaPhim);
				}
			}
		}
		else if(type == 7) {
			for(DiaPhim DiaPhim:list) {
				String date = DiaPhim.getNgayNhapHangCuoi().toString();
				
				if(date.equals(search)) {
					tempList.add(DiaPhim);
				}
			}
		}
		return tempList;
	}
	
	
}
