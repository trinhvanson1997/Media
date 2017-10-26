package vn.media.server.controller.movies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.DiaPhim;
import vn.media.server.view.MainFrame;
import vn.media.server.view.movies.EditMoviesView;
import vn.media.server.view.movies.TableMoviesPanel;

public class EditMoviesController {
	private JButton btnSua;
	private TableMoviesPanel tableMoviescPanel;

	public EditMoviesController(MainFrame mainFrame, DBConnector db) {
		btnSua = mainFrame.getFuncMoviesPanel().getBtnSua();
		tableMoviescPanel = mainFrame.getTabbedProduct().getTableMoviesPanel();

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = tableMoviescPanel.getTable().getSelectedRow();

				// if a row is chosen
				if (index >= 0) {
					String id = tableMoviescPanel.getTable().getModel().getValueAt(index, 0).toString();

					DiaPhim diaphim = db.getMovies(id);
					new EditMoviesView(db, tableMoviescPanel, diaphim);
				} else {

					JOptionPane.showMessageDialog(null, "Vui lòng chọn đĩa phim cần sửa !", "Thông báo",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});
	}
}
