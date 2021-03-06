package vn.media.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.Fee;
import vn.media.models.HoaDon;
import vn.media.models.KhachHang;
import vn.media.models.MuaHang;
import vn.media.models.NhanVien;
import vn.media.models.Paid;
import vn.media.models.Sach;
import vn.media.serialization.HistoryWait;

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
	public boolean checkAccLogin(String username,String pass) {
		conn = getConnection();
		try {
			rs = stm.executeQuery("SELECT pass FROM account WHERE username='" + username + "';");
		
			if (rs.next()) {
			
				
				
				if (pass.equals(rs.getString("pass"))) {
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

	public boolean checkSerial(String serial) {
		conn =getConnection();
		
		try {
			rs = stm.executeQuery("SELECT status FROM card WHERE serial = '"+serial+"';");
			if(rs.next()) {
				if(rs.getInt(1)==1) {
					return true;
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public long getValueCard(String serial) {
		conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT value FROM card WHERE serial = '"+serial+"';");
			if(rs.next()) {
				return rs.getLong(1);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void updateStatusCard(String serial) {
		conn = getConnection();
		
		try {
			int r = stm.executeUpdate("UPDATE card SET status ="+0+" WHERE serial = '"+serial+"';");
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
	
	public void editSalary(String username, long newSalary) {
		conn = getConnection();
		try {
			int result = stm
					.executeUpdate("UPDATE nhanvien SET luong =" + newSalary + " WHERE username='" + username + "';");
			if (result > 0) {
				System.out.println("Edit salary");
			}
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection failed !");
			e.printStackTrace();
		}
	}
	
	
	/*					STAFF							*/
	
	public List<NhanVien> getAllStaff(int page) {
		List<NhanVien> lst = new ArrayList<>();
		conn = getConnection();
		try {
			

			rs = stm.executeQuery(
					"SELECT nhanvien.*,pass FROM nhanvien,account WHERE nhanvien.username=account.username and nhanvien.trangthai =1 LIMIT 20 OFFSET "+page*20+";");
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
					+ diaChi + "','" + sDT + "','" + luong + "','" + username + "', 1)");

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
		
			int result = stm.executeUpdate("update nhanvien set trangthai = 0 WHERE id='" + id + "';");
			
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
	
	public NhanVien getStaff(String username) {
		conn = getConnection();
	
		try {
			

			rs = stm.executeQuery(
					"SELECT nhanvien.*,pass FROM nhanvien,account WHERE nhanvien.username=account.username and nhanvien.username='"+username+"';");
			if(rs.next()) {

				String id = rs.getString("id");
				String hoten = rs.getString("hoten");
				Timestamp ngaysinh = rs.getTimestamp("ngaysinh");
				String diachi = rs.getString("diachi");
				String sdt = rs.getString("sdt");
				long luong = rs.getLong("luong");
				String password = rs.getString("pass");
				NhanVien nv = new NhanVien(id, hoten, ngaysinh, diachi, sdt, luong, username, password);
				return nv;
			}
			conn.close();
			return null;
		} catch (SQLException e) {
			System.out.println("failed to select all staff");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}
	
	public void editStaff(String id, String hoTen, Timestamp date, String diaChi, String sDT, long luong, String username,
			String password) {
		conn = getConnection();
		try {
			int kq2 = stm
					.executeUpdate("UPDATE account SET pass='"+password+"' WHERE username = '"+username+"' ;");
			int kq = stm.executeUpdate("UPDATE nhanvien SET hoten = '"+hoTen+"', ngaysinh='"+date+"',"
					+ " diachi = '"+diaChi+"', sdt = '"+sDT+"', luong = "+luong+" WHERE username = '"+username+"' ;");

			if (kq > 0 && kq2 > 0) {
				System.out.println("edit staff to table nhanvien and table account");
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add staff");
			e.printStackTrace();
		}
	}
	
	public String getIDStaff(String username) {
		conn=getConnection();
		String id ="";
		try {
			rs = stm.executeQuery("SELECT id FROM nhanvien where username='"+username+"';");
			if(rs.next()) {
				 id = rs.getString("id");
				 conn.close();
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public int getCountStaff() {
		conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT COUNT(*) FROM nhanvien;");
			if(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	
	
	
	/*						CUSTOMER					*/
	

	public List<KhachHang> getAllCus(int page) {
		List<KhachHang> lst = new ArrayList<>();

		try {
			conn = getConnection();

			rs = stm.executeQuery(
					"SELECT khachhang.*,pass FROM khachhang,account WHERE khachhang.username=account.username  LIMIT 20 OFFSET "+page*20+";");
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
			System.out.println("select all customer");
			conn.close();
			return lst;
		} catch (SQLException e) {
			System.out.println("failed to select all staff");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}
	
	public boolean addCus(String id, String hoTen, Timestamp date, String diaChi, String sDT, long coin, String username,
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
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add customer");
			e.printStackTrace();
		}
return false;
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
	
	
	public String getCusName(String username) {
		conn = getConnection();
		try {
			rs = stm.executeQuery("SELECT hoten FROM khachhang WHERE username='"+username+"';");
			if(rs.next()) {
				conn.close();
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public long getCoinCus(String id) {
		conn = getConnection();
		try {
			rs = stm.executeQuery("SELECT coin FROM khachhang WHERE id='"+id+"';");
			if(rs.next()) {
				conn.close();
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	public void updateCoin(String username,long coin) {
		conn = getConnection();
		
		try {
			System.out.println("coin db: "+ coin);
			int r = stm.executeUpdate("UPDATE khachhang SET coin = "+coin+" WHERE username = '"+username+"';");
			conn.close();
		} catch (SQLException e) {
			System.out.println("UPDATE coin from db");
			e.printStackTrace();
		}
	}
	
	public void editCustomer(String id, String hoTen, Timestamp date, String diaChi, String sDT, long coin, String username,
			String password) {
		conn = getConnection();
		try {
			int kq2 = stm
					.executeUpdate("UPDATE account SET pass='"+password+"' WHERE username = '"+username+"' ;");
			int kq = stm.executeUpdate("UPDATE khachhang SET hoten = '"+hoTen+"', ngaysinh='"+date+"',"
					+ " diachi = '"+diaChi+"', sdt = '"+sDT+"', coin = "+coin+" WHERE username = '"+username+"' ;");

			if (kq > 0 && kq2 > 0) {
				System.out.println("edit cus to table khachhang and table account");
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to add customer");
			e.printStackTrace();
		}
	}
	
	
	public KhachHang getCus(String username) {
		conn = getConnection();
	
		try {
			

			rs = stm.executeQuery(
					"SELECT khachhang.*,pass FROM khachhang,account WHERE khachhang.username=account.username and khachhang.username='"+username+"';");
			if(rs.next()) {

				String id = rs.getString("id");
				String hoten = rs.getString("hoten");
				Timestamp ngaysinh = rs.getTimestamp("ngaysinh");
				String diachi = rs.getString("diachi");
				String sdt = rs.getString("sdt");
				long luong = rs.getLong("coin");
				String password = rs.getString("pass");
				KhachHang nv = new KhachHang(id, hoten, ngaysinh, diachi, sdt, luong, username, password);
				return nv;
			}
			conn.close();
			return null;
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}
	
	public String getIDCus(String username) {
		conn=getConnection();
		String id ="";
		try {
			rs = stm.executeQuery("SELECT id FROM khachhang where username='"+username+"';");
			if(rs.next()) {
				 id = rs.getString("id");
				 conn.close();
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public int getCountCus() {
conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT COUNT(*) FROM khachhang;");
			if(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getNameProduct(String id) {
		conn  = getConnection();
		try {
			rs = stm.executeQuery("SELECT tensp FROM sanpham WHERE id = '"+id+"' ;");
			if(!rs.next())
			{	conn.close();
				return null;}
			conn.close();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/*						MOVIES				*/
	
	public List<Sach> getAllBook(int page) {
		conn = getConnection();
		List<Sach> list = new ArrayList<>();
		
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,nxb FROM sanpham,sach where sanpham.id=sach.id LIMIT 20 OFFSET "+page*20+";");
			
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
	
	public List<Sach> getBookByNameAndPublisher(String name,String publisher){
		List<Sach> list = new ArrayList<>();
		conn = getConnection();
		try {
			rs = stm.executeQuery(""
					+ "SELECT sanpham.*,nxb FROM sanpham,sach WHERE lower(tensp) = '"+name+"' and sanpham.id = sach.id and ("+convertNXBToSQL(publisher)+");");
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
			conn.close();
			System.out.println("Advanced Search Book !");
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Sach> getBookByNameAndPublisherOp(String name,String publisher){
		List<Sach> list = new ArrayList<>();
		conn = getConnection();
		try {
			rs = stm.executeQuery(""
					+ "SELECT * FROM sanpham WHERE lower(tensp) = '"+name+"' and id IN (SELECT id FROM sach WHERE "+convertNXBToSQL(publisher)+");");
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
			conn.close();
			System.out.println("Advanced Search Book !");
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Sach> getBookByNameAndAuthor(String name,String author){
		List<Sach> list = new ArrayList<>();
		conn = getConnection();
		try {
			rs = stm.executeQuery(""
					+ "SELECT sanpham.*,nxb FROM sanpham,sach,sach_tacgia WHERE lower(tensp) = '"+name+"' and sanpham.id = sach.id and "
							+ " sach.id = sach_tacgia.id and ("+convertTacGiaToSQL(author)+");");
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
			conn.close();
			System.out.println("Advanced Search Book !");
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Sach> getBookByPublisherAndAuthor(String publisher,String author){
		List<Sach> list = new ArrayList<>();
		conn = getConnection();
		try {
			rs = stm.executeQuery(""
					+ "SELECT sanpham.*,nxb FROM sanpham,sach,sach_tacgia WHERE  sanpham.id = sach.id and "
							+ " sach.id = sach_tacgia.id and ("+convertNXBToSQL(publisher) +") and ("+convertTacGiaToSQL(author)+");");
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
			conn.close();
			System.out.println("Advanced Search Book !");
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Sach> getBookByNameAndPublisherAndAuthor(String name,String publisher,String author){
		List<Sach> list = new ArrayList<>();
		conn = getConnection();
		try {
			rs = stm.executeQuery(""
					+ "SELECT sanpham.*,nxb FROM sanpham,sach,sach_tacgia WHERE  sanpham.id = sach.id and "
							+ " sach.id = sach_tacgia.id and lower(tensp) = '"+name+"' and ("+convertNXBToSQL(publisher) +") and ("+convertTacGiaToSQL(author)+");");
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
			conn.close();
			System.out.println("Advanced Search Book !");
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Sach> getBookByNameOrPublisherOrAuthor(String name, String publisher, String author) {
		List<Sach> list = new ArrayList<>();
	
		conn = getConnection();
		try {
			rs = stm.executeQuery(""
					+ "SELECT sanpham.*,nxb FROM sanpham,sach,sach_tacgia WHERE  sanpham.id = sach.id and "
							+ " sach.id = sach_tacgia.id and (lower(tensp) = '"+name+"' or ("+convertNXBToSQL(publisher) +") or ("+convertTacGiaToSQL(author)+"));");
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
			conn.close();
			System.out.println("Advanced Search Book !");
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String convertNXBToSQL(String s) {
		if(s.equals("")) {
			return "lower(nxb) = ''";
		}
		else {
			String rs = "lower(nxb) = '";
			for(int i=0;i<s.length();i++) {
				if(s.charAt(i) == ',') {
					rs+= "' or lower(nxb) = '";
				}
				else rs += s.charAt(i);
			}
			rs += "'";
			return rs;
		}
	}
	
	public String convertTacGiaToSQL(String s) {
		if(s.equals("")) {
			return "lower(tacgia) = ''";
		}
		else {
		String rs = "lower(tacgia) = '";
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == ',') {
				rs+= "' or lower(tacgia) = '";
			}
			else rs += s.charAt(i);
		}
		rs += "'";
		return rs;
		}
	}
	
	
	public int getCountBook() {
conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT COUNT(*) FROM sach;");
			if(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<String> getTacGia(String id) throws SQLException{
		List<String> tacgia =new ArrayList<>();
		
		
		try {
			Connection c2   = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Media", "postgres", "son");
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
			int result1 = stm.executeUpdate("INSERT INTO sanpham VALUES ('"+id+"','"+tensp+"','SA',"+soluong+
					","+giamua+","+giaban+",'"+date+"');");
			
			int result2 = stm.executeUpdate("INSERT INTO sach VALUES ('"+id+"','"+nxb+"');");
			
			for(String s:tacgia) {
			 stm.executeUpdate("INSERT INTO sach_tacgia VALUES ('"+id+"','"+s+"');");
			}
			
			if(result1>0 &&result2>0) {
				System.out.println("Add book ");
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

	public void editBook(Sach sach)  {
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
	
	public List<DiaPhim> getAllMovies(int page) {
		conn = getConnection();
		List<DiaPhim> list = new ArrayList<>();
		
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,daodien FROM sanpham,diaphim where sanpham.id=diaphim.id LIMIT 20 OFFSET "+page*20+";");
			
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
	
	public int getCountMovies() {
conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT COUNT(*) FROM diaphim;");
			if(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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

	public void editMovies(DiaPhim diaphim)  {
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
	
	public List<DiaNhac> getAllMusic(int page) {
		conn = getConnection();
		List<DiaNhac> list = new ArrayList<>();
		
		try {
			rs 	 = stm.executeQuery("SELECT sanpham.*,theloai FROM sanpham,dianhac where sanpham.id=dianhac.id LIMIT 20 OFFSET "+page*20+";");
			
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
	
	public int getCountMusic() {
conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT COUNT(*) FROM dianhac;");
			if(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
			int result1 = stm.executeUpdate("INSERT INTO sanpham VALUES ('"+id+"','"+tensp+"','DN',"+soluong+
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
	
	
	
	/*									BILL										*/
	
	public List<HoaDon> getAllBill(int page) {
		conn = getConnection();
		List<HoaDon> listHD = new ArrayList<>();
		try {
			rs = stm.executeQuery("SELECT * FROM hoadon WHERE trangthai = 'Hoàn tất' ORDER BY ngayxuly DESC LIMIT 20 OFFSET "+20*page+";");
			while(rs.next()) {
				String idhoadon 	= rs.getString("id");
				String idkhachhang 	= rs.getString("idkhachhang");
				String idnhanvien 	= rs.getString("idnhanvien");
				Timestamp ngaymua	= rs.getTimestamp("ngaymua");
				Timestamp ngayxuly 	= rs.getTimestamp("ngayxuly");
				
				HoaDon hd = new HoaDon(idhoadon, idkhachhang, idnhanvien, ngaymua, ngayxuly, getListMuaHang(idhoadon));
				listHD.add(hd);
			}
			conn.close();
			return listHD;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getCountBill() {
conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT COUNT(*) FROM hoadon WHERE trangthai = 'Hoàn tất';");
			if(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public List<MuaHang> getListMuaHang(String idhoadon){
		
		List<MuaHang > listMH = new ArrayList<>();
		try {
			Connection c2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Media", "postgres", "son");
			Statement stm2;
			ResultSet rs2;
			 stm2 = c2.createStatement();
		
			
			rs2 = stm2.executeQuery("SELECT chitiethoadon.*,giaban FROM chitiethoadon,sanpham WHERE chitiethoadon.idsanpham= sanpham.id and "
					+ "chitiethoadon.idhoadon = '"+idhoadon+"';");
			while(rs2.next()) {
				String idsanpham 	= rs2.getString("idsanpham");
				int soluong			= rs2.getInt("soluong");
				long dongia 		= rs2.getLong("giaban");
				
				MuaHang mh = new MuaHang( idsanpham, soluong, dongia);
				listMH.add(mh);
			}
			c2.close();
			stm2.close();
			rs2.close();
			return listMH;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public boolean addBill(String idhoadon, String idkhachhang, String idnhanvien,Timestamp date,Timestamp date2, List<MuaHang> listMH) {
		conn = getConnection();
		
		try {
			stm.executeUpdate("INSERT INTO hoadon VALUES ('"+idhoadon+"','"+idkhachhang+"','"+idnhanvien+"','"+date+"','"+date2+"','Hoàn tất') ;");
			for(int i=0;i<listMH.size();i++) {
				System.out.println(listMH.get(i).getIdSanPham());
			stm.executeUpdate("INSERT INTO chitiethoadon VALUES ('"+idhoadon+"' , '"+listMH.get(i).getIdSanPham()+"' , "+listMH.get(i).getSoLuong()+");");
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public HoaDon getBill(String idHoaDon) {
		conn = getConnection();
		HoaDon hd =null;
		try {
			rs = stm.executeQuery("SELECT * FROM hoadon where id = '"+idHoaDon+"';");
			if(rs.next()) {
				String idhoadon 	= rs.getString("id");
				String idkhachhang 	= rs.getString("idkhachhang");
				String idnhanvien 	= rs.getString("idnhanvien");
				Timestamp ngaymua	= rs.getTimestamp("ngaymua");
				Timestamp ngayxuly 	= rs.getTimestamp("ngayxuly");
				
				 hd = new HoaDon(idhoadon, idkhachhang, idnhanvien, ngaymua,ngayxuly, getListMuaHang(idhoadon));
				conn.close();
				return hd;
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	
	public int getSoLuongTonKho(String id) {
		conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT soluongtonkho FROM sanpham WHERE id='"+id+"';");
			if(rs.next()) {
				conn.close();
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void updateNumberProduct(String id,int soluong) {
		conn = getConnection();
		
		try {
			stm.executeUpdate("UPDATE sanpham SET soluongtonkho = "+soluong+" WHERE id ='"+id+"' ;");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean addWait(String idhoadon, String idkhachhang,Timestamp date,List<MuaHang> listMH) {
		conn = getConnection();
		
		try {
			stm.executeUpdate("INSERT INTO hoadon VALUES ('"+idhoadon+"','"+idkhachhang+"','NV0','"+date+"',"+null+",'Đang xử lý') ;");
			for(int i=0;i<listMH.size();i++) {
				System.out.println(listMH.get(i).getIdSanPham());
			stm.executeUpdate("INSERT INTO chitiethoadon VALUES ('"+idhoadon+"' , '"+listMH.get(i).getIdSanPham()+"' , "+listMH.get(i).getSoLuong()+");");
			}
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean addWait(String idhoadon, String idkhachhang, String idnhanvien, Timestamp date1,Date date2,MuaHang listMH) {
		conn = getConnection();
		Timestamp t = new Timestamp(date2.getTime());
		try {
			stm.executeUpdate("INSERT INTO hoadon VALUES ('"+idhoadon+"','"+idkhachhang+"','"+idnhanvien+"','"+date1+"','"+t+"','Hoàn tất') ;");
		
				
			stm.executeUpdate("INSERT INTO chitiethoadon VALUES ('"+idhoadon+"' , '"+listMH.getIdSanPham()+"' , "+listMH.getSoLuong()+");");
			
			System.out.println("Add bill test");
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	public List<HoaDon> getAllWait(int page) {
		conn = getConnection();
		List<HoaDon> listHD = new ArrayList<>();
		try {
			rs = stm.executeQuery("SELECT * FROM hoadon WHERE trangthai = 'Đang xử lý'  ORDER BY ngaymua DESC LIMIT 20 OFFSET "+20*page+";");
			while(rs.next()) {
				String idhoadon 	= rs.getString("id");
				String idkhachhang 	= rs.getString("idkhachhang");
				String idnhanvien 	= rs.getString("idnhanvien");
				Timestamp ngaymua	= rs.getTimestamp("ngaymua");
				
				HoaDon hd = new HoaDon(idhoadon, idkhachhang, idnhanvien, ngaymua, null, getListMuaHang(idhoadon));
				listHD.add(hd);
			}
			conn.close();
			return listHD;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getCountWait() {
conn = getConnection();
		
		try {
			rs = stm.executeQuery("SELECT COUNT(*) FROM hoadon WHERE trangthai = 'Đang xử lý';");
			if(rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void handlingWait(String idnhanvien, String idhoadon) {
		conn = getConnection();
		Timestamp date = new Timestamp(new Date().getTime());
		
		try {
			stm.executeUpdate("UPDATE hoadon SET idnhanvien = '"+idnhanvien+"', trangthai = 'Hoàn tất', ngayxuly = '"+date+"' WHERE id ='"+idhoadon+"' ;  ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*								FEE							*/
	
	public boolean addFee(Fee fee) {
		conn = getConnection();
		try {
			String query;

			query = "INSERT INTO fee VALUES ('" + fee.getFeeName() + "',"
					+ fee.getFeeValue() + "," + fee.getFeeCycle() + ",'" + fee.getLastRequest() + "');";
			stm.executeUpdate(query);
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addPaid(Paid paid) {
		conn = getConnection();
		try {
			String query;
			if (paid.getPaidTime() != null) {
				query = "insert into paid values ('" + paid.getID() + "','"
						+ paid.getFeeName() + "'," + paid.getStatus() + ",'" + paid.getRequestTime() + "','"
						+ paid.getPaidTime() + "',"+paid.getFeeValue()+");";
				stm.executeUpdate(query);
			} else {
				query = "insert into paid(id,feename,status,requesttime,feevalue) values ('" + paid.getID() + "','"
						+ paid.getFeeName() + "'," + paid.getStatus() + ",'" + paid.getRequestTime() + "',"+paid.getFeeValue()+");";
				stm.executeUpdate(query);
			}
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Fee> getAllFee(int page) {
		conn = getConnection();
		List<Fee> result = new ArrayList<>();
		try {
			String query = "select * from fee LIMIT 20 OFFSET "+page*20+";";
			rs = stm.executeQuery(query);
			while (rs.next()) {
				String feeName = rs.getString("feename");
				long feeValue = rs.getLong("feevalue");
				int feeCycle = rs.getInt("feecycle");
				Timestamp time = rs.getTimestamp("requesttime");
				result.add(new Fee(feeName, feeValue, feeCycle, time));
			}
			System.out.println("get all fees");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Fee getFee(String feeName) {
		conn = getConnection();
		try {
			String query = "select * from fee where feeName='" + feeName + "';";
			rs = stm.executeQuery(query);
			if (!rs.next())
				return null;
			String feename = rs.getString("feename");
			long feevalue = rs.getLong("feevalue");
			int feeCycle = rs.getInt("feecycle");
			Timestamp time = rs.getTimestamp("requesttime");
			
			System.out.println("Get fee from DB");
			conn.close();
			return new Fee(feename, feevalue, feeCycle, time);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateFee(Fee bk) {
		conn =  getConnection();
		try {
			String query = "update fee set feename='" + bk.getFeeName() + "'," + " feevalue=" + bk.getFeeValue() + ","
					+ "  feecycle=" + bk.getFeeCycle() + "," + "requesttime='" + bk.getLastRequest() + "'"
					+ " where feename='" + bk.getFeeName() + "';";
			stm.executeUpdate(query);
			conn.close();
			System.out.println("update fee");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getCountFee() {
		conn = getConnection();
				
				try {
					rs = stm.executeQuery("SELECT COUNT(*) FROM fee;");
					if(rs.next()) {
						return rs.getInt("count");
					}
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
	}
	
	public int getCountPaid() {
		conn = getConnection();
				
				try {
					rs = stm.executeQuery("SELECT COUNT(*) FROM paid;");
					if(rs.next()) {
						return rs.getInt("count");
					}
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
	}
	
	
	public void deleteFee(String feeName) {
		conn = getConnection();
		try {
			String query = "delete from paid where feename='" + feeName + "';";
			stm.executeUpdate(query);
			query = "delete from fee where feename='" + feeName + "';";
			stm.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<HistoryWait> getHistory(String idkhachhang) {
		conn = getConnection();
		List<HistoryWait> list = new ArrayList<>();
		
		String sql = "select sp.tensp,cthd.soluong,hd.ngaymua,hd.trangthai " + 
				"from sanpham as sp,chitiethoadon as cthd,hoadon as hd " + 
				"where sp.id = cthd.idsanpham and cthd.idhoadon = hd.id and hd.idkhachhang = '"+idkhachhang+"' "
				+ "order by hd.ngaymua desc;";
		try {
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("tensp");
				int soluong = rs.getInt("soluong");
				Timestamp date = rs.getTimestamp("ngaymua");
				String trangthai = rs.getString("trangthai");
				
				HistoryWait h =  new HistoryWait(name, soluong, date, trangthai);
				list.add(h);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	public List<Fee> getAllFee() {
		conn = getConnection();
		List<Fee> result = new ArrayList<>();
		try {
			String query = "select * from fee;";
			rs = stm.executeQuery(query);
			while (rs.next()) {
				String feeName = rs.getString("feename");
				long feeValue = rs.getLong("feevalue");
				int feeCycle = rs.getInt("feecycle");
				Timestamp time = rs.getTimestamp("requesttime");
				result.add(new Fee(feeName, feeValue, feeCycle, time));
			}
			System.out.println("get all fees");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Paid> getAllPaid(int page) {
		conn = getConnection();
		List<Paid> result = new ArrayList<>();
		try {
			String query = "select * from paid order by status asc LIMIT 20 OFFSET "+page*20+";";
			rs = stm.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				String feeName = rs.getString("feename");
				int status = rs.getInt("status");
				long feeValue = rs.getLong("feevalue");
				Timestamp requestTime = rs.getTimestamp("requesttime");
				Timestamp paidTime = rs.getTimestamp("paidtime");
				
				result.add(new Paid(id, feeName, status, feeValue, requestTime, paidTime));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Paid getPaid(String id) {
		conn = getConnection();
		try {
			String query = "select * from paid where id ='" + id + "';";
			rs = stm.executeQuery(query);
			if (!rs.next())
				return null;
			String feename = rs.getString("feeName");
			long feevalue = rs.getLong("feeValue");
			int status = rs.getInt("status");
			Timestamp request = rs.getTimestamp("requestTime");
			Timestamp paidti = rs.getTimestamp("paidTime");
			conn.close();
			return new Paid(id, feename, status, feevalue, request, paidti);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void paidPaid(Paid paid) {
		conn = getConnection();
		try {
			Date time = new Date();
			@SuppressWarnings("deprecation")
		
			String query = "update paid set status=1 " + ",requesttime='" + paid.getRequestTime() + "',"
					+ " paidtime='" + new Timestamp(time.getTime()) + "'" + " where id='" + paid.getID() + "';";
			stm.executeUpdate(query);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Date getMinDateBill(Date one) {
		conn = getConnection();
		Timestamp time1 = new Timestamp(one.getYear(), one.getMonth(), one.getDate(), 0, 0, 0, 0);
		
		try {
			String query="select min(ngayxuly) from hoadon where ngayxuly>='"+time1+"' and trangthai = 'Hoàn tất';";
			rs=stm.executeQuery(query);
			if(rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				conn.close();
				return new Date(time.getYear(), time.getMonth(), time.getDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Date getMaxDateBill(Date one) {
		conn = getConnection();
		Timestamp time1 = new Timestamp(one.getYear(), one.getMonth(), one.getDate(), 0, 0, 0, 0);
		try {
			String query="select max(ngayxuly) from hoadon where ngayxuly<='"+time1+"' and trangthai = 'Hoàn tất';";
			rs=stm.executeQuery(query);
			if(rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				conn.close();
				return new Date(time.getYear(), time.getMonth(), time.getDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date getMinDateBillByMonth(Date one) {
		conn = getConnection();
		Timestamp time1 = new Timestamp(one.getYear(), one.getMonth(), 1, 0, 0, 0, 0);
	
		try {
			String query="select min(ngayxuly) from hoadon where ngayxuly>='"+time1+"' and trangthai = 'Hoàn tất';";
			rs=stm.executeQuery(query);
			if(rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				conn.close();
				return new Date(time.getYear(), time.getMonth(), time.getDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Date getMaxDateBillByMonth(Date one) {
		conn = getConnection();
		Timestamp time1 = new Timestamp(one.getYear(), one.getMonth(), 31, 0, 0, 0, 0);
		try {
			String query="select max(ngayxuly) from hoadon where ngayxuly<='"+time1+"' and trangthai = 'Hoàn tất';";
			rs=stm.executeQuery(query);
			if(rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				conn.close();
				return new Date(time.getYear(), time.getMonth(), time.getDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date getMinDateBillByYear(Date one) {
		conn = getConnection();
		Timestamp time1 = new Timestamp(one.getYear(), 1,1, 0, 0, 0, 0);
		System.out.println(time1);
		try {
			String query="select min(ngayxuly) from hoadon where ngayxuly>='"+time1+"' and trangthai = 'Hoàn tất';";
			rs=stm.executeQuery(query);
			if(rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				conn.close();
				return new Date(time.getYear(), time.getMonth(), time.getDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Date getMaxDateBillByYear(Date one) {
		conn = getConnection();
		Timestamp time1 = new Timestamp(one.getYear(),12,31 ,0, 0, 0, 0);
		try {
			String query="select max(ngayxuly) from hoadon where ngayxuly<='"+time1+"' and trangthai = 'Hoàn tất';";
			rs=stm.executeQuery(query);
			if(rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				conn.close();
				return new Date(time.getYear(), time.getMonth(), time.getDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Date getMinDateBill() {
		conn = getConnection();
		try {
			String query="select min(ngayxuly) from hoadon where trangthai = 'Hoàn tất';";
			rs=stm.executeQuery(query);
			if(rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				conn.close();
				return new Date(time.getYear(), time.getMonth(), time.getDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Date getMaxDateBill() {
		conn = getConnection();
		
		try {
			String query="select max(ngayxuly) from hoadon where trangthai = 'Hoàn tất';";
			rs=stm.executeQuery(query);
			if(rs.next()) {
				Timestamp time = rs.getTimestamp(1);
				conn.close();
				return new Date(time.getYear(), time.getMonth(), time.getDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long getDoanhThu(Date one,Date two) {
		conn = getConnection();
		
		Timestamp time1 = new Timestamp(one.getYear(), one.getMonth(), one.getDate(), 0, 0, 0, 0);
		Timestamp time2 = new Timestamp(two.getYear(), two.getMonth(), two.getDate(), 23, 59, 59, 999999999);
		System.out.println("time 1: "+ time1);
		System.out.println("time 2: "+ time2);
		try {
			String query ="select sum(soluong*(sanpham.giaban)) from chitiethoadon,sanpham,hoadon " + 
					"where  sanpham.id = chitiethoadon.idsanpham  and hoadon.id = chitiethoadon.idhoadon "
					+"and hoadon.ngayxuly >='"+time1+"' and hoadon.ngayxuly <='"+time2+ "';";

			rs=stm.executeQuery(query);
			
			if(rs.next()) 
				{
				System.out.println("doanh thu: "+rs.getLong(1));
				conn.close();
				return rs.getLong(1);}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public long getLoiNhuan(Date one,Date two) {
		conn = getConnection();
	
		Timestamp time1 = new Timestamp(one.getYear(), one.getMonth(), one.getDate(), 0, 0, 0, 0);
		Timestamp time2 = new Timestamp(two.getYear(), two.getMonth(), two.getDate(), 23, 59, 59, 999999999);
		try {
			long productmoney = 0,feemoney = 0;
		
					
			String query ="select sum(soluong*(sanpham.giaban-sanpham.giamua)) from chitiethoadon,sanpham,hoadon " + 
					"where  sanpham.id = chitiethoadon.idsanpham  and hoadon.id = chitiethoadon.idhoadon "
					+"and hoadon.ngayxuly >='"+time1+"' and hoadon.ngayxuly <='"+time2+ "';";
			rs=stm.executeQuery(query);
			
			if(rs.next()) productmoney= rs.getLong(1);
			
			
			query = "select sum(feevalue) from paid where status= 1 "+"and paidtime >='"+time1+"' and paidtime <='"+time2+ "'; ";
			rs=stm.executeQuery(query);
			if(rs.next()) feemoney= rs.getLong(1);
			
			conn.close();
			return productmoney-feemoney;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long getDoanhThuTheoThang(Date one) {
		conn = getConnection();
		
		Timestamp time1 = new Timestamp(one.getYear(), one.getMonth(), 1, 0, 0, 0, 0);
		Timestamp time2 = new Timestamp(one.getYear(), one.getMonth(),31, 23, 59,59 ,999999999);
		System.out.println("time 1: "+ time1);
		System.out.println("time 2: "+ time2);
		try {
			String query ="select sum(soluong*(sanpham.giaban)) from chitiethoadon,sanpham,hoadon " + 
					"where  sanpham.id = chitiethoadon.idsanpham  and hoadon.id = chitiethoadon.idhoadon "
					+"and hoadon.ngayxuly >='"+time1+"' and hoadon.ngayxuly <='"+time2+ "';";

			rs=stm.executeQuery(query);
			
			if(rs.next()) 
				{
				System.out.println("doanh thu: "+rs.getLong(1));
				conn.close();
				return rs.getLong(1);}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public long getLoiNhuanTheoThang(Date one) {
		conn = getConnection();
		
		Timestamp time1 = new Timestamp(one.getYear(), one.getMonth(), 1, 0, 0, 0, 0);
		Timestamp time2 = new Timestamp(one.getYear(), one.getMonth(),31, 23, 59,59 ,999999999);
		try {
			long productmoney = 0,feemoney = 0;
		
					
			String query ="select sum(soluong*(sanpham.giaban-sanpham.giamua)) from chitiethoadon,sanpham,hoadon " + 
					"where  sanpham.id = chitiethoadon.idsanpham  and hoadon.id = chitiethoadon.idhoadon "
					+"and hoadon.ngayxuly >='"+time1+"' and hoadon.ngayxuly <='"+time2+ "';";
			rs=stm.executeQuery(query);
			
			if(rs.next()) productmoney= rs.getLong(1);
			
			
			query = "select sum(feevalue) from paid where status= 1 "+"and paidtime >='"+time1+"' and paidtime <='"+time2+ "'; ";
			rs=stm.executeQuery(query);
			if(rs.next()) feemoney= rs.getLong(1);
			
			conn.close();
			return productmoney-feemoney;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long getDoanhThuTheoNam(Date one) {
		conn = getConnection();
		
		Timestamp time1 = new Timestamp(one.getYear(),0, 1, 0, 0, 0, 0);
		Timestamp time2 = new Timestamp(one.getYear(),11, 31, 23, 59,59 ,999999999);
		System.out.println("time 1: "+ time1);
		System.out.println("time 2: "+ time2);
		try {
			String query ="select sum(soluong*(sanpham.giaban)) from chitiethoadon,sanpham,hoadon " + 
					"where  sanpham.id = chitiethoadon.idsanpham  and hoadon.id = chitiethoadon.idhoadon "
					+"and hoadon.ngayxuly >='"+time1+"' and hoadon.ngayxuly <='"+time2+ "';";

			rs=stm.executeQuery(query);
			
			if(rs.next()) 
				{
				System.out.println("doanh thu: "+rs.getLong(1));
				conn.close();
				return rs.getLong(1);}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public long getLoiNhuanTheoNam(Date one) {
		conn = getConnection();
	
		Timestamp time1 = new Timestamp(one.getYear(), 0, 1, 0, 0, 0, 0);
		Timestamp time2 = new Timestamp(one.getYear(), 11, 31, 23, 59,59 ,999999999);
		try {
			long productmoney = 0,feemoney = 0;
		
					
			String query ="select sum(soluong*(sanpham.giaban-sanpham.giamua)) from chitiethoadon,sanpham,hoadon " + 
					"where  sanpham.id = chitiethoadon.idsanpham  and hoadon.id = chitiethoadon.idhoadon "
					+"and hoadon.ngayxuly >='"+time1+"' and hoadon.ngayxuly <='"+time2+ "';";
			rs=stm.executeQuery(query);
			
			if(rs.next()) productmoney= rs.getLong(1);
			
			
			query = "select sum(feevalue) from paid where status= 1 "+"and paidtime >='"+time1+"' and paidtime <='"+time2+ "'; ";
			rs=stm.executeQuery(query);
			if(rs.next()) feemoney= rs.getLong(1);
			
			conn.close();
			return productmoney-feemoney;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

/*ublic static void main(String[] args) {
	DBConnector db = new DBConnector();

	System.out.println(db.getNameProduct("SA2904"));
	
}*/

	
}
