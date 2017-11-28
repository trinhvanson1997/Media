package vn.media.controller.fee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.Store;
import vn.media.view.MainFrame;
import vn.media.view.fee.AddFeeView;
import vn.media.view.fee.TableFeePanel;

public class AddFeeController {
	private JButton btnThem;
	private TableFeePanel tableFeePanel;
	
	public AddFeeController(MainFrame mainFrame,DBConnector db) {
		btnThem = mainFrame.getFuncFeePanel().getBtnThem();
		tableFeePanel = mainFrame.getTableFeePanel();
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new AddFeeView(db,mainFrame, tableFeePanel);
				
			}
		});
	}
}
