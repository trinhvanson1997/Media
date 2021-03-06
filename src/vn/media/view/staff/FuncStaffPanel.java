package vn.media.view.staff;

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

public class FuncStaffPanel extends JPanel implements ActionListener{
	private JButton btnXemTatCa;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTimKiem;
	private JButton btnRefresh;
	private JButton btnLuong;
	
	private JTextField tfSearch;
	private TableStaffPanel tablePanel;
	private String[] s = {"ID","Họ tên","Ngày sinh","Địa chỉ","Số điện thoại","Lương","Username"};
	private JComboBox<String> cbType ;
	
	public FuncStaffPanel() {
		//this.tablePanel=tablePanel;
		setLayout(new BorderLayout(10,10));
		setBorder(BorderFactory.createTitledBorder("Chức năng"));
		add(createSearchPanel(),BorderLayout.NORTH);
		add(createActionPanel(),BorderLayout.CENTER);
	}
	

	private JPanel createSearchPanel() {
		btnTimKiem  = createButtons("TÌM KIẾM");
		btnTimKiem.setIcon(new ImageIcon(getClass().getResource("/search.png")));
		cbType = new JComboBox<>(s);
		tfSearch = new JTextField(30);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(20,0));
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new BorderLayout(20,0));
		panelBtn.add(new JLabel("Tìm kiếm theo:"),BorderLayout.WEST);
		panelBtn.add(cbType,BorderLayout.CENTER);
		panelBtn.add(btnTimKiem,BorderLayout.EAST);
		
		panel.add(new JLabel("Ô tìm kiếm:"),BorderLayout.WEST);
		panel.add(tfSearch, BorderLayout.CENTER);
		panel.add(panelBtn,BorderLayout.EAST);
		return panel;
	}
	
	private JPanel createActionPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(1, 6, 10, 10));
		
		btnXemTatCa = createButtons("XEM TẤT CẢ");
		btnThem     = createButtons("THÊM");
		btnSua      = createButtons("ĐỔI MẬT KHẨU");
		btnXoa      = createButtons("XÓA");
		btnRefresh 	= createButtons("REFRESH");
		btnLuong 	= createButtons("SỬA LƯƠNG");
		
		btnXemTatCa.setIcon(new ImageIcon(getClass().getResource("/show_all.png")));
		btnThem.setIcon(new ImageIcon(getClass().getResource("/add.png")));
		btnSua.setIcon(new ImageIcon(getClass().getResource("/edit.png")));
		btnXoa.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
		btnRefresh.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
		
		panel.add(btnRefresh);
		panel.add(btnXemTatCa);
		panel.add(btnThem);
		panel.add(btnSua);
		panel.add(btnLuong);
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


	public JButton getBtnRefresh() {
		return btnRefresh;
	}


	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}


	public JButton getBtnXemTatCa() {
		return btnXemTatCa;
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


	public JButton getBtnLuong() {
		return btnLuong;
	}


	public void setBtnLuong(JButton btnLuong) {
		this.btnLuong = btnLuong;
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
