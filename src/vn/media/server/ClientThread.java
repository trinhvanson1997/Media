package vn.media.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.media.common.IOFile;
import vn.media.controller.DBConnector;
import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.KhachHang;
import vn.media.models.MuaHang;
import vn.media.models.Sach;
import vn.media.models.SanPham;
import vn.media.serialization.HistoryWait;
import vn.media.view.LoginBox;

public class ClientThread extends Thread{
	public static final int LOGIN=1,LOGIN_SUCCESS=2,LOGIN_FAIL=3,GET_ALL_BOOK=4,GET_ALL_MOVIE=5
			,GET_ALL_MUSIC=6,GET_CUSTOMER_NAME=7,GET_COIN_CUS=8,GET_SLTK=9,UPDATE_COIN=10
					,UPDATE_NUMBER_PRODUCT=11,UPDATE_CUSTOMER_INFO=12
					,GET_CUSTOMER=13,CHECK_SERIAL=14,GET_VALUE_CARD=15,CHECK_EXIST_USERNAME=16
							,ADD_CUSTOMER=17,CLOSE_REQUEST=18,ORDER_REQUEST=19
							,COUNT_BOOK=20,COUNT_MOVIE=21,COUNT_MUSIC=22,SERVER_CLOSE=23,GET_HISTORY=24;
	public Socket socket;
	public Server server;
	public DBConnector db;
	public LoginBox login;
	
	public DataInputStream in;
	public DataOutputStream out;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	

