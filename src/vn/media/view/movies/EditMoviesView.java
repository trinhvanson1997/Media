package vn.media.view.movies;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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

import vn.media.controller.DBConnector;
import vn.media.models.DiaPhim;
import vn.media.view.MainFrame;

public class EditMoviesView extends JDialog implements ActionListener{
	private static final int WARNING_MESSAGE = 0;
	private static final int INFORMATION_MESSAGE = 0;
	
	private JLabel 		lbID, lbTenSP, lbSoLuong, lbGiaMua, lbGiaBan, lbNgayNhapCuoi, lbDaoDien, lbDienVien;
	private JTextField 	tfID, tfTenSP, tfSoLuong, tfGiaMua, tfGiaBan, tfNgayNhapCuoi, tfDaoDien, tfDienVien;
	private JPanel p1, p2,p3;
	private JButton btnSua, btnHuy;
	private DBConnector db;
	private TableMoviesPanel tableMoviesPanel;
	private DiaPhim diaphim;
	private MainFrame mainFrame;
	Timestamp date = new Timestamp(new Date().getTime());
	public EditMoviesView(MainFrame mainFrame,DBConnector db, TableMoviesPanel tableMoviesPanel,DiaPhim diaphim) {
		this.db = db;
		this.tableMoviesPanel=tableMoviesPanel;
		this.diaphim=diaphim;
		this.mainFrame = mainFrame;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Thêm nhân viên");

		lbID       	= new JLabel("ID");
		lbTenSP    	= new JLabel("Tên đĩa phim");
		lbSoLuong 	= new JLabel("Số lượng");
		lbGiaMua 	= new JLabel("Giá mua");
		lbGiaBan 	= new JLabel("Giá bán");
		lbNgayNhapCuoi = new JLabel("Ngày nhập");
		lbDaoDien   	= new JLabel("Đạo diễn");
		lbDienVien    = new JLabel("Diễn viên");
		
		
		tfID       	= new JTextField(20);  tfID.setEditable(false);
		tfTenSP    	= new JTextField(20);
		tfSoLuong 	= new JTextField(20);
		tfGiaMua   	= new JTextField(20);
		tfGiaBan    = new JTextField(20);
		tfNgayNhapCuoi 	= new JTextField(20);tfNgayNhapCuoi.setEditable(false);
		tfDaoDien   	= new JTextField(20);
		tfDienVien    = new JTextField(20);
		
		
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
		p1.add(lbDaoDien);			p2.add(tfDaoDien);
		p1.add(lbDienVien);		p2.add(tfDienVien);
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
		tfID.setText(diaphim.getId());
		tfTenSP.setText(diaphim.getTenSP());
		tfDaoDien.setText(diaphim.getDaoDien());
		tfDienVien.setText(convertListToString(diaphim.getDienVien()));
		tfSoLuong.setText(String.valueOf(diaphim.getSoLuongTonKho()));
		tfGiaMua.setText(String.valueOf(diaphim.getGiaMua()));;
		tfGiaBan.setText(String.valueOf(diaphim.getGiaBan()));
		tfNgayNhapCuoi.setText(diaphim.getNgayNhapHangCuoi().toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHuy) {
			dispose();
		}
		if (e.getSource() == btnSua) {
		
			
			if(checkFormat() == true){
			
				try {
					String id 		= tfID.getText();
					String tensp 	= tfTenSP.getText();
					int soluong 	= Integer.parseInt(tfSoLuong.getText());
					long giamua 	= Long.parseLong(tfGiaMua.getText());
					long giaban 	= Long.parseLong(tfGiaBan.getText());
					//Timestamp date 		= diaphim.getNgayNhapHangCuoi();
					String daodien 		= tfDaoDien.getText();
					List<String> dienvien = convertStringToList(tfDienVien.getText());
					
					
					DiaPhim diaphim = new DiaPhim(id, tensp, "DP", soluong, giamua, giaban, date, daodien, dienvien);
					
					db.editMovies(diaphim);
					
					dispose();
					
					List<DiaPhim> list = db.getAllMovies(mainFrame.getPageMovies());
					tableMoviesPanel.updateTable(list);
					mainFrame.setListMovie(list);
					JOptionPane.showMessageDialog(null, "Sửa đĩa phim thành công");
					
					 
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
				tfDaoDien.getText().equals(null) || tfDaoDien.getText().equals("") ||
				tfDienVien.getText().equals(null) || tfDienVien.getText().equals("") ||
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