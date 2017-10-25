package vn.media.view.movies;

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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vn.media.models.DiaPhim;
import vn.media.models.Sach;

public class TableMoviesPanel extends JPanel{
	private JTable table;
	private JLabel lbName;
	private JScrollPane scroll;

	private String[] columns = { "ID", "Tên đĩa phim", "�?ạo diễn", "Diễn viên", "Số lượng tồn kho", "giá mua","giá bán","ngày nhập hàng cuối" };

	public TableMoviesPanel() {
		setLayout(new BorderLayout(10, 0));
		// setBorder(BorderFactory.createEtcheBorder(EtchedBorder.RAISED));
		// setBorder(BorderFactory.createEtchedBorder());
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		
		
		
		// create table
		table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		loadData(table);

		// add table to scroll
		scroll = new JScrollPane();
		
		scroll.setViewportView(table);
		
		add(scroll, BorderLayout.CENTER);
		
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
	
	private String convertListToString(List<String> list){
		String temp ="";
		for(String s: list) {
			temp = temp + s +",";
			
		}
		if(temp.equals("")) {
			return temp;
			}
		else {
			temp=temp.substring(0, temp.length()-1);
			return temp;
		}
	}

	public void updateTable(List<DiaPhim> list){
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		String[][] data =  new String[list.size()][columns.length];
		for(int i=0;i<list.size();i++){
			DiaPhim s = list.get(i);
			data[i][0] = s.getId();
			data[i][1] = s.getTenSP();
			data[i][2] = s.getDaoDien();
			data[i][3] = convertListToString(s.getDienVien());
			data[i][4] = String.valueOf(s.getSoLuongTonKho());
			data[i][5] = format.format(s.getGiaMua()).toString();
			data[i][6] = format.format(s.getGiaBan()).toString();
			data[i][7] = new SimpleDateFormat("dd/MM/yyyy HH:mm::ss").format(s.getNgayNhapHangCuoi());
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
