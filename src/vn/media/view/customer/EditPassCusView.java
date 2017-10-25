package vn.media.view.customer;

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

import vn.media.controller.DBConnector;
import vn.media.view.MainFrame;

public class EditPassCusView extends JDialog implements ActionListener{
	
	private JButton btnSua,btnHuy;
	private JTextField tfUsername,tfNewPass;
	private DBConnector db;
	private TableCusPanel tableCusPanel;
	
	public EditPassCusView(MainFrame mainFrame,DBConnector db) {
		this.db = db;
		this.tableCusPanel = mainFrame.getTableCusPanel();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400,300);
		setLayout(new BorderLayout(10,10));
		
		setTitle("Thay đổi mật khẩu");
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
		panel.add(new JLabel("Username    "));
		panel.add(new JLabel("Mật khẩu mới"));
		
		return panel;
	}
	
	private JPanel createTextFieldPanel(){
		int index = tableCusPanel.getTable().getSelectedRow();
		String id = tableCusPanel.getTable().getModel().getValueAt(index, 0).toString();
		String username = db.getUsernameCus(id);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1,15,15));
		tfUsername = new JTextField(15); tfUsername.setText(username);
		tfNewPass = new JTextField(15);
		panel.add(tfUsername);
		panel.add(tfNewPass);
		return panel;
	}
	
	
	private JPanel createButtonPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2,30,30));
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
			String username = tfUsername.getText();
			String pass = tfNewPass.getText();
			
			if(db.checkExistUsernameCus(username)==false){
				JOptionPane.showMessageDialog(null, "ID nhân viên '" + tfUsername.getText() + "' không tồn tại!", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else{
				db.editPass(username, pass);
				dispose();
				JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công");
			}
		}
		
	}
}
