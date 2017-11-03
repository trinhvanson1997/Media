package vn.media.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import vn.media.controller.DBConnector;
import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.KhachHang;
import vn.media.models.Sach;
import vn.media.models.SanPham;

public class ClientThread extends Thread{
	public static final int LOGIN=1,LOGIN_SUCCESS=2,LOGIN_FAIL=3,GET_ALL_BOOK=4,GET_ALL_MOVIE=5
			,GET_ALL_MUSIC=6,GET_CUSTOMER_NAME=7,GET_COIN_CUS=8,GET_SLTK=9,UPDATE_COIN=10
					,UPDATE_NUMBER_PRODUCT=11,UPDATE_CUSTOMER_INFO=12
					,GET_CUSTOMER=13,CHECK_SERIAL=14,GET_VALUE_CARD=15,CHECK_EXIST_USERNAME=16
							,ADD_CUSTOMER=17,CLOSE_REQUEST=18;
	public Socket socket;
	public Server server;
	public DBConnector db;
	
	public DataInputStream in;
	public DataOutputStream out;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	
	public ClientThread(Socket socket,DBConnector db,Server server)  {
		this.socket=socket;
		this.server=server;
		this.db = db;
		
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
				int stt=in.read();
				
				if(stt==LOGIN) {
					String username = in.readUTF();
					String password = in.readUTF();
					System.out.println("lala");
					if(db.checkAccLogin(username, password)==true && db.checkTypeAcc(username).equals("khachhang")) out.writeInt(LOGIN_SUCCESS);
					else out.writeInt(LOGIN_FAIL);
					
				}
				else if(stt == GET_ALL_BOOK) {
					List<Sach> list = db.getAllBook();
					
					oos.writeObject(list);
				}
				else if(stt == GET_ALL_MOVIE) {
					List<DiaPhim> list = db.getAllMovies();
					
					oos.writeObject(list);
				}
				else if(stt == GET_ALL_MUSIC) {
					List<DiaNhac> list = db.getAllMusic();
					
					oos.writeObject(list);
				}
				else if(stt == GET_CUSTOMER_NAME) {
					String username = in.readUTF();
					String name = db.getCusName(username);
					
					out.writeUTF(name);
				}
				else if(stt == GET_COIN_CUS) {
					String username = in.readUTF();
					long coin = db.getCoinCus(username);
					
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
					SanPham sp = new SanPham();
					
					if(db.addCus("KH"+sp.indexOfCus, kh.getHoTen(),
							kh.getNgaySinh(), kh.getDiaChi(), kh.getsDT(), kh.getCoin(), 
							kh.getUsername(), kh.getPassword())) {
						sp.indexOfCus++;
						out.writeBoolean(true);
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
