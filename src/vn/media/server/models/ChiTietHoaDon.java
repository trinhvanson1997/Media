package vn.media.server.models;

public class ChiTietHoaDon {
	private String idHoaDon;
	private String idSanPham;
	private int soLuong;
	
	public ChiTietHoaDon() {
		// TODO Auto-generated constructor stub
	}

	public ChiTietHoaDon(String idHoaDon, String idSanPham, int soLuong) {
		super();
		this.idHoaDon = idHoaDon;
		this.idSanPham = idSanPham;
		this.soLuong = soLuong;
	}

	public String getIdHoaDon() {
		return idHoaDon;
	}

	public void setIdHoaDon(String idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	public String getIdSanPham() {
		return idSanPham;
	}

	public void setIdSanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
