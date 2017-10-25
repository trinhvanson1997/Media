package vn.media.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.KhachHang;
import vn.media.models.NhanVien;
import vn.media.models.Sach;

public class DBConnector {
	private Connection conn;
	private Statement stm;
	private ResultSet rs;

	public DBConnector() {

	}
	
	/*			INITIALIZE CONNECTION TO DATABASE		*/
	private Connection getConnection() {
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Media", "postgres", "son");
			if (conn != null) {
				System.out.println("Connected !");
			}
			stm = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Connection fail !");
			e.printStackTrace();
		}
		return conn;
	}
	
	
	// return true if password is correct with username
	public boolean checkAccLogin(String username) {
		conn = getConnection();
		try {
			rs = stm.executeQuery("SELECT pass FROM account WHERE username='" + username + "';");
			System.out.println("Check account ");
			while (rs.next()) {
				if (username.equals(rs.getString("pass"))) {
					System.out.println("Check account login");
					conn.close();
					return true;
				}
			}
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("connection failed !");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	
	// return type of account is quanly,nhanvien or khachhang
	public String checkTypeAcc(String username) {
		conn = getConnection();
		String type = "";
		try {
			rs = stm.executeQuery("SELECT type FROM account WHERE username='" + username + "';");
			System.out.println("Check type account ");
			if (rs.next()) {
				type = rs.getString("type");

			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("connection failed !");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;

	}
	
	
	
	//edit pass by username
	public void editPass(String username, String newPass) {
		conn = getConnection();
		try {
			int result = stm
					.executeUpdate("UPDATE account SET pass ='" + newPass + "' WHERE username='" + username + "';");
			if (result > 0) {
				System.out.println("Edit password");
			}
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection failed !");
			e.printStackTrace();
		}
	}
	
	
	
	
	/*					STAFF							*/
	
	public List<NhanVien> getAllStaff() {
		List<NhanVien> lst = new ArrayList<>();

		try {
			conn = getConnection();

			rs = stm.executeQuery(
					"SELECT nhanvien.*,pass FROM nhanvien,account WHERE nhanvien.username=account.username;");
			while (rs.next()) {

				String id = rs.getString("id");
				String hoten = rs.getString("hoten");
				Timestamp ngaysinh = rs.getTimestamp("ngaysinh");
				String diachi = rs.getString("diachi");
				String sdt = rs.getString("sdt");
				long luong = rs.getLong("luong");
				String username = rs.getString("username");
				String password = rs.getString("pass");
				NhanVien nv = new NhanVien(id, hoten, ngaysinh, diachi, sdt, luong, username, password);
				lst.add(nv);
			}
			
			System.out.println("select all staff");
			conn.close();
			return lst;
		} catch (SQLException e) {
			System.out.println("failed to select all staff");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}
	
	public void addStaff(String id, String hoTen, Timestamp date, String diaChi, String sDT, long luong, String username,
			String password) {
		conn = getConnection();
		try {
			int kq2 = stm
					.executeUpdate("INSERT INTO account VALUES ('" + username + "','" + password + "','nhanvien')");
			int kq = stm.executeUpdate("INSERT INTO nhanvien VALUES ('" + id + "','" + hoTen + "','" + date + "','"
					+ diaChi + "','" + sDT + "','" + luong + "','" + username + "')");

			if (kq > 0 && kq2 > 0) {
				System.out.println("Insert staff to table nhanvien and table account");
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add staff");
			e.printStackTrace();
		}

	}
	
	

	public void deleteStaff(String id) {
		conn = getConnection();

		try {
			String username = "";
			rs=stm.executeQuery("SELECT username FROM nhanvien WHERE id = '" + id + "';");
		
			if(rs.next()){
				username = rs.getString("username");
			}
			System.out.println(username);
			
			//First,delete from table which contains foreign key
			int result = stm.executeUpdate("DELETE FROM nhanvien WHERE id='" + id + "';");
			
			//Then, delete from table which contains primary key
			int result1 = stm.executeUpdate("DELETE FROM account WHERE username = '" + username + "';");
			
			if (result > 0 && result1 > 0) {
				System.out.println("Delete staff");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}
	}
	
	
	// return true if ID exists
	public boolean checkExistStaff(String id) {
		conn = getConnection();

		try {

			rs = stm.executeQuery("SELECT id FROM nhanvien WHERE id='" + id + "';");

			if (rs.next()) {
				return true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	// return true if username exists
	public boolean checkExistUsername(String username) {
		conn = getConnection();

		try {

			rs = stm.executeQuery("SELECT username FROM account WHERE username='" + username + "';");

			if (rs.next()) {
				return true;
			}
			System.out.println("Check username");
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection failed !");
			e.printStackTrace();
		}
		return false;
	}


	public String getUsernameStaff(String id) {
		conn=getConnection();
		String username ="";
		try {
			rs = stm.executeQuery("SELECT username FROM nhanvien where id='"+id+"';");
			if(rs.next()) {
				 username = rs.getString("username");
				 conn.close();
				return username;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}
	
	
	
	
	/*						CUSTOMER					*/
	

	public List<KhachHang> getAllCus() {
		List<KhachHang> lst = new ArrayList<>();

		try {
			conn = getConnection();

			rs = stm.executeQuery(
					"SELECT khachhang.*,pass FROM khachhang,account WHERE khachhang.username=account.username;");
			while (rs.next()) {

				String id = rs.getString("id");
				String hoten = rs.getString("hoten");
				Timestamp ngaysinh = (rs.getTimestamp("ngaysinh"));
				String diachi = rs.getString("diachi");
				String sdt = rs.getString("sdt");
				long coin = rs.getLong("coin");
				String username = rs.getString("username");
				String password = rs.getString("pass");
				KhachHang kh = new KhachHang(id, hoten, ngaysinh, diachi, sdt, coin, username, password);
				lst.add(kh);
			}
			System.out.println("select all staff");
			conn.close();
			return lst;
		} catch (SQLException e) {
			System.out.println("failed to select all staff");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}
	
	public void addCus(String id, String hoTen, Timestamp date, String diaChi, String sDT, long coin, String username,
			String password) {
		conn = getConnection();
		try {
			int kq2 = stm
					.executeUpdate("INSERT INTO account VALUES ('" + username + "','" + password + "','khachhang')");
			int kq = stm.executeUpdate("INSERT INTO khachhang VALUES ('" + id + "','" + hoTen + "','" + date + "','"
					+ diaChi + "','" + sDT + "','" + coin + "','" + username + "')");

			if (kq > 0 && kq2 > 0) {
				System.out.println("Insert cus to table khachhang and table account");
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add customer");
			e.printStackTrace();
		}

	}
	
	
	public boolean checkExistCus(String id) {
		conn = getConnection();

		try {

			rs = stm.executeQuery("SELECT id FROM khachhang WHERE id='" + id + "';");

			if (rs.next()) {
				return true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public  String getUsernameCus(String id) {
		conn=getConnection();
		String username ="";
		try {
			rs = stm.executeQuery("SELECT username FROM khachhang where id='"+id+"';");
			if(rs.next()) {
				 username = rs.getString("username");
				 conn.close(); 
				return username;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}
	
	

	public boolean checkExistUsernameCus(String username) {
		conn = getConnection();

		try {

			rs = stm.executeQuery("SELECT username FROM khachhang WHERE username='" + username + "';");

			if (rs.next()) {
				return true;
			}
			System.out.println("Check username customer");
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection failed !");
			e.printStackTrace();
		}
		return false;
	
	}
	
	/*						MOVIES				*/
	
	public List<Sach> getAllBook() {
		conn = getConnection();
		List<Sach> list = new ArrayList<>();
		
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,nxb FROM sanpham,sach where sanpham.id=sach.id");
			
			while(rs.next()) {
				String id 		= rs.getString("id");
				String tensp	= rs.getString("tensp");
				int	soluong	 	= rs.getInt("soluongtonkho");
				long giamua 	= rs.getLong("giamua");
				long giaban 	= rs.getLong("giaban");
				Timestamp ngaynhaphang = rs.getTimestamp("ngaynhaphang");
				String nxb		= rs.getString("nxb");
				
				List<String> tacgia = getTacGia(id);
				
				Sach sach = new Sach(id, tensp, "SA", soluong, giamua, giaban, ngaynhaphang, nxb, tacgia);
				list.add(sach);
			}
			
			System.out.println("See all books");
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getTacGia(String id) throws SQLException{
		List<String> tacgia =new ArrayList<>();
		
		
		try {
			Connection c2  = conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Media", "postgres", "son");
			Statement stm2 = c2.createStatement();
			ResultSet rs2  = stm2.executeQuery("SELECT tacgia FROM sach_tacgia WHERE id='"+id+"';");
			while(rs2.next()) {
				String s = rs2.getString("tacgia");
				tacgia.add(s);
			}
			c2.close();
			return tacgia;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkExistBook(String id) {
		conn = getConnection();

		try {

			rs = stm.executeQuery("SELECT id FROM sach WHERE id='" + id + "';");

			if (rs.next()) {
				return true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void addBook(Sach sach) {
		conn = getConnection();
		String id 		= sach.getId();
		String tensp 	= sach.getTenSP();
		int soluong 	= sach.getSoLuongTonKho();
		long giamua 	= sach.getGiaMua();
		long giaban 	= sach.getGiaBan();
		Timestamp date 		= sach.getNgayNhapHangCuoi();
		String nxb 		= sach.getNhaXB();
		List<String> tacgia = sach.getTacGia();
		
		try {
			int result1 = stm.executeUpdate("INSERT INTO sanpham VALUES ('"+id+"','"+tensp+"','DP',"+soluong+
					","+giamua+","+giaban+",'"+date+"');");
			
			int result2 = stm.executeUpdate("INSERT INTO sach VALUES ('"+id+"','"+nxb+"');");
			
			for(String s:tacgia) {
			 stm.executeUpdate("INSERT INTO sach_tacgia VALUES ('"+id+"','"+s+"');");
			}
			
			if(result1>0 &&result2>0) {
				System.out.println("Add book");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to add book");
			e.printStackTrace();
		}
		
	}
	
	public void deleteBook(String id) {
		conn = getConnection();
		
		try {
			int r1 = stm.executeUpdate("DELETE FROM sach_tacgia WHERE id='"+id+"';");
			
			int r2 = stm.executeUpdate("DELETE FROM sach WHERE id='"+id+"';");
			
			int r3 = stm.executeUpdate("DELETE FROM sanpham WHERE id='"+id+"';");
			
			if(r1 >0 && r2 >0 && r3 >0) {
				System.out.println("Deleted book");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to delete book");
			e.printStackTrace();
		}
	}

	public Sach getBook(String Id) {
		conn = getConnection();
		Sach sach = new Sach();
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,nxb FROM sanpham,sach where sanpham.id=sach.id and sanpham.id='"+Id+"';");
			
			while(rs.next()) {
				String  id		= rs.getString("id");
				String tensp	= rs.getString("tensp");
				int	soluong	 	= rs.getInt("soluongtonkho");
				long giamua 	= rs.getLong("giamua");
				long giaban 	= rs.getLong("giaban");
				Timestamp ngaynhaphang = rs.getTimestamp("ngaynhaphang");
				String nxb		= rs.getString("nxb");
				
				List<String> tacgia = getTacGia(Id);
				
				 sach = new Sach(id, tensp, "SA", soluong, giamua, giaban, ngaynhaphang, nxb, tacgia);
			}
			conn.close();
			System.out.println("Get book");
			return sach;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void editBook(Sach sach) throws SQLException {
		conn = getConnection();
		String id 		= sach.getId();
		String tensp 	= sach.getTenSP();
		int soluong 	= sach.getSoLuongTonKho();
		long giamua 	= sach.getGiaMua();
		long giaban 	= sach.getGiaBan();
		Timestamp date 		= sach.getNgayNhapHangCuoi();
		String nxb 		= sach.getNhaXB();
		
		List<String> tacgia = sach.getTacGia();
		
		try {
			int result = stm.executeUpdate("UPDATE sanpham SET tensp='"+tensp+"',soluongtonkho="+soluong+","
					+ "giamua="+giamua+",giaban="+giaban+",ngaynhaphang='"+date+"' WHERE id='"+id+"';");
			
			int result2 = stm.executeUpdate("UPDATE sach SET nxb='"+nxb+"' WHERE id='"+id+"';");
			
			int r1 = stm.executeUpdate("DELETE FROM sach_tacgia WHERE id='"+id+"';");
			for(String s:tacgia) {
				System.out.println(s);
			}
			for(String tg: tacgia) {
				stm.executeUpdate("INSERT INTO sach_tacgia VALUES ('"+id+"','"+tg+"');");
			}
			
			if(result >0 && result2 >0) {
				System.out.println("Edited book");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to edit book");
			e.printStackTrace();
		}
		
	}

	
	
	
	/*						MOVIES				*/
	
	public List<DiaPhim> getAllMovies() {
		conn = getConnection();
		List<DiaPhim> list = new ArrayList<>();
		
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,daodien FROM sanpham,diaphim where sanpham.id=diaphim.id");
			
			while(rs.next()) {
				String id 		= rs.getString("id");
				String tensp	= rs.getString("tensp");
				int	soluong	 	= rs.getInt("soluongtonkho");
				long giamua 	= rs.getLong("giamua");
				long giaban 	= rs.getLong("giaban");
				Timestamp ngaynhaphang = rs.getTimestamp("ngaynhaphang");
				String daodien		= rs.getString("daodien");
				
				List<String> dienvien = getDienVien(id);
				
				DiaPhim diaphim = new DiaPhim(id, tensp, "DP", soluong, giamua, giaban, ngaynhaphang, daodien, dienvien);
				list.add(diaphim);
			}
			
			System.out.println("See all movies");
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getDienVien(String id) throws SQLException{
		List<String> dienvien =new ArrayList<>();
		
		
		try {
			Connection c2  = conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Media", "postgres", "son");
			Statement stm2 = c2.createStatement();
			ResultSet rs2  = stm2.executeQuery("SELECT dienvien FROM diaphim_dienvien WHERE id='"+id+"';");
			while(rs2.next()) {
				String s = rs2.getString("dienvien");
				dienvien.add(s);
			}
			c2.close();
			return dienvien;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkExistMovies(String id) {
		conn = getConnection();

		try {

			rs = stm.executeQuery("SELECT id FROM diaphim WHERE id='" + id + "';");

			if (rs.next()) {
				return true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void addMovies(DiaPhim diaphim) {
		conn = getConnection();
		String id 		= diaphim.getId();
		String tensp 	= diaphim.getTenSP();
		int soluong 	= diaphim.getSoLuongTonKho();
		long giamua 	= diaphim.getGiaMua();
		long giaban 	= diaphim.getGiaBan();
		Timestamp date 		= diaphim.getNgayNhapHangCuoi();
		String daodien 		= diaphim.getDaoDien();
		List<String> dienvien = diaphim.getDienVien();
		
		try {
			int result1 = stm.executeUpdate("INSERT INTO sanpham VALUES ('"+id+"','"+tensp+"','DP',"+soluong+
					","+giamua+","+giaban+",'"+date+"');");
			
			int result2 = stm.executeUpdate("INSERT INTO diaphim VALUES ('"+id+"','"+daodien+"');");
			
			for(String s:dienvien) {
			 stm.executeUpdate("INSERT INTO diaphim_dienvien VALUES ('"+id+"','"+s+"');");
			}
			
			if(result1>0 &&result2>0) {
				System.out.println("Add Movies");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to add movies");
			e.printStackTrace();
		}
		
	}
	
	public void deleteMovies(String id) {
		conn = getConnection();
		
		try {
			int r1 = stm.executeUpdate("DELETE FROM diaphim_dienvien WHERE id='"+id+"';");
			
			int r2 = stm.executeUpdate("DELETE FROM diaphim WHERE id='"+id+"';");
			
			int r3 = stm.executeUpdate("DELETE FROM sanpham WHERE id='"+id+"';");
			
			if(r1 >0 && r2 >0 && r3 >0) {
				System.out.println("Deleted movies");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to delete movies");
			e.printStackTrace();
		}
	}

	public DiaPhim getMovies(String Id) {
		conn = getConnection();
		DiaPhim diaphim = new DiaPhim();
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,daodien FROM sanpham,diaphim where sanpham.id=diaphim.id and sanpham.id='"+Id+"';");
			
			while(rs.next()) {
				String  id		= rs.getString("id");
				String tensp	= rs.getString("tensp");
				int	soluong	 	= rs.getInt("soluongtonkho");
				long giamua 	= rs.getLong("giamua");
				long giaban 	= rs.getLong("giaban");
				Timestamp ngaynhaphang = rs.getTimestamp("ngaynhaphang");
				String daodien		= rs.getString("daodien");
				
				List<String> dienvien = getDienVien(Id);
				
				 diaphim = new DiaPhim(id, tensp, "DP", soluong, giamua, giaban, ngaynhaphang, daodien, dienvien);
			}
			
			System.out.println("Get movies");
			return diaphim;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void editMovies(DiaPhim diaphim) throws SQLException {
		conn = getConnection();
		String id 		= diaphim.getId();
		String tensp 	= diaphim.getTenSP();
		int soluong 	= diaphim.getSoLuongTonKho();
		long giamua 	= diaphim.getGiaMua();
		long giaban 	= diaphim.getGiaBan();
		Timestamp date 		= diaphim.getNgayNhapHangCuoi();
		String daodien 		= diaphim.getDaoDien();
		
		List<String> dienvien = diaphim.getDienVien();
		
		try {
			int result = stm.executeUpdate("UPDATE sanpham SET tensp='"+tensp+"',soluongtonkho="+soluong+","
					+ "giamua="+giamua+",giaban="+giaban+",ngaynhaphang='"+date+"' WHERE id='"+id+"';");
			
			int result2 = stm.executeUpdate("UPDATE diaphim SET daodien='"+daodien+"' WHERE id='"+id+"';");
			
			int r1 = stm.executeUpdate("DELETE FROM diaphim_dienvien WHERE id='"+id+"';");
			
			for(String tg: dienvien) {
				stm.executeUpdate("INSERT INTO diaphim_dienvien VALUES ('"+id+"','"+tg+"');");
			
			if(result >0 && result2 >0) {
				System.out.println("Edited Movies");
			}
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to edit Movies");
			e.printStackTrace();
		}
		
	}


	
	/*						MUSIC				*/
	
	public List<DiaNhac> getAllMusic() {
		conn = getConnection();
		List<DiaNhac> list = new ArrayList<>();
		
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,theloai FROM sanpham,dianhac where sanpham.id=dianhac.id");
			
			while(rs.next()) {
				String id 		= rs.getString("id");
				String tensp	= rs.getString("tensp");
				int	soluong	 	= rs.getInt("soluongtonkho");
				long giamua 	= rs.getLong("giamua");
				long giaban 	= rs.getLong("giaban");
				Timestamp ngaynhaphang = rs.getTimestamp("ngaynhaphang");
				String theloai		= rs.getString("theloai");
				
				List<String> casi = getCaSi(id);
				
				DiaNhac dianhac = new DiaNhac(id, tensp, "DN", soluong, giamua, giaban, ngaynhaphang, theloai, casi);
				list.add(dianhac);
			}
			
			System.out.println("See all musics");
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<String> getCaSi(String id) throws SQLException{
		List<String> casi =new ArrayList<>();
		
		
		try {
			Connection c2  = conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Media", "postgres", "son");
			Statement stm2 = c2.createStatement();
			ResultSet rs2  = stm2.executeQuery("SELECT casi FROM dianhac_casi WHERE id='"+id+"';");
			while(rs2.next()) {
				String s = rs2.getString("casi");
				casi.add(s);
			}
			c2.close();
			return casi;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean checkExistMusic(String id) {
		conn = getConnection();

		try {

			rs = stm.executeQuery("SELECT id FROM dianhac WHERE id='" + id + "';");

			if (rs.next()) {
				return true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void addMusic(DiaNhac dianhac) {
		conn = getConnection();
		String id 		= dianhac.getId();
		String tensp 	= dianhac.getTenSP();
		int soluong 	= dianhac.getSoLuongTonKho();
		long giamua 	= dianhac.getGiaMua();
		long giaban 	= dianhac.getGiaBan();
		Timestamp date 		= dianhac.getNgayNhapHangCuoi();
		String theloai 		= dianhac.getTheLoai();
		List<String> casi = dianhac.getCaSi();
		
		try {
			int result1 = stm.executeUpdate("INSERT INTO sanpham VALUES ('"+id+"','"+tensp+"','SA',"+soluong+
					","+giamua+","+giaban+",'"+date+"');");
			
			int result2 = stm.executeUpdate("INSERT INTO dianhac VALUES ('"+id+"','"+theloai+"');");
			
			for(String s:casi) {
			 stm.executeUpdate("INSERT INTO dianhac_casi VALUES ('"+id+"','"+s+"');");
			}
			
			if(result1>0 &&result2>0) {
				System.out.println("Add Music");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to add music");
			e.printStackTrace();
		}
		
	}
	
	
	public void deleteMusic(String id) {
		conn = getConnection();
		
		try {
			int r1 = stm.executeUpdate("DELETE FROM dianhac_casi WHERE id='"+id+"';");
			
			int r2 = stm.executeUpdate("DELETE FROM dianhac WHERE id='"+id+"';");
			
			int r3 = stm.executeUpdate("DELETE FROM sanpham WHERE id='"+id+"';");
			
			if(r1 >0 && r2 >0 && r3 >0) {
				System.out.println("Deleted music");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to delete music");
			e.printStackTrace();
		}
	}

	public DiaNhac getMusic(String Id) {
		conn = getConnection();
		DiaNhac dianhac = new DiaNhac();
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,theloai FROM sanpham,dianhac where sanpham.id=dianhac.id and sanpham.id='"+Id+"';");
			
			while(rs.next()) {
				String  id		= rs.getString("id");
				String tensp	= rs.getString("tensp");
				int	soluong	 	= rs.getInt("soluongtonkho");
				long giamua 	= rs.getLong("giamua");
				long giaban 	= rs.getLong("giaban");
				Timestamp ngaynhaphang = rs.getTimestamp("ngaynhaphang");
				String theloai		= rs.getString("theloai");
				
				List<String> casi = getCaSi(Id);
				
				 dianhac = new DiaNhac(id, tensp, "SA", soluong, giamua, giaban, ngaynhaphang, theloai, casi);
			}
			conn.close();
			System.out.println("Get book");
			return dianhac;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void editMusic(DiaNhac dianhac) throws SQLException {
		conn = getConnection();
		String id 		= dianhac.getId();
		String tensp 	= dianhac.getTenSP();
		int soluong 	= dianhac.getSoLuongTonKho();
		long giamua 	= dianhac.getGiaMua();
		long giaban 	= dianhac.getGiaBan();
		Timestamp date 		= dianhac.getNgayNhapHangCuoi();
		String theloai 		= dianhac.getTheLoai();
		
		List<String> casi = dianhac.getCaSi();
		try {
			int result = stm.executeUpdate("UPDATE sanpham SET tensp='"+tensp+"',soluongtonkho="+soluong+","
					+ "giamua="+giamua+",giaban="+giaban+",ngaynhaphang='"+date+"' WHERE id='"+id+"';");
			
			int result2 = stm.executeUpdate("UPDATE dianhac SET theloai='"+theloai+"' WHERE id='"+id+"';");
			
			int r1 = stm.executeUpdate("DELETE FROM dianhac_casi WHERE id='"+id+"';");
			
			for(String tg: casi) {
				stm.executeUpdate("INSERT INTO dianhac_casi VALUES ('"+id+"','"+tg+"');");
			
			if(result >0 && result2 >0) {
				System.out.println("Edited music");
			}
			
			}
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to edit music");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}
