package vn.media.server.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ManagerInfoView extends JDialog implements ActionListener{
	private JLabel lbHoTen,lbNgaySinh,lbDiaChi,lbSDT,lbChucVu;
	private JButton btnOK ;
	
	public ManagerInfoView() {
		setLayout(new BorderLayout(20,20));
		setSize(200,300);
		
		btnOK=new JButton("OK");
		btnOK.addActionListener(this);
		
		add(createLabelPanel(),BorderLayout.CENTER);
		add(btnOK,BorderLayout.SOUTH);
		setTitle("Thông tin quản lý");
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	private JPanel createLabelPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 10, 20));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		lbHoTen 	= new JLabel("Họ tên    :  Trịnh Văn Sơn");
		lbNgaySinh	= new JLabel("Ngày sinh :  1/9/1997     ");
		lbDiaChi	= new JLabel("Quê quán  :  Vĩnh Phúc    ");
		lbSDT		= new JLabel("Phone     :  0982747570   ");
		lbChucVu	= new JLabel("Chức vụ   :  Quản lý      ");
		
		panel.add(lbHoTen);
		panel.add(lbNgaySinh);
		panel.add(lbDiaChi);
		panel.add(lbSDT);
		panel.add(lbChucVu);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnOK) {
			dispose();
		}
		
	}

	
}
