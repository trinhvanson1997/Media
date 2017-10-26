package vn.media.server.models;

public class MuaHang {
	private String idHoaDon;
	private String idSanPham;
	private int soLuong;
	private long donGia;
	
	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}

	public MuaHang() {
		// TODO Auto-generated constructor stub
	}

	

	public MuaHang(String idHoaDon, String idSanPham, int soLuong, long donGia) {
		super();
		this.idHoaDon = idHoaDon;
		this.idSanPham = idSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
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
