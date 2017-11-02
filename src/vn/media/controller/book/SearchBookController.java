package vn.media.controller.book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
		
		

		

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<Sach> list = db.getAllBook();
			@Override
			public void removeUpdate(DocumentEvent e) {
				showSearchList();
				if(tfSearch.getText().length()==0) {
					tableBookPanel.updateTable(list);
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
						long giamua = sach.getGiaMua();
					
						if(String.valueOf(giamua).startsWith(search)) {
							tempList.add(sach);
						}
					}
				}
				else if(type == 6) {
					for(Sach sach:list) {
					
						long giaban = sach.getGiaBan();
						if(String.valueOf(giaban).startsWith(search)) {
							tempList.add(sach);
						}
					}
				}
				else if(type == 7) {
					for(Sach sach:list) {
						String date = new SimpleDateFormat("dd/MM/yyyy").format(sach.getNgayNhapHangCuoi());
						
						if(date.startsWith(search)) {
							tempList.add(sach);
						}
					}
				}
				
				tableBookPanel.updateTable(tempList);
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
