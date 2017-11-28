package vn.media.view.staff;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class EditSalaryView extends JDialog implements ActionListener{
	private JButton btnSua,btnHuy;
	private JTextField tfOldSalary,tfNewSalary;
	private DBConnector db;
	private TableStaffPanel tableStaffPanel;
	String username;
	
	public EditSalaryView(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		this.tableStaffPanel = mainFrame.getTableStaffPanel();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400,300);
		setLayout(new BorderLayout(10,10));
		
		setTitle("Thay đổi lương nhân viên");
		setLocationRelativeTo(null);
		
		add(createLabelPanel(),BorderLayout.WEST);
		add(createTextFieldPanel(),BorderLayout.CENTER);
		add(createButtonPanel(),BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		
	}
	
	private JPanel createLabelPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1,15,15));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("Lương hiện tại"));
		panel.add(new JLabel("Lương cập nhật"));
		
		return panel;
	}
	
	private JPanel createTextFieldPanel(){
		int index = tableStaffPanel.getTable().getSelectedRow();
		String id = tableStaffPanel.getTable().getModel().getValueAt(index, 0).toString();
		 username = db.getUsernameStaff(id);
		NhanVien nv = db.getStaff(username);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1,15,15));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfOldSalary = new JTextField(15); tfOldSalary.setText(String.valueOf(nv.getLuong())); tfOldSalary.setEditable(false);
		tfNewSalary = new JTextField(15);
		panel.add(tfOldSalary);
		panel.add(tfNewSalary);
		return panel;
	}
	
	
	private JPanel createButtonPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2,30,30));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnSua = createButton("Sửa");
		btnHuy = createButton("Hủy");
		panel.add(btnSua);
		panel.add(btnHuy);
		return panel;
	}
	private JButton createButton(String name){
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnHuy){
			dispose();
		}
		
		if(e.getSource() == btnSua){
	
			long newSalary = Long.parseLong(tfNewSalary.getText());
			
		
				db.editSalary(username, newSalary);
				dispose();
				JOptionPane.showMessageDialog(null, "Cập nhật lương thành công");
			
		}
		
	}
}
