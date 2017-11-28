package vn.media.controller.fee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.Fee;
import vn.media.models.Sach;
import vn.media.models.Store;
import vn.media.view.MainFrame;
import vn.media.view.fee.TableFeePanel;

public class DeleteFeeController {
	private JButton btnXoa;
	private TableFeePanel tableFeePanel;

	public DeleteFeeController(MainFrame mainFrame, DBConnector db) {
		btnXoa = mainFrame.getFuncFeePanel().getBtnXoa();
		tableFeePanel = mainFrame.getTableFeePanel();

		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableFeePanel.getTable().getSelectedRow();

				if (index >= 0) {
					String ten = tableFeePanel.getTable().getModel().getValueAt(index, 0).toString();
					if (ten.equals("Tiền lương")) {
						JOptionPane.showConfirmDialog(mainFrame, "Không được xóa chi phí này!");
					} else {
						int a = JOptionPane.showConfirmDialog(tableFeePanel, "Bạn muốn xóa chi phí '" + ten + "'?",
								"Cảnh báo", JOptionPane.YES_NO_OPTION);
						if (a == JOptionPane.YES_OPTION) {
							List<Fee> list = mainFrame.getListFee();
							for(int i=0;i<list.size();i++) {
								if(list.get(i).getFeeName().equals(ten)) {
									list.remove(i);
									break;
								}
							}
							
							mainFrame.setListFee(list);
							tableFeePanel.updateTable(list);
							
							db.deleteFee(ten);
							JOptionPane.showMessageDialog(null, "Xóa chi phí thành công");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần xóa !", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
	}
}
