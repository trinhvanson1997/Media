package vn.media.server.controller.music;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.DiaNhac;
import vn.media.server.view.MainFrame;
import vn.media.server.view.music.EditMusicView;
import vn.media.server.view.music.TableMusicPanel;

public class EditMusicController {
	private JButton btnSua;
	private TableMusicPanel tableMusicPanel;

	public EditMusicController(MainFrame mainFrame, DBConnector db) {
		btnSua = mainFrame.getFuncMusicPanel().getBtnSua();
		tableMusicPanel = mainFrame.getTabbedProduct().getTableMusicPanel();

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = tableMusicPanel.getTable().getSelectedRow();

				// if a row is chosen
				if (index >= 0) {
					String id = tableMusicPanel.getTable().getModel().getValueAt(index, 0).toString();

					DiaNhac dianhac = db.getMusic(id);
					new EditMusicView(db, tableMusicPanel, dianhac);
				} else {

					JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần sửa !", "Thông báo",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});
	}
}
