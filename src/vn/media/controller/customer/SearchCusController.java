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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
		
		

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<KhachHang> list = db.getAllCus();
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				showSearchList();
				if(tfSearch.getText().length()==0) {
					tableCusPanel.updateTable(list);
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

				List<KhachHang> tempList = new ArrayList<>();
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
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
						if(new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()).startsWith(search)) {
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
						
						if(nv.getsDT().trim().toLowerCase().startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 5) {
					for(KhachHang nv:list) {
						long coin = nv.getCoin();
						if(String.valueOf(coin).startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 6) {
					for(KhachHang nv:list) {
						if(nv.getUsername().trim().toLowerCase().startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				
				tableCusPanel.updateTable(tempList);
			}
			
		});
		
	}
	

}
