package vn.media.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.controller.DBConnector;
import vn.media.models.SanPham;
import vn.media.models.Store;
import vn.media.view.customer.TableCusPanel;

public class RegisterBox extends JDialog implements ActionListener {
	private static final int WARNING_MESSAGE = 0;
	private static final int INFORMATION_MESSAGE = 0;
	private JLabel lbID, lbHoTen, lbNgaySinh, lbNgay, lbThang, lbNam, lbDiaChi, lbSDT, lbCoin,lbUsername,lbPassword;
	private JTextField tfID, tfHoTen, tfNgaySinh, tfNgay, tfThang, tfNam, tfDiachi, tfSDT, tfCoin,tfPassword,tfUsername;
	private JPanel p1, p2,p3;
	private JButton btnThem, btnHuy;
	private DBConnector db;
	private TableCusPanel tableCusPanel;
	private SanPham sp;
	private Store store;
	
	public RegisterBox(DBConnector db,Store store) {
		this.db = db;
		this.tableCusPanel=tableCusPanel;
		this.store = store;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Register Box");

		lbID       = new JLabel("ID");
		lbHoTen    = new JLabel("Họ Tên");
		lbNgaySinh = new JLabel("Ngày Sinh"); 
		lbDiaChi   = new JLabel("Địa Chỉ");
		lbSDT      = new JLabel("SĐT");
		//lbCoin    = new JLabel("Lương");
		lbUsername = new JLabel("Username");
		lbPassword = new JLabel("Password");
		
		tfID       = new JTextField(15);  tfID.setEditable(false);
		tfHoTen    = new JTextField(15);
		tfNgaySinh = new JTextField(15);
		tfDiachi   = new JTextField(15);
		tfSDT      = new JTextField(15);
		//tfCoin    = new JTextField(15);
		tfUsername = new JTextField(15);
		tfPassword = new JTextField(15);
		
		btnThem    = new JButton("ĐĂNG KÝ");		btnThem.addActionListener(this);
		btnHuy     = new JButton("HỦY ");		btnHuy.addActionListener(this);
		
		
		
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(7, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(7, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
		p1.add(lbID);           p2.add(tfID);
		p1.add(lbHoTen);		p2.add(tfHoTen);	 
		p1.add(lbNgaySinh);		p2.add(tfNgaySinh);	
		p1.add(lbDiaChi);		p2.add(tfDiachi);
		p1.add(lbSDT);			p2.add(tfSDT);
		//p1.add(lbCoin);		p2.add(tfCoin);
		p1.add(lbUsername);		p2.add(tfUsername);
		p1.add(lbPassword);		p2.add(tfPassword);
		
		p3.add(btnThem);		p3.add(btnHuy);
		
		add(p1,BorderLayout.WEST);
		add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
		
		tfID.setText("KH"+sp.indexOfCus);
		
		pack();
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHuy) {
			dispose();
			new LoginBox(db,store).setVisible(true);;
		}
		if (e.getSource() == btnThem) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			
				
			
			
			if(checkFormat() == true){
			

				try {
					Timestamp date = new Timestamp(format.parse(tfNgaySinh.getText()).getTime());
					db.addCus(tfID.getText(), tfHoTen.getText(), date,
							tfDiachi.getText(), tfSDT.getText(),0,tfUsername.getText(),tfPassword.getText());
					
					
					JOptionPane.showMessageDialog(null, "Đăng ký thành công !");
					sp.indexOfCus++;
					
					dispose();
					
					 new LoginBox(db,store);
				} catch (NumberFormatException e1) {
				System.out.println("Fail to register");
				JOptionPane.showMessageDialog(null, "Đăng ký thất bại !");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}
	
	private boolean checkFormat(){
		if (db.checkExistCus(tfID.getText())) {
			JOptionPane.showMessageDialog(null, "ID khách '" + tfID.getText() + "' đã tồn tại!", "Warning",
					WARNING_MESSAGE);
			return false;
		} 
		else if(db.checkExistUsername(tfUsername.getText())==true) {
			JOptionPane.showMessageDialog(null, "Username '"+tfUsername.getText()+"' đã tồn tại!", "Warning",
					WARNING_MESSAGE);
			return false;
		}
		else if(tfHoTen.getText().equals(null) || tfHoTen.getText().equals("") ||
				tfNgaySinh.getText().equals(null) || tfNgaySinh.getText().equals("") ||
				tfDiachi.getText().equals(null) || tfDiachi.getText().equals("") ||
				tfSDT.getText().equals(null) || tfSDT.getText().equals("") ||
				//tfCoin.getText().equals(null) || tfCoin.getText().equals("") ||
				tfUsername.getText().equals(null) || tfUsername.getText().equals("") ||
				tfPassword.getText().equals(null) || tfPassword.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
//		else if(tfNgaySinh.getText().length() != 10){
//			JOptionPane.showMessageDialog(null, "Ngày sinh phải có dạng yyyy/MM/dd vd. 1997/09/01","Cảnh báo",0);
//			return false;
//		}
		
		return true;
	}
}
