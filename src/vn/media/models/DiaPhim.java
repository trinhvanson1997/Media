package vn.media.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DiaPhim extends SanPham implements Serializable{
	private String daoDien;
	private List<String> dienVien;
	
	public DiaPhim() {
		// TODO Auto-generated constructor stub
	}
	
	public DiaPhim(String id, String tenSP, String maLoaiSP, int soLuongTonKho, long giaMua, long giaBan,
			Timestamp ngayNhapHangCuoi,String daoDien, List<String> dienVien) {
		super(id,tenSP,maLoaiSP,soLuongTonKho,giaMua,giaBan,ngayNhapHangCuoi);
		this.daoDien = daoDien;
		this.dienVien = dienVien;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public List<String> getDienVien() {
		return dienVien;
	}

	public void setDienVien(List<String> dienVien) {
		this.dienVien = dienVien;
	}
	
	
}
