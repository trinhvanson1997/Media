package vn.media.models;

import java.io.Serializable;

public class MuaHang implements Serializable{
	
	
	private String idSanPham;
	private int soLuong;
	private long donGia;
	
	public MuaHang() {
		// TODO Auto-generated constructor stub
	}

	public MuaHang(String idSanPham, int soLuong, long donGia) {
		super();
		this.idSanPham = idSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public String getIdSanPham() {
		return idSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public long getDonGia() {
		return donGia;
	}
	
	
	
}
