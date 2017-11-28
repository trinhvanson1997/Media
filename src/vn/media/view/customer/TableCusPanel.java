package vn.media.view.customer;

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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vn.media.models.KhachHang;

public class TableCusPanel extends JPanel implements ActionListener {
	private JTable table;
	private JScrollPane scroll;
	private JLabel lbName;
	private String[] columns = { "ID", "Họ tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Coin","Username" };
	private JButton btnTrangTruoc,btnTrangSau;
	private int currentPage;
	
	public TableCusPanel() {
		setLayout(new BorderLayout(10, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// create table
		table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		loadData(table);

		// add tabel to scroll
		scroll = new JScrollPane();
		// scroll.setPreferredSize();
		scroll.setViewportView(table);
		add(scroll, BorderLayout.CENTER);
		add(namePanel(),BorderLayout.NORTH);
		add(pagePanel(),BorderLayout.SOUTH);
	}

//	public JPanel createTablePanel() {
//	
//		panel.add(scroll, BorderLayout.CENTER);
//		return panel;
//
//	}
	private JPanel namePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		//create label
				lbName = new JLabel("DANH SÁCH KHÁCH HÀNG");
				lbName.setHorizontalAlignment(JLabel.CENTER);
			panel.add(lbName,BorderLayout.CENTER);
			
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

	public void updateTable(List<KhachHang> list){
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		String[][] data =  new String[list.size()][columns.length];
		for(int i=0;i<list.size();i++){
			KhachHang nv = list.get(i);
			data[i][0] = nv.getId();
			data[i][1] =nv.getHoTen();
			data[i][2] =new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh());
			data[i][3] =nv.getDiaChi();
			data[i][4] =nv.getsDT();
			data[i][5] =format.format(nv.getCoin()).toString();
			data[i][6] = nv.getUsername();
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
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setBtnTrangSau(JButton btnTrangSau) {
		this.btnTrangSau = btnTrangSau;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
