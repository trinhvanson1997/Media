package vn.media.view.wait;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vn.media.models.HoaDon;

public class TableWaitPanel extends JPanel implements ActionListener {
	private JTable table;
	private JLabel lbName;
	private JScrollPane scroll;
	private JButton btnTrangTruoc,btnTrangSau;
	private String[] columns = { "Mã hóa đơn", "Mã khách hàng","Ngày đặt hàng", "Trạng thái"};
	private int currentPage;
	
	public TableWaitPanel() {
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
		add(pagePanel(),BorderLayout.SOUTH);
	}

	private JPanel namePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		// create label
		lbName = new JLabel("DANH SÁCH ĐƠN HÀNG");
		lbName.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lbName, BorderLayout.CENTER);

		return panel;
	}

	private JPanel pagePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		btnTrangTruoc = new JButton("Trang Trước");		btnTrangTruoc.addActionListener(this);
		btnTrangSau   = new JButton("Trang Sau");		btnTrangSau.addActionListener(this);
		
		panel.add(btnTrangTruoc, BorderLayout.WEST);
		panel.add(btnTrangSau, BorderLayout.EAST);
		
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
		//int numberRow = 0;
//		for (HoaDon hd : list) {
//			numberRow += hd.getMuaHang().size();
//		}

		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String[][] data = new String[list.size()][columns.length];
		int row = 0;

		for (int i = 0; i < list.size(); i++) {
				data[i][0] = list.get(i).getId();
				data[i][1] = list.get(i).getIdKhachHang();
				data[i][2] = new SimpleDateFormat("dd/MM/yyyy HH:mm::ss").format(list.get(i).getNgayMuaHang());
				data[i][3] = "Đang chờ xử lý";
			
			
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
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JLabel getLbName() {
		return lbName;
	}

	public void setLbName(JLabel lbName) {
		this.lbName = lbName;
	}

	public JButton getBtnTrangTruoc() {
		return btnTrangTruoc;
	}

	public void setBtnTrangTruoc(JButton btnTrangTruoc) {
		this.btnTrangTruoc = btnTrangTruoc;
	}

	public JButton getBtnTrangSau() {
		return btnTrangSau;
	}

	public void setBtnTrangSau(JButton btnTrangSau) {
		this.btnTrangSau = btnTrangSau;
	}
}