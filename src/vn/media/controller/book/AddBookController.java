package vn.media.controller.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.view.MainFrame;
import vn.media.view.book.AddBookView;
import vn.media.view.book.TableBookPanel;

public class AddBookController {
	private JButton btnThem;
	private TableBookPanel tableBookPanel;
	
	public AddBookController(MainFrame mainFrame,DBConnector db) {
		btnThem = mainFrame.getFuncBookPanel().getBtnThem();
		tableBookPanel = mainFrame.getTabbedProduct().getTableBookPanel();
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new AddBookView(db, tableBookPanel);
				
			}
		});
	}
}
