package vn.media.view.bill;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.media.models.HoaDon;

public class BillDetailView extends JDialog{
	
	public BillDetailView(String idHoaDon,HoaDon hd) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(200,250);
		setLayout(new BorderLayout(10,10));
		setTitle("Chi tiết hóa đơn mã " + idHoaDon);
		
		
		TableBillDetailPanel table = new TableBillDetailPanel(); 
		table.updateTable(hd);
		
		
		long tongtien = 0;
		for(int i=0;i<hd.getMuaHang().size();i++) {
			tongtien += hd.getMuaHang().get(i).getDonGia()*hd.getMuaHang().get(i).getSoLuong();
		}
		
		
		
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));
		
		JLabel label = new JLabel("TỔNG TIỀN :    " + format.format(tongtien).toString());
		Font font = label.getFont().deriveFont(Font.PLAIN, 20f);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);

		
		
		add(table,BorderLayout.CENTER);
		add(label,BorderLayout.SOUTH);
		
		
		
		this.setLocation(450, 100);;
		pack();
		setVisible(true);
		
	}
	

		
}
