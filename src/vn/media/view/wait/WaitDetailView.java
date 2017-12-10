package vn.media.view.wait;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import vn.media.models.HoaDon;
import vn.media.view.bill.TableBillDetailPanel;

public class WaitDetailView extends JDialog{
	private JButton btnXuly;
	
	public WaitDetailView(String idHoaDon,HoaDon hd) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(200,250);
		setLayout(new BorderLayout(10,10));
		setTitle("Chi tiết hóa đơn mã " + idHoaDon);
		
		
		TableBillDetailPanel table = new TableBillDetailPanel(); 
		table.updateTable(hd);
		
		btnXuly = new JButton("XỬ LÝ ĐƠN HÀNG");
		
		add(table,BorderLayout.CENTER);
		add(btnXuly,BorderLayout.SOUTH);
		
		btnXuly.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.setLocation(450, 100);;
		pack();
		setVisible(true);
		
	}

	public JButton getBtnXuly() {
		return btnXuly;
	}

	public void setBtnXuly(JButton btnXuly) {
		this.btnXuly = btnXuly;
	}
	

		
}
