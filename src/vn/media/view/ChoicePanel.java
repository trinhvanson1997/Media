package vn.media.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class ChoicePanel extends JPanel implements ActionListener{
	private JButton btnNhanVien;
	private JButton btnKhachHang;
	private JButton btnSanPham;
	private JButton btnDonHang;
	private JButton btnDangXuat;
	private JButton btnThongTin;
	private JButton btnDoiMatKhau;
	
	public ChoicePanel() {
		setLayout(new GridLayout(8, 1, 10, 10));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setBorder(BorderFactory.createTitledBorder("Danh mục"));
		btnNhanVien  =  createButtons("NHÂN VIÊN");
		btnKhachHang =  createButtons("KHÁCH HÀNG");
		btnSanPham   =  createButtons("SẢN PHẨM");
		btnDonHang   =  createButtons("ĐƠN HÀNG");
		btnDangXuat  =  createButtons("ĐĂNG XUẤT");
		btnDoiMatKhau = createButtons("ĐỔI MẬT KHẨU");
		btnThongTin = createButtons("THÔNG TIN TK");
		
		add(btnSanPham);
		add(btnKhachHang);
		add(btnDonHang);
		add(btnNhanVien);
		add(btnThongTin);
		add(btnDoiMatKhau);
		add(btnDangXuat);
	}
	
	public JButton createButtons(String name){
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}

	public JButton getBtnNhanVien() {
		return btnNhanVien;
	}

	public void setBtnNhanVien(JButton btnNhanVien) {
		this.btnNhanVien = btnNhanVien;
	}

	public JButton getBtnKhachHang() {
		return btnKhachHang;
	}

	public void setBtnKhachHang(JButton btnKhachHang) {
		this.btnKhachHang = btnKhachHang;
	}

	public JButton getBtnSanPham() {
		return btnSanPham;
	}

	public void setBtnSanPham(JButton btnSanPham) {
		this.btnSanPham = btnSanPham;
	}

	public JButton getBtnThongKe() {
		return btnDonHang;
	}

	public void setBtnThongKe(JButton btnThongKe) {
		this.btnDonHang = btnThongKe;
	}

	public JButton getBtnDangXuat() {
		return btnDangXuat;
	}

	public void setBtnDangXuat(JButton btnDangXuat) {
		this.btnDangXuat = btnDangXuat;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JButton getBtnThongTin() {
		return btnThongTin;
	}

	public void setBtnThongTin(JButton btnThongTin) {
		this.btnThongTin = btnThongTin;
	}

	public JButton getBtnDoiMatKhau() {
		return btnDoiMatKhau;
	}

	public void setBtnDoiMatKhau(JButton btnDoiMatKhau) {
		this.btnDoiMatKhau = btnDoiMatKhau;
	}

	
}
