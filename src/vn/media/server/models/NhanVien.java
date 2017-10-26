package vn.media.server.models;

import java.sql.Timestamp;

public class NhanVien extends Nguoi {
	private long   luong;
	private String username;
	private String password;
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public NhanVien() {
		// TODO Auto-generated constructor stub
	}


	public NhanVien(String id, String hoTen, Timestamp ngaySinh, String diaChi, String sDT, long luong,
			String username,String password) {
		super(id, hoTen, ngaySinh, diaChi, sDT);
		this.luong    = luong;
		this.username = username;
		this.password = password;
	}


	


	public long getLuong() {
		return luong;
	}

	public void setLuong(long luong) {
		this.luong = luong;
	}

}
