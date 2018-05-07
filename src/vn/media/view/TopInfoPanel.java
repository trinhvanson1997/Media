package vn.media.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import vn.media.controller.DBConnector;
import vn.media.models.NhanVien;

public class TopInfoPanel extends JPanel{
	private JLabel lbUser,lbTableName;
	private	String username;
	private	String type;
	String strType;
	public TopInfoPanel(String username,DBConnector db) {
		this.username=username;
		this.type = db.checkTypeAcc(username);
		
		if(this.type.equals("quanly")) {
			strType = "Quản Lý";
		}
		else {
			strType = "Nhân Viên";
		}
		
		setLayout(new BorderLayout(10,10));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		setSize(990,30);
		
		NhanVien nv = db.getStaff(username);
		
		lbUser = new JLabel("Xin chào: ,  "+nv.getHoTen());
		lbUser.setHorizontalAlignment(JLabel.CENTER);
	
		
		
		add(lbUser,BorderLayout.WEST);
		
		add(new JLabel("Chức vụ:  "+strType),BorderLayout.CENTER);
		
		//add(new JLabel("Type:  Quan ly"),BorderLayout.CENTER);
	}

	public JLabel getLbUser() {
		return lbUser;
	}

	public void setLbUser(JLabel lbUser) {
		this.lbUser = lbUser;
	}

	public JLabel getLbTableName() {
		return lbTableName;
	}

	public void setLbTableName(JLabel lbTableName) {
		this.lbTableName = lbTableName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
