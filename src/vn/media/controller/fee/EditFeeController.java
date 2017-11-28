package vn.media.controller.fee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.Fee;
import vn.media.models.Store;
import vn.media.view.MainFrame;
import vn.media.view.fee.EditFeeView;
import vn.media.view.fee.TableFeePanel;

public class EditFeeController {
	private JButton btnSua;
	private TableFeePanel tableFeePanel;

	public EditFeeController(MainFrame mainFrame, DBConnector db) {
		btnSua = mainFrame.getFuncFeePanel().getBtnSua();
		tableFeePanel = mainFrame.getTableFeePanel();

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = tableFeePanel.getTable().getSelectedRow();
				
				// if a row is chosen
				if (index >= 0) {
					String name = tableFeePanel.getTable().getModel().getValueAt(index, 0).toString();
					if(name.equals("Tiền lương")) {
						JOptionPane.showMessageDialog(tableFeePanel, "Không được sửa chi phí này");
					}
					else{
						Fee fee = db.getFee(name);
						new EditFeeView(mainFrame,db, tableFeePanel, fee);
					}
					
				} else {

					JOptionPane.showMessageDialog(null, "Vui lòng chọn chi phí cần sửa !", "Thông báo",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});
	}
}
