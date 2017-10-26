package vn.media.client.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vn.media.client.view.ClientUI;
import vn.media.server.controller.DBConnector;
import vn.media.server.models.DiaPhim;
import vn.media.server.view.movies.TableMoviesPanel;

public class SearchMovieClient {
	
	private TableMoviesPanel tableMoviesPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	
	
	public SearchMovieClient(ClientUI clientUI,DBConnector db) {
		
		btnTimKiem = clientUI.getSearchClientPanel().getBtnTimKiemPhim();
		tfSearch =clientUI.getSearchClientPanel().getTfSearchMovie();
		cbType = clientUI.getSearchClientPanel().getCbTypeMovie();
		tableMoviesPanel = clientUI.getTabbedProduct().getTableMoviesPanel();
		
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<DiaPhim> list = db.getAllMovies();
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showSearchList();
				if(tfSearch.getText().length()==0) {
					tableMoviesPanel.updateTableClient(list);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showSearchList();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showSearchList();
			}
			
			public void showSearchList() {
				List<DiaPhim> tempList = new ArrayList<>();
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
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
						
							if(convertListToString(DiaPhim.getDienVien()).trim().toLowerCase().contains(search)) {
								tempList.add(DiaPhim);
							}
						
					}
				}
				else if(type == 4) {
					for(DiaPhim DiaPhim:list) {
						int soluong = DiaPhim.getSoLuongTonKho();
						if(String.valueOf(soluong).startsWith(search)) {
							tempList.add(DiaPhim);
						}
					}
				}
			
				else if(type == 5) {
					for(DiaPhim DiaPhim:list) {
					
						long giaban = DiaPhim.getGiaBan();
						if(String.valueOf(giaban).startsWith(search)) {
							tempList.add(DiaPhim);
						}
					}
				}
			
				
				tableMoviesPanel.updateTableClient(tempList);
			}
		});
		
	}

	private String convertListToString(List<String> list){
		String temp ="";
		for(String s: list) {
			temp += s;
			temp += ",";
		}
		if(temp.equals("")) {
			return temp;
			}
		else {
			temp=temp.substring(0, temp.length()-1);
			return temp;
		}
	}
}
