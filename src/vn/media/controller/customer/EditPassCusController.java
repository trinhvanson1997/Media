package vn.media.controller.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.view.MainFrame;
import vn.media.view.customer.EditPassCusView;
import vn.media.view.customer.TableCusPanel;

public class EditPassCusController {
	private JButton btnSua;
	private TableCusPanel tableCusPanel;
	
	public EditPassCusController(MainFrame mainFrame,DBConnector db) {
		btnSua = mainFrame.getFuncCusPanel().getBtnSua();
		tableCusPanel = mainFrame.getTableCusPanel();
		
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableCusPanel.getTable().getSelectedRow();
				
				//if a row is chosen
				if(index >=0 ) {
				new EditPassCusView(mainFrame,db);
				}
				else {
					
						JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa !"
								,"Thông báo",JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
		});
	}
}
