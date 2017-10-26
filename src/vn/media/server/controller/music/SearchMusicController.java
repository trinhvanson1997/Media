package vn.media.server.controller.music;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.DiaNhac;
import vn.media.server.view.MainFrame;
import vn.media.server.view.music.TableMusicPanel;

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
		
	
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<DiaNhac> list = db.getAllMusic();
	
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showSearchList();
				if(tfSearch.getText().length()==0) {
					tableMusicPanel.updateTable(list);
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
				List<DiaNhac> tempList = new ArrayList<>();
				int type 		= cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
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
						
							if(convertListToString(DiaNhac.getCaSi()).trim().toLowerCase().contains(search)) {
								tempList.add(DiaNhac);
							}
						
					}
				}
				else if(type == 4) {
					for(DiaNhac DiaNhac:list) {
						int soluong = DiaNhac.getSoLuongTonKho();
						if(String.valueOf(soluong).startsWith(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
				else if(type == 5) {
					for(DiaNhac DiaNhac:list) {
						long giamua = DiaNhac.getGiaMua();
					
						if(String.valueOf(giamua).startsWith(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
				else if(type == 6) {
					for(DiaNhac DiaNhac:list) {
						
						long giaban = DiaNhac.getGiaBan();
					
						if(String.valueOf(giaban).startsWith(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
				else if(type == 7) {
					for(DiaNhac DiaNhac:list) {
						String date = new SimpleDateFormat("dd/MM/yyyy").format(DiaNhac.getNgayNhapHangCuoi());
						
						if(date.startsWith(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
				
				tableMusicPanel.updateTable(tempList);
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
