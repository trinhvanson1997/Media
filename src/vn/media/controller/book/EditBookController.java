package vn.media.controller.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.Sach;
import vn.media.view.MainFrame;
import vn.media.view.book.EditBookView;
import vn.media.view.book.TableBookPanel;

public class EditBookController {
	private JButton btnSua;
	private TableBookPanel tableBookPanel;

	public EditBookController(MainFrame mainFrame, DBConnector db) {
		btnSua = mainFrame.getFuncBookPanel().getBtnSua();
		tableBookPanel = mainFrame.getTabbedProduct().getTableBookPanel();

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = tableBookPanel.getTable().getSelectedRow();

				// if a row is chosen
				if (index >= 0) {
					String id = tableBookPanel.getTable().getModel().getValueAt(index, 0).toString();

					Sach sach = db.getBook(id);
					new EditBookView(db, tableBookPanel, sach);
				} else {

					JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần sửa !", "Thông báo",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});
	}
}
