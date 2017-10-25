package vn.media.models;

import java.sql.Timestamp;
import java.util.Date;

public class Nguoi {
	protected String id;
	protected String hoTen;
	protected Timestamp ngaySinh;
	protected String diaChi;
	protected String sDT;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Timestamp getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Timestamp ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public Nguoi(String id, String hoTen, Timestamp ngaySinh, String diaChi, String sDT) {
		super();
		this.id = id;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.sDT = sDT;
	}

	public Nguoi() {
		// TODO Auto-generated constructor stub
	}




}
