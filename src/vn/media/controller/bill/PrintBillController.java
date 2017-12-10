package vn.media.controller.bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.controller.ExportToPDF;
import vn.media.models.HoaDon;
import vn.media.view.MainFrame;
import vn.media.view.bill.TableBillPanel;

public class PrintBillController {
	private JButton button;
	private TableBillPanel table;
	
	public PrintBillController(MainFrame mainFrame,DBConnector db) {
		button = mainFrame.getFuncBillPanel().getBtnInPDF();
		table = mainFrame.getTableBillPanel();
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getTable().getSelectedRow();
				if (index >= 0) {
					String id = table.getTable().getModel().getValueAt(index, 0).toString();

					HoaDon hoadon = db.getBill(id);
					new ExportToPDF(hoadon,db);
					JOptionPane.showMessageDialog(null, "Đã in hóa đơn mã "+ hoadon.getId() );
				} else {

					JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần sửa !", "Thông báo",
							JOptionPane.WARNING_MESSAGE);

				}
				
			}
		});
	}
}
