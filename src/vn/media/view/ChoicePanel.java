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
	private JButton btnHoaDon;
	private JButton btnDangXuat;
	private JButton btnThongTin;
	private JButton btnDoiMatKhau;
	private JButton btnDonHang;
	
	public ChoicePanel() {
		setLayout(new GridLayout(8, 1, 10, 10));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setBorder(BorderFactory.createTitledBorder("Danh mục"));
		btnNhanVien  =  createButtons("NHÂN VIÊN");
		btnKhachHang =  createButtons("KHÁCH HÀNG");
		btnSanPham   =  createButtons("SẢN PHẨM");
		btnHoaDon   =  createButtons("HÓA ĐƠN");
		btnDangXuat  =  createButtons("ĐĂNG XUẤT");
		btnDoiMatKhau = createButtons("ĐỔI MẬT KHẨU");
		btnThongTin = createButtons("THÔNG TIN TK");
		btnDonHang	= createButtons("ĐƠN HÀNG");
		
		add(btnSanPham);
		add(btnHoaDon);
		add(btnDonHang);
		add(btnKhachHang);
		add(btnNhanVien);
		add(btnThongTin);
		
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

	public JButton getBtnHoaDon() {
		return btnHoaDon;
	}

	public void setBtnHoaDon(JButton btnHoaDon) {
		this.btnHoaDon = btnHoaDon;
	}

	public JButton getBtnDangXuat() {
		return btnDangXuat;
	}

	public void setBtnDangXuat(JButton btnDangXuat) {
		this.btnDangXuat = btnDangXuat;
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

	public JButton getBtnDonHang() {
		return btnDonHang;
	}

	public void setBtnDonHang(JButton btnDonHang) {
		this.btnDonHang = btnDonHang;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}




	
}
