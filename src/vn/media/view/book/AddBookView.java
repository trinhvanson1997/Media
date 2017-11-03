package vn.media.view.book;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.common.IOFile;
import vn.media.controller.DBConnector;
import vn.media.models.Sach;
import vn.media.models.SanPham;
import vn.media.view.MainFrame;

public class AddBookView extends JDialog implements ActionListener {
	private static final int WARNING_MESSAGE = 0;
	private static final int INFORMATION_MESSAGE = 0;
	
	private JLabel 		lbID, lbTenSP, lbSoLuong, lbGiaMua, lbGiaBan, lbNgayNhapCuoi, lbNXB, lbTacGia;
	private JTextField 	tfID, tfTenSP, tfSoLuong, tfGiaMua, tfGiaBan, tfNgayNhapCuoi, tfNXB, tfTacGia;
	private JPanel p1, p2,p3;
	private JButton btnThem, btnHuy;
	private DBConnector db;
	private TableBookPanel tableBookPanel;
	private SanPham sp;
	Timestamp date = new Timestamp(new Date().getTime());
	
	private MainFrame mainFrame;
	
	
	public AddBookView(MainFrame mainFrame,DBConnector db, TableBookPanel tableBookPanel) {
		this.db = db;
		this.tableBookPanel=tableBookPanel;
	this.mainFrame = mainFrame;
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Thêm sách");

		lbID       	= new JLabel("ID");
		lbTenSP    	= new JLabel("Tên sách");
		lbSoLuong 	= new JLabel("Số lượng");
		lbGiaMua 	= new JLabel("Giá mua");
		lbGiaBan 	= new JLabel("Giá bán");
		lbNgayNhapCuoi = new JLabel("Ngày nhập");
		lbNXB   	= new JLabel("Nhà XB");
		lbTacGia    = new JLabel("Tác giả");
		
		
		tfID       	= new JTextField(20); tfID.setEditable(false);
		tfTenSP    	= new JTextField(20);
		tfSoLuong 	= new JTextField(20);
		tfGiaMua   	= new JTextField(20);
		tfGiaBan    = new JTextField(20);
		tfNgayNhapCuoi 	= new JTextField(20); tfNgayNhapCuoi.setEditable(false);
		tfNXB   	= new JTextField(20);
		tfTacGia    = new JTextField(20);
		
		
		btnThem    = new JButton("THÊM");		btnThem.addActionListener(this);
		btnHuy     = new JButton("HỦY ");		btnHuy.addActionListener(this);
		
		
		
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(8, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(8, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
		p1.add(lbID);           p2.add(tfID);
		p1.add(lbTenSP);		p2.add(tfTenSP);
		p1.add(lbNXB);			p2.add(tfNXB);
		p1.add(lbTacGia);		p2.add(tfTacGia);
		p1.add(lbSoLuong);		p2.add(tfSoLuong);	
		p1.add(lbGiaMua);		p2.add(tfGiaMua);	 
		p1.add(lbGiaBan);		p2.add(tfGiaBan);	
		p1.add(lbNgayNhapCuoi);	p2.add(tfNgayNhapCuoi);
		
		
		p3.add(btnThem);		p3.add(btnHuy);
		
		add(p1,BorderLayout.WEST);
		add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
		
		tfID.setText("SA"+sp.indexOfBook);
		
		
		tfNgayNhapCuoi.setText(date.toString());
		
		pack();
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHuy) {
			dispose();
		}
		if (e.getSource() == btnThem) {


			if(checkFormat() == true){
			
				try {
					String id 		= tfID.getText();
					String tensp 	= tfTenSP.getText();
					int soluong 	= Integer.parseInt(tfSoLuong.getText());
					long giamua 	= Long.parseLong(tfGiaMua.getText());
					long giaban 	= Long.parseLong(tfGiaBan.getText());
					
					String nxb 		= tfNXB.getText();
					List<String> tacgia = convertStringToList(tfTacGia.getText());
					
					
					Sach sach = new Sach(id, tensp, "SA", soluong, giamua, giaban, date, nxb, tacgia);
					
					db.addBook(sach);
					
					dispose();
					
					List<Sach> list =mainFrame.getListBook();
					list.add(sach);
					mainFrame.setListBook(list);
					tableBookPanel.updateTable(list);
					JOptionPane.showMessageDialog(null, "Thêm sách thành công");
					
					 sp.indexOfBook++;
					 new IOFile().writeFile();
					 
				} catch (NumberFormatException e1) {
				
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}
	
private List<String> convertStringToList(String s){
		
		List<String> list = new ArrayList<>();
		
		if(s.contains(",")==false) {
			list.add(s);
		}
		else {
			int begin=0,last=0;
			for(int i=0;i<s.length();i++) {
				if(s.charAt(i)==',') {
					last=i;;
					list.add(s.substring(begin, last));
					begin=last+1;
				}
			}
			
			list.add(s.substring(s.lastIndexOf(',')+1));
		}
		return list;
	}
	
	private boolean checkFormat(){
	 if(tfTenSP.getText().equals(null) || tfTenSP.getText().equals("") ||
				tfSoLuong.getText().equals(null) || tfSoLuong.getText().equals("") ||
				tfNXB.getText().equals(null) || tfNXB.getText().equals("") ||
				tfTacGia.getText().equals(null) || tfTacGia.getText().equals("") ||
				tfID.getText().equals(null) || tfID.getText().equals("") ||
				tfGiaMua.getText().equals(null) || tfGiaMua.getText().equals("") ||
				tfGiaBan.getText().equals(null) || tfGiaBan.getText().equals("") ||
				tfNgayNhapCuoi.getText().equals(null) || tfNgayNhapCuoi.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Các trư�?ng dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		else if( Long.parseLong(tfGiaMua.getText()) <0 ||
				 Long.parseLong(tfGiaMua.getText()) <0 ||
				 Integer.parseInt(tfSoLuong.getText()) < 0
				){
			
			JOptionPane.showMessageDialog(null, "Số lượng, giá mua và giá bán phải lớn hơn 0","Cảnh báo",0);
			return false;
		}
		return true;
	}
	

}
