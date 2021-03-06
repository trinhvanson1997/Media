package vn.media.view.staff;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.controller.DBConnector;
import vn.media.models.NhanVien;
import vn.media.view.MainFrame;

public class EditStaffView extends JDialog implements ActionListener{
	private static final int WARNING_MESSAGE = 0;
	private static final int INFORMATION_MESSAGE = 0;
	private JLabel lbID, lbHoTen, lbNgaySinh, lbNgay, lbThang, lbNam, lbDiaChi, lbSDT, lbLuong,lbUsername,lbPassword;
	private JTextField tfID, tfHoTen, tfNgaySinh, tfNgay, tfThang, tfNam, tfDiachi, tfSDT, tfLuong,tfPassword,tfUsername;
	private JPanel p1, p2,p3;
	private JButton btnThem, btnHuy;
	private DBConnector db;
	private TableStaffPanel tablePanel;
	private MainFrame mainFrame;
String oldname;
	private NhanVien nv;
	
	public EditStaffView(MainFrame mainFrame,DBConnector db, NhanVien nv) {
		this.db = db;
		this.tablePanel=tablePanel;
		this.nv = nv;
		this.mainFrame = mainFrame;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(500, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Thông tin nhân viên");

		lbID       = new JLabel("ID");  
		lbHoTen    = new JLabel("Họ Tên");
		lbNgaySinh = new JLabel("Ngày Sinh");
		lbDiaChi   = new JLabel("Địa Chỉ");
		lbSDT      = new JLabel("SĐT");
		
		lbUsername = new JLabel("Username");
		lbPassword = new JLabel("Password");
		
		tfID       = new JTextField(15); 	tfID.setText(nv.getId()); tfID.setEditable(false); 
		tfHoTen    = new JTextField(15);	tfHoTen.setText(nv.getHoTen());		 
		tfNgaySinh = new JTextField(15);	tfNgaySinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(nv.getNgaySinh()));
		tfDiachi   = new JTextField(15);	tfDiachi.setText(nv.getDiaChi());
		tfSDT      = new JTextField(15);	tfSDT.setText(nv.getsDT());
	
		tfUsername = new JTextField(15);	tfUsername.setText(nv.getUsername()); tfUsername.setEditable(false);
		tfPassword = new JTextField(15);	tfPassword.setText(nv.getPassword());
		
		btnThem    = new JButton("SỬA ");		btnThem.addActionListener(this);
		btnHuy     = new JButton("ĐÓNG");		btnHuy.addActionListener(this);
		
		
		
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(8, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(8, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
		p1.add(lbID);           p2.add(tfID);
		p1.add(lbHoTen);		p2.add(tfHoTen);	 
		p1.add(lbNgaySinh);		p2.add(tfNgaySinh);	
		p1.add(lbDiaChi);		p2.add(tfDiachi);
		p1.add(lbSDT);			p2.add(tfSDT);
		
		p1.add(lbUsername);		p2.add(tfUsername);
		p1.add(lbPassword);		p2.add(tfPassword);
		
		p3.add(btnThem);		p3.add(btnHuy);
		
		add(p1,BorderLayout.WEST);
		add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);

		
		pack();
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHuy) {
			dispose();
		}
		if (e.getSource() == btnThem) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(checkFormat() == true){
				try {
					Timestamp date = new Timestamp(format.parse(tfNgaySinh.getText()).getTime());
					db.editStaff(tfID.getText(), tfHoTen.getText(), date,
							tfDiachi.getText(), tfSDT.getText(), nv.getLuong(),tfUsername.getText(),tfPassword.getText());
					dispose();
					
					List<NhanVien> list = db.getAllStaff(mainFrame.getPageStaff());
					mainFrame.setListStaff(list);
					mainFrame.getTableStaffPanel().updateTable(list);
					
					JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
				if(tfHoTen.getText().compareTo(nv.getHoTen()) != 0) {
					mainFrame.getTopInfoPanel().remove(mainFrame.getTopInfoPanel().getComponent(0));
					JLabel lbUser = new JLabel("Xin chào : "+tfHoTen.getText());
					lbUser.setHorizontalAlignment(JLabel.CENTER);
				
					
					
					mainFrame.getTopInfoPanel().add(lbUser,BorderLayout.WEST);
					
					mainFrame.getTopInfoPanel().validate();
					mainFrame.getTopInfoPanel().repaint();
					
				}
				
					 
				} catch (NumberFormatException e1) {
				
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
		 
		 if(tfHoTen.getText().equals(null) || tfHoTen.getText().equals("") ||
				tfNgaySinh.getText().equals(null) || tfNgaySinh.getText().equals("") ||
				tfDiachi.getText().equals(null) || tfDiachi.getText().equals("") ||
				tfSDT.getText().equals(null) || tfSDT.getText().equals("") ||
			
				tfUsername.getText().equals(null) || tfUsername.getText().equals("") ||
				tfPassword.getText().equals(null) || tfPassword.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		
		return true;
	}
}
