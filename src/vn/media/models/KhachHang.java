package vn.media.models;

import java.sql.Timestamp;

public class KhachHang extends Nguoi{
	private long coin;
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


	public KhachHang() {
		// TODO Auto-generated constructor stub
	}


	public KhachHang(String id, String hoTen, Timestamp ngaySinh, String diaChi, String sDT, long coin,
			String username,String password) {
		super(id, hoTen, ngaySinh, diaChi, sDT);
		this.coin    = coin;
		this.username = username;
		this.password = password;
	}


	


	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}
}
