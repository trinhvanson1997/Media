package vn.media.view.movies;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vn.media.view.staff.TableStaffPanel;

public class FuncMoviesPanel extends JPanel implements ActionListener{
	private JButton btnXemTatCa;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTimKiem;
	private JButton btnRefresh;
	private JTextField tfSearch;
	private TableStaffPanel tablePanel;
	private String[] s =  { "ID", "Tên đĩa phim", "Đạo diễn", "Diễn viên", "Số lượng tồn kho", "Giá mua","Giá bán","ngày nhập hàng cuối" };
	private JComboBox<String> cbType ;
	
	
	public FuncMoviesPanel() {
		//this.tablePanel=tablePanel;
		setLayout(new BorderLayout(10,10));
		setBorder(BorderFactory.createTitledBorder("Chức năng"));
		add(createSearchPanel(),BorderLayout.NORTH);
		add(createActionPanel(),BorderLayout.CENTER);
	}
	

	private JPanel createSearchPanel() {
		btnTimKiem  = createButtons("TÌM KIẾM");
		cbType = new JComboBox<>(s);
		tfSearch = new JTextField(30);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(20,0));
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new BorderLayout(20,0));
		
		panelBtn.add(new JLabel("Tìm kiếm theo:"),BorderLayout.WEST);
		btnTimKiem.setIcon(new ImageIcon(getClass().getResource("/search.png")));
		panelBtn.add(cbType,BorderLayout.CENTER);
		panelBtn.add(btnTimKiem,BorderLayout.EAST);
		
		panel.add(new JLabel("Ô tìm kiếm:"),BorderLayout.WEST);
		panel.add(tfSearch, BorderLayout.CENTER);
		panel.add(panelBtn,BorderLayout.EAST);
		return panel;
	}
	
	private JPanel createActionPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(1, 4, 10, 10));
		
	panel.setLayout(new GridLayout(1, 5, 10, 10));
		
		btnXemTatCa = createButtons("XEM TẤT CẢ");
		btnThem     = createButtons("THÊM");
		btnSua      = createButtons("SỬA");
		btnXoa      = createButtons("XÓA");
		btnRefresh 	= createButtons("REFRESH");
		
		btnXemTatCa.setIcon(new ImageIcon(getClass().getResource("/show_all.png")));
		btnThem.setIcon(new ImageIcon(getClass().getResource("/add.png")));
		btnSua.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
		btnXoa.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
		btnRefresh.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
		
		panel.add(btnRefresh);
		panel.add(btnXemTatCa);
		panel.add(btnThem);
		panel.add(btnSua);
		panel.add(btnXoa);
		
		return panel;
	}
	
	public JButton createButtons(String name){
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public JButton getBtnXemTatCa() {
		return btnXemTatCa;
	}

	public JButton getBtnRefresh() {
		return btnRefresh;
	}


	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}
	public void setBtnXemTatCa(JButton btnXemTatCa) {
		this.btnXemTatCa = btnXemTatCa;
	}


	public JButton getBtnThem() {
		return btnThem;
	}


	public void setBtnThem(JButton btnThem) {
		this.btnThem = btnThem;
	}


	public JButton getBtnSua() {
		return btnSua;
	}


	public void setBtnSua(JButton btnSua) {
		this.btnSua = btnSua;
	}


	public JButton getBtnXoa() {
		return btnXoa;
	}


	public void setBtnXoa(JButton btnXoa) {
		this.btnXoa = btnXoa;
	}


	public JButton getBtnTimKiem() {
		return btnTimKiem;
	}


	public void setBtnTimKiem(JButton btnTimKiem) {
		this.btnTimKiem = btnTimKiem;
	}


	public JTextField getTfSearch() {
		return tfSearch;
	}


	public void setTfSearch(JTextField tfSearch) {
		this.tfSearch = tfSearch;
	}


	public TableStaffPanel getTablePanel() {
		return tablePanel;
	}


	public void setTablePanel(TableStaffPanel tablePanel) {
		this.tablePanel = tablePanel;
	}


	public JComboBox<String> getCbType() {
		return cbType;
	}


	public void setCbType(JComboBox<String> cbType) {
		this.cbType = cbType;
	}
	
}
