package vn.media.serialization;

import java.io.Serializable;
import java.sql.Timestamp;

public class HistoryWait implements Serializable{
	
	private String name;
	private int soLuong;
	private Timestamp ngayMua;
	private String trangthai;
	public HistoryWait(String name, int soLuong, Timestamp ngayMua, String trangthai) {
		super();
		this.name = name;
		this.soLuong = soLuong;
		this.ngayMua = ngayMua;
		this.trangthai = trangthai;
	}
	public String getName() {
		return name;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public Timestamp getNgayMua() {
		return ngayMua;
	}
	public String getTrangthai() {
		return trangthai;
	}
	

	
	
}
