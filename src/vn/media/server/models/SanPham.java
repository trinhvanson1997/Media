package vn.media.server.models;

import java.sql.Timestamp;

public class SanPham {
	public static int indexOfStaff;
	public static int indexOfCus;
	public static int indexOfBook;
	public static int indexOfMovies;
	public static int indexOfMusic;
	public static int indexOfBill;
	
	protected String id;
	protected String tenSP;
	protected String maLoaiSP;
	protected int soLuongTonKho;
	protected long giaMua;
	protected long giaBan;
	protected Timestamp ngayNhapHangCuoi; 
	
	public SanPham() {
		// TODO Auto-generated constructor stub
	}
	

	public SanPham(String id, String tenSP, String maLoaiSP, int soLuongTonKho, long giaMua, long giaBan,
			Timestamp ngayNhapHangCuoi) {
		super();
		this.id = id;
		this.tenSP = tenSP;
		this.maLoaiSP = maLoaiSP;
		this.soLuongTonKho = soLuongTonKho;
		this.giaMua = giaMua;
		this.giaBan = giaBan;
		this.ngayNhapHangCuoi = ngayNhapHangCuoi;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMaLoaiSP() {
		return maLoaiSP;
	}

	public void setMaLoaiSP(String maLoaiSP) {
		this.maLoaiSP = maLoaiSP;
	}

	public int getSoLuongTonKho() {
		return soLuongTonKho;
	}

	public void setSoLuongTonKho(int soLuongTonKho) {
		this.soLuongTonKho = soLuongTonKho;
	}

	public long getGiaMua() {
		return giaMua;
	}

	public void setGiaMua(long giaMua) {
		this.giaMua = giaMua;
	}

	public long getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(long giaBan) {
		this.giaBan = giaBan;
	}

	public Timestamp getNgayNhapHangCuoi() {
		return ngayNhapHangCuoi;
	}

	public void setNgayNhapHangCuoi(Timestamp ngayNhapHangCuoi) {
		this.ngayNhapHangCuoi = ngayNhapHangCuoi;
	}
	
	
}
