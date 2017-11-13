package vn.media.controller.wait;

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
import vn.media.view.MainFrame;
import vn.media.view.wait.TableWaitPanel;


public class SearchWaitController {
	private TableWaitPanel TableWaitPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	private DBConnector db;
	
	public SearchWaitController(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		
		btnTimKiem = mainFrame.getFuncWaitPanel().getBtnTimKiem();
		tfSearch = mainFrame.getFuncWaitPanel().getTfSearch();
		cbType = mainFrame.getFuncWaitPanel().getCbType();
		TableWaitPanel = mainFrame.getTableWaitPanel();
		
		

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<HoaDon> list = mainFrame.getListWait();
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				showSearchList();
				if(tfSearch.getText().length()==0) {
					TableWaitPanel.updateTable(list);
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
						if(new SimpleDateFormat("dd/MM/yyyy HH:mm::ss").format(nv.getNgayMuaHang()).startsWith(search)) {
							tempList.add(nv);
						}
					}
				}
				
				
				TableWaitPanel.updateTable(tempList);
			}
			
		});
		
	}
}
