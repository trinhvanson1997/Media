package vn.media.controller.fee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.controller.DBConnector;
import vn.media.models.Paid;
import vn.media.models.Store;
import vn.media.view.MainFrame;
import vn.media.view.fee.PaidFeeView;
import vn.media.view.fee.TableFeePanel;

public class PaidFeeController {
	private JButton btnThanhToan;
	private TableFeePanel tableFeePanel;

	public PaidFeeController(MainFrame mainFrame, DBConnector db,Store store) {
		btnThanhToan = mainFrame.getFuncFeePanel().getBtnThanhToan();
		tableFeePanel = mainFrame.getTableFeePanel();

		btnThanhToan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Paid> list = db.getAllPaid(mainFrame.getPagePaid());
				PaidFeeView paidFeeView=new PaidFeeView(db,mainFrame, tableFeePanel,store);
				paidFeeView.updateTable(list);
			}
		});
	}
}
