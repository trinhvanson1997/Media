package vn.media.models;

import java.sql.Timestamp;
import java.util.List;

public class HoaDon {
	private String id;
	private String idKhachHang;
	private String idNhanVien;
	private Timestamp ngayMuaHang;
	private Timestamp ngayXuLy;
	private List<MuaHang> muaHang;
	
	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String id, String idKhachHang, String idNhanVien, Timestamp ngayMuaHang, Timestamp ngayXuLy,
			List<MuaHang> muaHang) {
		super();
		this.id = id;
		this.idKhachHang = idKhachHang;
		this.idNhanVien = idNhanVien;
		this.ngayMuaHang = ngayMuaHang;
		this.ngayXuLy = ngayXuLy;
		this.muaHang = muaHang;
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

	public Timestamp getNgayXuLy() {
		return ngayXuLy;
	}

	public void setNgayXuLy(Timestamp ngayXyLy) {
		this.ngayXuLy = ngayXyLy;
	}

	public List<MuaHang> getMuaHang() {
		return muaHang;
	}

	public void setMuaHang(List<MuaHang> muaHang) {
		this.muaHang = muaHang;
	}

	
	
}
