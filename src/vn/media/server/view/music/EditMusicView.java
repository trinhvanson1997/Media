package vn.media.server.view.music;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.DiaNhac;

public class EditMusicView extends JDialog implements ActionListener{
	private static final int WARNING_MESSAGE = 0;
	private static final int INFORMATION_MESSAGE = 0;
	
	private JLabel 		lbID, lbTenSP, lbSoLuong, lbGiaMua, lbGiaBan, lbNgayNhapCuoi, lbTheLoai, lbCaSi;
	private JTextField 	tfID, tfTenSP, tfSoLuong, tfGiaMua, tfGiaBan, tfNgayNhapCuoi, tfTheLoai, tfCaSi;
	private JPanel p1, p2,p3;
	private JButton btnSua, btnHuy;
	private DBConnector db;
	private TableMusicPanel tableMusicPanel;
	private DiaNhac dianhac;
	public EditMusicView(DBConnector db, TableMusicPanel tableMusicPanel, DiaNhac dianhac) {
		this.db = db;
		this.tableMusicPanel=tableMusicPanel;
		this.dianhac = dianhac;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Thêm nhân viên");

		lbID       	= new JLabel("ID");
		lbTenSP    	= new JLabel("Tên đĩa nhạc");
		lbSoLuong 	= new JLabel("Số lượng");
		lbGiaMua 	= new JLabel("Giá mua");
		lbGiaBan 	= new JLabel("Giá bán");
		lbNgayNhapCuoi = new JLabel("Ngày nhập");tfNgayNhapCuoi.setEditable(false);
		lbTheLoai   	= new JLabel("Thể loại");
		lbCaSi    = new JLabel("Ca sĩ");
		
		
		tfID       	= new JTextField(20);  tfID.setEditable(false);
		tfTenSP    	= new JTextField(20);
		tfSoLuong 	= new JTextField(20);
		tfGiaMua   	= new JTextField(20);
		tfGiaBan    = new JTextField(20);
		tfNgayNhapCuoi 	= new JTextField(20);
		tfTheLoai   	= new JTextField(20);
		tfCaSi    = new JTextField(20);
		
		
		btnSua    = new JButton("Sửa");		btnSua.addActionListener(this);
		btnHuy     = new JButton("Hủy ");		btnHuy.addActionListener(this);
		
		
		
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(8, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(8, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
		p1.add(lbID);           p2.add(tfID);
		p1.add(lbTenSP);		p2.add(tfTenSP);
		p1.add(lbTheLoai);			p2.add(tfTheLoai);
		p1.add(lbCaSi);		p2.add(tfCaSi);
		p1.add(lbSoLuong);		p2.add(tfSoLuong);	
		p1.add(lbGiaMua);		p2.add(tfGiaMua);	 
		p1.add(lbGiaBan);		p2.add(tfGiaBan);	
		p1.add(lbNgayNhapCuoi);	p2.add(tfNgayNhapCuoi);
		
		
		p3.add(btnSua);		p3.add(btnHuy);
		
		add(p1,BorderLayout.WEST);
		add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
		
		loadInfo();
		
		pack();
		setVisible(true);
	}
	
	
	private void loadInfo() {
		tfID.setText(dianhac.getId());
		tfTenSP.setText(dianhac.getTenSP());
		tfTheLoai.setText(dianhac.getTheLoai());
		tfCaSi.setText(convertListToString(dianhac.getCaSi()));
		tfSoLuong.setText(String.valueOf(dianhac.getSoLuongTonKho()));
		tfGiaMua.setText(String.valueOf(dianhac.getGiaMua()));;
		tfGiaBan.setText(String.valueOf(dianhac.getGiaBan()));
		tfNgayNhapCuoi.setText(dianhac.getNgayNhapHangCuoi().toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHuy) {
			dispose();
		}
		if (e.getSource() == btnSua) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			
			if(checkFormat() == true){
			
				int year = Integer.parseInt(tfNgayNhapCuoi.getText().substring(0, 4)) - 1900;
				int month = Integer.parseInt(tfNgayNhapCuoi.getText().substring(5, 7)) - 1;
				int day = Integer.parseInt(tfNgayNhapCuoi.getText().substring(8, 10));

				try {
					String id 		= tfID.getText();
					String tensp 	= tfTenSP.getText();
					int soluong 	= Integer.parseInt(tfSoLuong.getText());
					long giamua 	= Long.parseLong(tfGiaMua.getText());
					long giaban 	= Long.parseLong(tfGiaBan.getText());
					Timestamp date 		= dianhac.getNgayNhapHangCuoi();
					String theloai 		= tfTheLoai.getText();
					List<String> casi = convertStringToList(tfCaSi.getText());
					
					
					DiaNhac dianhac = new DiaNhac(id, tensp, "DN", soluong, giamua, giaban, date, theloai, casi);
					
					try {
						db.editMusic(dianhac);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					dispose();
					
					List<DiaNhac> list = db.getAllMusic();
					tableMusicPanel.updateTable(list);
					
					JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
					
					 
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
		if (db.checkExistBook(tfID.getText())) {
			JOptionPane.showMessageDialog(null, "ID đĩa nhạc '" + tfID.getText() + "' đã tồn tại!", "Warning",
					WARNING_MESSAGE);
			return false;
		} 
	
		else if(tfTenSP.getText().equals(null) || tfTenSP.getText().equals("") ||
				tfSoLuong.getText().equals(null) || tfSoLuong.getText().equals("") ||
				tfTheLoai.getText().equals(null) || tfTheLoai.getText().equals("") ||
				tfCaSi.getText().equals(null) || tfCaSi.getText().equals("") ||
				tfID.getText().equals(null) || tfID.getText().equals("") ||
				tfGiaMua.getText().equals(null) || tfGiaMua.getText().equals("") ||
				tfGiaBan.getText().equals(null) || tfGiaBan.getText().equals("") ||
				tfNgayNhapCuoi.getText().equals(null) || tfNgayNhapCuoi.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
//		else if(tfNgayNhapCuoi.getText().length() != 10){
//			JOptionPane.showMessageDialog(null, "Ngày phải có dạng yyyy/MM/dd vd. 1997/09/01","Cảnh báo",0);
//			return false;
//		}
		else if( Long.parseLong(tfGiaMua.getText()) <0 ||
				 Long.parseLong(tfGiaMua.getText()) <0 ||
				 Integer.parseInt(tfSoLuong.getText()) < 0
				){
			
			JOptionPane.showMessageDialog(null, "Số lượng, giá mua và giá bán phải lớn hơn 0","Cảnh báo",0);
			return false;
		}
		return true;
	}
	private String convertListToString(List<String> list){
		String temp ="";
		for(String s: list) {
			temp += s;
			temp += ",";
		}
		if(temp.equals("")) {
			return temp;
			}
		else {
			temp=temp.substring(0, temp.length()-1);
			return temp;
		}
	}
}