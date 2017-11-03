package vn.media.view.customer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FuncCusPanel extends JPanel implements ActionListener{
	private JButton btnXemTatCa;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTimKiem;
	private JButton btnRefresh;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	private String[] s={"ID","Họ tên","Ngày sinh","Địa chỉ","Số điện thoại","Coin","Username"};
	
	public FuncCusPanel() {
		
		
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
		panelBtn.add(cbType,BorderLayout.CENTER);
		panelBtn.add(btnTimKiem,BorderLayout.EAST);
		
		panel.add(new JLabel("Ô tìm kiếm:"),BorderLayout.WEST);
		panel.add(tfSearch, BorderLayout.CENTER);
		panel.add(panelBtn,BorderLayout.EAST);
		return panel;
	}
	
	private JPanel createActionPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(1, 3, 50, 10));
		
		btnXemTatCa = createButtons("XEM TẤT CẢ");
		btnSua      = createButtons("ĐỔI MẬT KHẨU");
btnRefresh 	= createButtons("REFRESH");
		
		panel.add(btnRefresh);
		
		
		panel.add(btnXemTatCa);
		
		panel.add(btnSua);
		
		
		return panel;
	}
	public JButton createButtons(String name){
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public JButton getBtnXemTatCa() {
		return btnXemTatCa;
	}

	public JButton getBtnTimKiem() {
		return btnTimKiem;
	}
	public JButton getBtnRefresh() {
		return btnRefresh;
	}


	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}
	public void setBtnTimKiem(JButton btnTimKiem) {
		this.btnTimKiem = btnTimKiem;
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
	public JTextField getTfSearch() {
		return tfSearch;
	}
	public void setTfSearch(JTextField tfSearch) {
		this.tfSearch = tfSearch;
	}
	public JComboBox<String> getCbType() {
		return cbType;
	}
	public void setCbType(JComboBox<String> cbType) {
		this.cbType = cbType;
	}
	
}
