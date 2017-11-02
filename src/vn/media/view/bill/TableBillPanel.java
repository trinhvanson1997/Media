package vn.media.view.bill;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vn.media.models.HoaDon;
import vn.media.models.MuaHang;

public class TableBillPanel extends JPanel {
	private JTable table;
	private JLabel lbName;
	private JScrollPane scroll;

	private String[] columns = { "Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Mã sản phẩm", "Số lượng", "Đơn giá",
			"Ngày mua hàng" };

	public TableBillPanel() {
		setLayout(new BorderLayout(10, 0));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);

		loadData(table);

		scroll = new JScrollPane();

		scroll.setViewportView(table);
		add(namePanel(), BorderLayout.PAGE_START);
		add(scroll, BorderLayout.CENTER);

	}

	private JPanel namePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		// create label
		lbName = new JLabel("DANH SÁCH ĐƠN HÀNG");
		lbName.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lbName, BorderLayout.CENTER);

		return panel;
	}

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

	public void updateTable(List<HoaDon> list) {
		int numberRow = 0;
		for (HoaDon hd : list) {
			numberRow += hd.getMuaHang().size();
		}

		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String[][] data = new String[numberRow][columns.length];
		int row = 0;

		for (int i = 0; i < list.size(); i++) {

			HoaDon nv = list.get(i);

			for (int j = 0; j < list.get(i).getMuaHang().size(); j++) {
				data[row][0] = nv.getId();
				data[row][1] = nv.getIdKhachHang();
				data[row][2] = nv.getIdNhanVien();
				data[row][3] = list.get(i).getMuaHang().get(j).getIdSanPham();
				data[row][4] = String.valueOf(list.get(i).getMuaHang().get(j).getSoLuong());
				data[row][5] = format.format(list.get(i).getMuaHang().get(j).getDonGia()).toString();
				data[row][6] = new SimpleDateFormat("dd/MM/yyyy").format(list.get(i).getNgayMuaHang());
				row++;
			}
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
