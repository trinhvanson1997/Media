package vn.media.server.controller.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.Sach;
import vn.media.server.view.MainFrame;
import vn.media.server.view.book.EditBookView;
import vn.media.server.view.book.TableBookPanel;

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
