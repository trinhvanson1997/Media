package vn.media.client.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vn.media.client.view.ClientUI;
import vn.media.client.view.SearchClientPanel;
import vn.media.server.controller.DBConnector;
import vn.media.server.models.Sach;
import vn.media.server.view.TabbedProduct;
import vn.media.server.view.book.TableBookPanel;


public class SearchBookClient {
	private TableBookPanel tableBookPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	
	private SearchClientPanel searchClientPanel;
	
	public SearchBookClient(ClientUI clientUI,DBConnector db) {
	
		
		btnTimKiem = clientUI.getSearchClientPanel().getBtnTimKiemSach();
		tfSearch =clientUI.getSearchClientPanel().getTfSearchBook();
		cbType = clientUI.getSearchClientPanel().getCbTypeBook();
		tableBookPanel = clientUI.getTabbedProduct().getTableBookPanel();
		
		

		

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<Sach> list = db.getAllBook();
			@Override
			public void removeUpdate(DocumentEvent e) {
				showSearchList();
				if(tfSearch.getText().length()==0) {
					tableBookPanel.updateTableClient(list);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				showSearchList();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				showSearchList();
			}
			
			public void showSearchList() {
				List<Sach> tempList = new ArrayList<>();
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				
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
						
							if(convertListToString(sach.getTacGia()).trim().toLowerCase().contains(search)) {
								tempList.add(sach);
								
							
						}
					}
				}
				else if(type == 4) {
					for(Sach sach:list) {
						int soluong = sach.getSoLuongTonKho();
						if(String.valueOf(soluong).startsWith(search)) {
							tempList.add(sach);
						}
					}
				}
			
				else if(type == 5) {
					for(Sach sach:list) {
					
						long giaban = sach.getGiaBan();
						if(String.valueOf(giaban).startsWith(search)) {
							tempList.add(sach);
						}
					}
				}
			
				
				tableBookPanel.updateTableClient(tempList);
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
