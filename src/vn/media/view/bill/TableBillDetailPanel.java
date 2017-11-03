package vn.media.view.bill;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vn.media.models.HoaDon;

public class TableBillDetailPanel extends JPanel {
	private JTable table;
	private JLabel lbName;
	private JScrollPane scroll;
	private String idHoaDon;
	private String[] columns = { "Mã sản phẩm", "Số lượng","Đơn giá" };

	public TableBillDetailPanel() {
	
		
		setLayout(new BorderLayout(10, 0));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);

		loadData(table);

		scroll = new JScrollPane();

		scroll.setViewportView(table);
	//	add(namePanel(), BorderLayout.PAGE_START);
		add(scroll, BorderLayout.CENTER);

	}

//	private JPanel namePanel() {
//		JPanel panel = new JPanel();
//		panel.setLayout(new BorderLayout());
//		panel.setBorder(BorderFactory.createTitledBorder(""));
//		// create label
//		lbName = new JLabel("CHI TIẾT ĐƠN HÀNG MÃ "+ idHoaDon);
//		lbName.setHorizontalAlignment(JLabel.CENTER);
//		panel.add(lbName, BorderLayout.CENTER);
//
//		return panel;
//	}

	private void loadData(JTable table) {
		String[][] data = null;

		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setModel(tableModel);

	}

	public void updateTable(HoaDon hd) {


		String[][] data = new String[hd.getMuaHang().size()][columns.length];
		
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		for (int i = 0; i < hd.getMuaHang().size(); i++) {
				data[i][0] = hd.getMuaHang().get(i).getIdSanPham();
				data[i][1] =String.valueOf(hd.getMuaHang().get(i).getSoLuong());
				data[i][2] = format.format(hd.getMuaHang().get(i).getDonGia()).toString();

		}

		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		table.setModel(tableModel);
		tableModel.fireTableDataChanged();

	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}
}
