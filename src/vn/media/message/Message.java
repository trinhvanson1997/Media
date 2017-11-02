package vn.media.message;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	
	String idhoadon;
	String idkhachhang;
	String idnhanvien;
	String idsanpham;
	int soluong;
	long dongia;
	Timestamp ngaymua;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIdhoadon() {
		return idhoadon;
	}
	public void setIdhoadon(String idhoadon) {
		this.idhoadon = idhoadon;
	}
	public String getIdkhachhang() {
		return idkhachhang;
	}
	public void setIdkhachhang(String idkhachhang) {
		this.idkhachhang = idkhachhang;
	}
	public String getIdnhanvien() {
		return idnhanvien;
	}
	public void setIdnhanvien(String idnhanvien) {
		this.idnhanvien = idnhanvien;
	}
	public String getIdsanpham() {
		return idsanpham;
	}
	public void setIdsanpham(String idsanpham) {
		this.idsanpham = idsanpham;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public long getDongia() {
		return dongia;
	}
	public void setDongia(long dongia) {
		this.dongia = dongia;
	}
	public Timestamp getNgaymua() {
		return ngaymua;
	}
	public void setNgaymua(Timestamp ngaymua) {
		this.ngaymua = ngaymua;
	}
	public Message(String title, String idhoadon, String idkhachhang, String idnhanvien, String idsanpham, int soluong,
			long dongia, Timestamp ngaymua) {
		super();
		this.title = title;
		this.idhoadon = idhoadon;
		this.idkhachhang = idkhachhang;
		this.idnhanvien = idnhanvien;
		this.idsanpham = idsanpham;
		this.soluong = soluong;
		this.dongia = dongia;
		this.ngaymua = ngaymua;
	}
	
	
	

	
}
