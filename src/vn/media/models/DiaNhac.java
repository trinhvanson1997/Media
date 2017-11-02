package vn.media.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DiaNhac extends SanPham implements Serializable{
	private String theLoai;
	private List<String> caSi;
	
	public DiaNhac() {
		// TODO Auto-generated constructor stub
	}

	public DiaNhac(String id, String tenSP, String maLoaiSP, int soLuongTonKho, long giaMua, long giaBan,
			Timestamp ngayNhapHangCuoi, String theLoai, List<String> caSi) {
		super(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi);
		this.theLoai = theLoai;
		this.caSi = caSi;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public List<String> getCaSi() {
		return caSi;
	}

	public void setCaSi(List<String> caSi) {
		this.caSi = caSi;
	}
	 
	
}
