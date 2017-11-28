package vn.media.controller.fee;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vn.media.controller.DBConnector;
import vn.media.models.Fee;
import vn.media.view.MainFrame;
import vn.media.view.fee.TableFeePanel;

public class SearchFeeController {
	private TableFeePanel tableFeePanel;
	@SuppressWarnings("unused")
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	@SuppressWarnings("unused")
	private DBConnector db;
	
	public SearchFeeController(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		
		btnTimKiem = mainFrame.getFuncFeePanel().getBtnTimKiem();
		tfSearch = mainFrame.getFuncFeePanel().getTfSearch();
		cbType = mainFrame.getFuncFeePanel().getCbType();
		tableFeePanel = mainFrame.getTableFeePanel();
		

		
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<Fee> list = mainFrame.getListFee();
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showSearchList();
				if(tfSearch.getText().length()==0) {
					tableFeePanel.updateTable(list);
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
				List<Fee> tempList = new ArrayList<>();
				int type = cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(type == 0) {
					for(Fee Fee:list) {
						if(Fee.getFeeName().trim().toLowerCase().contains(search)) {
							tempList.add(Fee);
						}
					}
				}
				else if(type == 1) {
					for(Fee Fee:list) {
						if(String.valueOf(Fee.getFeeValue()).equals(search) &&
								String.valueOf(Fee.getFeeValue()).startsWith(search)
								) {
							tempList.add(Fee);
						}
					}
				}
				else if(type == 2) {
					for(Fee Fee:list) {
						if(String.valueOf(Fee.getFeeCycle()).equals(search) &&
								String.valueOf(Fee.getFeeCycle()).startsWith(search)
								) {
							tempList.add(Fee);
						}
					}
				}
				tableFeePanel.updateTable(tempList);
				
			}
		});
		
	}
	

}
