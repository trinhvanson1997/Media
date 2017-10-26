package vn.media.server.models;

import java.sql.Timestamp;

public class HoaDon {
	private String id;
	private String idKhachHang;
	private String idNhanVien;
	private Timestamp ngayMuaHang;
	
	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String id, String idKhachHang, String idNhanVien, Timestamp ngayMuaHang) {
		super();
		this.id = id;
		this.idKhachHang = idKhachHang;
		this.idNhanVien = idNhanVien;
		this.ngayMuaHang = ngayMuaHang;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdKhachHang() {
		return idKhachHang;
	}

	public void setIdKhachHang(String idKhachHang) {
		this.idKhachHang = idKhachHang;
	}

	public String getIdNhanVien() {
		return idNhanVien;
	}

	public void setIdNhanVien(String idNhanVien) {
		this.idNhanVien = idNhanVien;
	}

	public Timestamp getNgayMuaHang() {
		return ngayMuaHang;
	}

	public void setNgayMuaHang(Timestamp ngayMuaHang) {
		this.ngayMuaHang = ngayMuaHang;
	}
	
	
}