	public ClientThread(LoginBox login,Socket socket,DBConnector db,Server server)  {
		this.socket=socket;
		this.server=server;
		this.db = db;
		this.login = login;
		
		try {
			in = new DataInputStream(this.socket.getInputStream());
			out = new DataOutputStream(this.socket.getOutputStream());
			oos = new ObjectOutputStream(this.socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	public void run() {
		while(true) {
			try {
				int stt=in.readInt();
				System.out.println("stt : "+stt);
				if(stt==LOGIN) {
					String username = in.readUTF();
					String password = in.readUTF();
					
					if(db.checkAccLogin(username, password)==true && db.checkTypeAcc(username).equals("khachhang")) out.writeInt(LOGIN_SUCCESS);
					else out.writeInt(LOGIN_FAIL);
					
				}
				else if(stt == GET_ALL_BOOK) {
					int page = in.readInt();
					
					List<Sach> list = db.getAllBook(page);
					
					out.writeInt(list.size());
					out.flush();
					
					for(int i=0;i<list.size();i++) {
						oos.writeObject(list.get(i).getTacGia());
						oos.flush();
						
						out.writeUTF(list.get(i).getId());
						out.flush();
						
						out.writeUTF(list.get(i).getTenSP());
						out.flush();
						
						out.writeUTF(list.get(i).getMaLoaiSP());
						out.flush();
						
						out.writeInt(list.get(i).getSoLuongTonKho());
						out.flush();
						
						out.writeLong(list.get(i).getGiaMua());
						out.flush();
						
						out.writeLong(list.get(i).getGiaBan());
						out.flush();
						
						out.writeUTF(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(list.get(i).getNgayNhapHangCuoi()));
						out.flush();
						
						out.writeUTF(list.get(i).getNhaXB());
						out.flush();
						
						
					}
				}
				else if(stt == GET_ALL_MOVIE) {
					int page = in.readInt();
					
					List<DiaPhim> list = db.getAllMovies(page);
					
					out.writeInt(list.size());
					out.flush();
					
					for(int i=0;i<list.size();i++) {
						oos.writeObject(list.get(i).getDienVien());
						oos.flush();
						
						out.writeUTF(list.get(i).getId());
						out.flush();
						
						out.writeUTF(list.get(i).getTenSP());
						out.flush();
						
						out.writeUTF(list.get(i).getMaLoaiSP());
						out.flush();
						
						out.writeInt(list.get(i).getSoLuongTonKho());
						out.flush();
						
						out.writeLong(list.get(i).getGiaMua());
						out.flush();
						
						out.writeLong(list.get(i).getGiaBan());
						out.flush();
						
						out.writeUTF(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(list.get(i).getNgayNhapHangCuoi()));
						out.flush();
						
						out.writeUTF(list.get(i).getDaoDien());
						out.flush();
						
						
					}
				}
				else if(stt == GET_ALL_MUSIC) {
					int page = in.readInt();
					
					List<DiaNhac> list = db.getAllMusic(page);
					
					out.writeInt(list.size());
					out.flush();
					
					for(int i=0;i<list.size();i++) {
						oos.writeObject(list.get(i).getCaSi());
						oos.flush();
						
						out.writeUTF(list.get(i).getId());
						out.flush();
						
						out.writeUTF(list.get(i).getTenSP());
						out.flush();
						
						out.writeUTF(list.get(i).getMaLoaiSP());
						out.flush();
						
						out.writeInt(list.get(i).getSoLuongTonKho());
						out.flush();
						
						out.writeLong(list.get(i).getGiaMua());
						out.flush();
						
						out.writeLong(list.get(i).getGiaBan());
						out.flush();
						
						out.writeUTF(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(list.get(i).getNgayNhapHangCuoi()));
						out.flush();
						
						out.writeUTF(list.get(i).getTheLoai());
						out.flush();
						
						
					}
				}
				else if(stt == GET_CUSTOMER_NAME) {
					String username = in.readUTF();
					String name = db.getCusName(username);
					
					out.writeUTF(name);
				}
				else if(stt == GET_COIN_CUS) {
					String id = in.readUTF();
					long coin = db.getCoinCus(id);
					
					out.writeLong(coin);
				}
				else if(stt == GET_SLTK) {
					String id = in.readUTF();
					
					int soluong = db.getSoLuongTonKho(id);
					out.writeInt(soluong);
					out.flush();
				}
				else if(stt == UPDATE_COIN) {
					String username = in.readUTF();
					
					long newCoin = in.readLong();
					
					db.updateCoin(username, newCoin);
				}
				else if(stt == UPDATE_NUMBER_PRODUCT) {
					String idsanpham = in.readUTF();
					int soluong = in.readInt();
					
					db.updateNumberProduct(idsanpham, soluong);
					
				}
				else if(stt == UPDATE_CUSTOMER_INFO) {
					try {
						KhachHang kh = (KhachHang) ois.readObject();
						System.out.println(kh.getNgaySinh());
						db.editCustomer(kh.getId(), kh.getHoTen(),
								kh.getNgaySinh(), kh.getDiaChi(), kh.getsDT(), kh.getCoin(), 
								kh.getUsername(), kh.getPassword());
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(stt == GET_CUSTOMER) {
					String username = in.readUTF();
					
					KhachHang kh = db.getCus(username);
					oos.writeObject(kh);
					oos.flush();
					
				}
				else if(stt == CHECK_SERIAL) {
					String serial = in.readUTF();
					System.out.println("check serial "+db.checkSerial(serial));
					if(db.checkSerial(serial)) {
						db.updateStatusCard(serial);
						out.writeBoolean(true);
						out.flush();
					}
					else {
						out.writeBoolean(false);
						out.flush();
					}
				}
				else if(stt == GET_VALUE_CARD) {
					String serial = in.readUTF();
					
					long value = db.getValueCard(serial);
					out.writeLong(value);
					out.flush();
				}
				else if(stt == CHECK_EXIST_USERNAME) {
					String username = in.readUTF();
					
					if(db.checkExistUsername(username)) {
						out.writeBoolean(true);
						out.flush();
					}
					else {
						out.writeBoolean(false);
						out.flush();
					}
				}
				else if(stt == ADD_CUSTOMER) {
					KhachHang kh = (KhachHang) ois.readObject();
					
					
					if(db.addCus("KH"+new SanPham().indexOfCus, kh.getHoTen(),
							kh.getNgaySinh(), kh.getDiaChi(), kh.getsDT(), kh.getCoin(), 
							kh.getUsername(), kh.getPassword())) {
						new SanPham().indexOfCus++;
						new IOFile().writeFile()
;						out.writeBoolean(true);
						out.flush();
					}
					else {
						out.writeBoolean(false);
						out.flush();
					}
				}
				else if(stt == CLOSE_REQUEST) {
					socket.close();
					this.stop();
				}
				else if(stt == ORDER_REQUEST) {
					String idkhachhang = in.readUTF();
			
					int Size = in.readInt();
					System.out.println(Size);
					List<MuaHang> list = new ArrayList<>();
					for(int i=0;i<Size;i++) {
						String id = in.readUTF();
						int soluong = in.readInt();
						long dongia = in.readLong();
						
						MuaHang m = new MuaHang(id, soluong, dongia);
						list.add(m);
					}
			
					SanPham sp = new SanPham();
					String idhoadon = "HD"+sp.indexOfBill;
					
					Timestamp date = new Timestamp(new Date().getTime());
					
						
					if(db.addWait(idhoadon,idkhachhang,date,list)) {	
						out.writeBoolean(true);
						sp.indexOfBill++;
						IOFile io = new IOFile();
						io.writeFile();
						
						login.getMainFrame().getFuncWaitPanel().getBtnRefresh().doClick();
					}
					else {
						out.writeBoolean(false);
					}
					
			
				}
				else if(stt == COUNT_BOOK) {
					int count = db.getCountBook();
					out.writeInt(count);
					out.flush();
							
				}
				else if(stt == COUNT_MOVIE) {
					int count = db.getCountMovies();
					out.writeInt(count);
					out.flush();
							
				}
				else if(stt == COUNT_MUSIC) {
					int count = db.getCountMusic();
					out.writeInt(count);
					out.flush();
							
				}
				else if(stt == GET_HISTORY) {
					String idkhachhang = in.readUTF();
					
					List<HistoryWait> list = db.getHistory(idkhachhang);
					
					out.writeInt(list.size());
					out.flush();
					
					for(int i=0;i<list.size();i++) {
						
						out.writeUTF(list.get(i).getName());
						out.flush();
						
						out.writeInt(list.get(i).getSoLuong());
						out.flush();
						
						out.writeUTF(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(list.get(i).getNgayMua()));
						out.flush();
						
						out.writeUTF(list.get(i).getTrangthai());
						out.flush();
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}
}
