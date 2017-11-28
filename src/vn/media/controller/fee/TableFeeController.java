package vn.media.controller.fee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.Fee;
import vn.media.view.MainFrame;
import vn.media.view.fee.TableFeePanel;

public class TableFeeController {
	private JButton btNext,btBack;
	private TableFeePanel taFeePanel;
	public TableFeeController(MainFrame mainFrame,DBConnector db) {
		taFeePanel=mainFrame.getTableFeePanel();
		btNext =taFeePanel.getBtNext();
		btBack =taFeePanel.getBtBack();
		btBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageFee();
				if(page>0) {
					page=page-1;
					mainFrame.setPageFee(page);
					List<Fee> list = db.getAllFee(page);
					taFeePanel.updateTable(list);
				}
				
			}
		});
		btNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageFee();
				if(page<db.getCountFee()/20) {
					page=page+1;
					mainFrame.setPageFee(page);
					List<Fee> list = db.getAllFee(page);
					taFeePanel.updateTable(list);
				}
				
			}
		});
	}
}
