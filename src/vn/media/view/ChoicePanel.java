package vn.media.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	private JButton btnThongKe;
	private JButton btnDonHang;
	private JButton btnChiPhi;
	
	public ChoicePanel() {
		setLayout(new GridLayout(9, 1, 10, 10));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setBorder(BorderFactory.createTitledBorder("Danh mục"));
		btnNhanVien  =  createButtons("NHÂN VIÊN ");
		btnKhachHang =  createButtons("KHÁCH HÀNG");
		btnSanPham   =  createButtons("SẢN PHẨM  ");
		btnHoaDon    =  createButtons("HÓA ĐƠN   ");
		btnDangXuat  =  createButtons("ĐĂNG XUẤT ");
		btnThongTin  =  createButtons("THÔNG TIN TK");
		btnDonHang	 =  createButtons("ĐƠN HÀNG  ");
		btnChiPhi 	 = 	createButtons("CHI PHÍ   ");
		btnThongKe 	 =  createButtons("THỐNG KÊ");
		
		btnNhanVien.setIcon(new ImageIcon(getClass().getResource("/staff.png")));
		btnKhachHang.setIcon(new ImageIcon(getClass().getResource("/customer.png")));
		btnSanPham.setIcon(new ImageIcon(getClass().getResource("/product.png")));
		btnHoaDon.setIcon(new ImageIcon(getClass().getResource("/bill.png")));
		btnDangXuat.setIcon(new ImageIcon(getClass().getResource("/log_out.png")));
		btnChiPhi.setIcon(new ImageIcon(getClass().getResource("/fee.png")));
		btnThongTin.setIcon(new ImageIcon(getClass().getResource("/info.png")));
		btnDonHang.setIcon(new ImageIcon(getClass().getResource("/don_hang.png")));
		btnThongKe.setIcon(new ImageIcon(getClass().getResource("/statis.png")));
		
		add(btnSanPham);
		add(btnHoaDon);
		add(btnDonHang);
		add(btnKhachHang);
		add(btnThongKe);
		add(btnChiPhi);
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

	public JButton getBtnChiPhi() {
		return btnChiPhi;
	}

	public void setBtnChiPhi(JButton btnChiPhi) {
		this.btnChiPhi = btnChiPhi;
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



	public JButton getBtnThongKe() {
		return btnThongKe;
	}

	public void setBtnThongKe(JButton btnThongKe) {
		this.btnThongKe = btnThongKe;
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
