package vn.media.controller.bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.models.KhachHang;
import vn.media.view.MainFrame;
import vn.media.view.bill.TableBillPanel;

public class SearchBillController {
	private TableBillPanel tableBillPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	private DBConnector db;
	
	public SearchBillController(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		
		btnTimKiem = mainFrame.getFuncBillPanel().getBtnTimKiem();
		tfSearch = mainFrame.getFuncBillPanel().getTfSearch();
		cbType = mainFrame.getFuncBillPanel().getCbType();
		tableBillPanel = mainFrame.getTableBillPanel();
		
		

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<HoaDon> list = mainFrame.getListBill();
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				showSearchList();
				if(tfSearch.getText().length()==0) {
					tableBillPanel.updateTable(list);
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

				List<HoaDon> tempList = new ArrayList<>();
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(type == 0) {
					for(HoaDon nv:list) {
						if(nv.getId().trim().toLowerCase().startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 1) {
					for(HoaDon nv:list) {
						if(nv.getIdKhachHang().trim().toLowerCase().startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 2) {
					for(HoaDon nv:list) {
						if(nv.getIdNhanVien().trim().toLowerCase().startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				else if(type == 3) {
					for(HoaDon nv:list) {
						if(new SimpleDateFormat("dd/MM/yyyy HH:mm::ss").format(nv.getNgayMuaHang()).startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				
				
				tableBillPanel.updateTable(tempList);
			}
			
		});
		
	}
}
