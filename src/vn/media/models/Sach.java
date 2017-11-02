package vn.media.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Sach extends SanPham implements Serializable{
	private String nhaXB;
	private List<String> tacGia;
	
	public Sach() {
		// TODO Auto-generated constructor stub
	}

	public Sach(String id, String tenSP, String maLoaiSP, int soLuongTonKho, long giaMua, long giaBan,
			Timestamp ngayNhapHangCuoi, String nhaXB, List<String> tacGia) {
		super(id,tenSP,maLoaiSP,soLuongTonKho,giaMua,giaBan,ngayNhapHangCuoi);
		this.nhaXB = nhaXB;
		this.tacGia = tacGia;
	}

	public String getNhaXB() {
		return nhaXB;
	}

	public void setNhaXB(String nhaXB) {
		this.nhaXB = nhaXB;
	}

	public List<String> getTacGia() {
		return tacGia;
	}

	public void setTacGia(List<String> tacGia) {
		this.tacGia = tacGia;
	}
	
	
}
