package vn.media.view.fee;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vn.media.common.IOFile;
import vn.media.controller.DBConnector;
import vn.media.models.Fee;
import vn.media.models.Paid;
import vn.media.models.Store;
import vn.media.view.MainFrame;

@SuppressWarnings("serial")
public class PaidFeeView extends JDialog implements ActionListener {
	private Store store;
	private JButton bnThanhToan;
	private DBConnector db;
	private TableFeePanel tableFeePanel;
	private JTable table;
	private JScrollPane scroll;
	private JButton btNext, btBack;
	private MainFrame mainFrame;
	private String[] columns = { "ID", "Tên chi phí", "Giá trị", "Ngày yêu cầu", "Ngày thanh toán", "Trạng thái" };
	IOFile io = new IOFile();
	
	public PaidFeeView(DBConnector db, MainFrame main, TableFeePanel tableFeePanel, Store store) {
		this.db = db;
		this.store = store;
		this.tableFeePanel = tableFeePanel;
		this.mainFrame = main;
		setSize(1000, 600);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Các chi phí");
		setLayout(new BorderLayout(10, 0));
		
		table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loadData(table);

		// add table to scroll
		scroll = new JScrollPane();
		scroll.setViewportView(table);

		// bt
		btNext = new JButton("Trang sau");
		btBack = new JButton("Trang trước");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(btNext, BorderLayout.EAST);
		panel.add(btBack, BorderLayout.WEST);
		bnThanhToan = new JButton("Thanh Toán");
		bnThanhToan.addActionListener(this);
		panel.add(bnThanhToan, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		add(scroll, BorderLayout.CENTER);
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		namePanel.add(new JLabel("CÁC CHI PHÍ CẦN THANH TOÁN"));
		add(namePanel,BorderLayout.NORTH);
		setVisible(true);
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

	public void updateTable(List<Paid> list) {
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String[][] data = new String[list.size()][columns.length];

		for (int i = 0; i < list.size(); i++) {
			Paid s = list.get(i);
			data[i][0] = s.getID();
			data[i][1] = s.getFeeName();
			data[i][2] = format.format(s.getFeeValue()).toString();
			data[i][3] = new SimpleDateFormat("dd/MM/yyyy HH:mm::ss").format(s.getRequestTime()).toString();
			if (s.getPaidTime() == null) {
				data[i][4] = "null";
			} else
				data[i][4] =new SimpleDateFormat("dd/MM/yyyy HH:mm::ss").format(s.getPaidTime()).toString();
			
			if(s.getStatus() == 0) {
				data[i][5] = "Chưa thanh toán";
			}
			else {
				data[i][5] = "Đã thanh toán";
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btBack) {
			if(mainFrame.getPagePaid()>0) {
				
				List<Paid> list = db.getAllPaid(mainFrame.getPagePaid()-1);
				mainFrame.setPagePaid(mainFrame.getPagePaid()-1);
				updateTable(list);
			}
		}
		else if(e.getSource()==btNext) {
			int page = mainFrame.getPagePaid();
			if(page<db.getCountPaid()/20) {
				page=page+1;
				mainFrame.setPagePaid(page);
				List<Paid> list = db.getAllPaid(page);
				updateTable(list);
			}
		}
		else if (e.getSource() == bnThanhToan) {
			int index = this.getTable().getSelectedRow();

			if (index >= 0) {
				String id = this.getTable().getModel().getValueAt(index, 0).toString();
				String status =this.getTable().getModel().getValueAt(index, 5).toString();
				
				if(status.equals("Chưa thanh toán")) {
					int a = JOptionPane.showConfirmDialog(this, "Bạn muốn thanh toán chi phí với id: '" + id + "'?",
							"Cảnh báo", JOptionPane.YES_NO_OPTION);
					if (a == JOptionPane.YES_OPTION) {
						Paid paid = db.getPaid(id);
						db.paidPaid(paid);
						store.setTotalMoney(store.getTotalMoney() - paid.getFeeValue());
						io.writeFile();
						
						DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));
					
						List<Paid> list = db.getAllPaid(mainFrame.getPagePaid());
						this.updateTable(list);
						JOptionPane.showMessageDialog(null, "Thanh toán thành công");

					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Chi phí này đã được thanh toán trước đó,Vui lòng chọn chi phí khác !", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn chi phí cần thanh toán !", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public JButton getBnThanhToan() {
		return bnThanhToan;
	}

	

	public void setBnThanhToan(JButton bnThanhToan) {
		this.bnThanhToan = bnThanhToan;
	}

	public DBConnector getDb() {
		return db;
	}

	public void setDb(DBConnector db) {
		this.db = db;
	}

	public TableFeePanel getTableFeePanel() {
		return tableFeePanel;
	}

	public void setTablefeePanel(TableFeePanel tablefeePanel) {
		this.tableFeePanel = tablefeePanel;
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

	public JButton getBtNext() {
		return btNext;
	}

	public void setBtNext(JButton btNext) {
		this.btNext = btNext;
	}

	public JButton getBtBack() {
		return btBack;
	}

	public void setBtBack(JButton btBack) {
		this.btBack = btBack;
	}

}
