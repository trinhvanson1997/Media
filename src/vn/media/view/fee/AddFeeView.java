package vn.media.view.fee;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.controller.DBConnector;
import vn.media.models.Fee;
import vn.media.view.MainFrame;



@SuppressWarnings("serial")
public class AddFeeView extends JDialog  implements ActionListener {

	
	private JLabel 		 lbTenChiPhi, lbGiatri, lbChuKy;
	private JTextField 	 tfTenChiPhi, tfGiatri, tfChuKy;
	private JPanel p1, p2,p3;
	private JButton btnThem, btnHuy;
	private DBConnector db;
	private TableFeePanel tablefeePanel;
	//private Store store;
	private MainFrame mainFrame;
	public AddFeeView(DBConnector db, MainFrame main,TableFeePanel tableFeePanel) {
		this.db = db;
		this.tablefeePanel=tableFeePanel;
		//this.store=store;
		this.mainFrame=main;
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Thêm Chi phí");

		lbTenChiPhi    	= new JLabel("Tên chi phí");
		lbGiatri 	= new JLabel("Giá trị");
		lbChuKy 	= new JLabel("Chu kỳ");
		
		
		tfTenChiPhi    	= new JTextField(20);
		tfGiatri 	= new JTextField(20);
		tfChuKy   	= new JTextField(20);;
		
		btnThem    = new JButton("Thêm");		btnThem.addActionListener(this);
		btnHuy     = new JButton("Hủy ");	btnHuy.addActionListener(this);
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(3, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(3, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
		p1.add(lbTenChiPhi);		p2.add(tfTenChiPhi);
		p1.add(lbGiatri);			p2.add(tfGiatri);
		p1.add(lbChuKy);		p2.add(tfChuKy);
		
		
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

			
			if(checkFormat() == true){
			
				DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
				try {
					String tenChiphi 	= tfTenChiPhi.getText();
					int cycle 	= Integer.parseInt(tfChuKy.getText());
					long giatri 	= Long.parseLong(tfGiatri.getText());
					Date time = new Date();
					@SuppressWarnings("deprecation")
					Timestamp now = new Timestamp(time.getYear(), time.getMonth(), time.getDate(), time.getHours(), time.getMinutes(), time.getSeconds(), 0);
					Fee fee = new Fee(tenChiphi,giatri,cycle,now);
					
					db.addFee(fee);
					
					dispose();
					
					List<Fee> list = mainFrame.getListFee();
					list.add(fee);
					mainFrame.setListFee(list);
					
					tablefeePanel.updateTable(list);
//					store.setCostList(list);
//					store.setTotalMoney(store.getTotalMoney()-fee.getFeeValue());
//					mainFrame.getTopInfoPanel().getLbTotalMoney().setText(format.format(store.getTotalMoney()).toString());
					JOptionPane.showMessageDialog(null, "Thêm chi phí thành công");
					
					 
				} catch (Exception e1) {
				
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}
	

	
	private boolean checkFormat(){
		if(tfTenChiPhi.getText().equals(null) || tfTenChiPhi.getText().equals("") ||
				tfChuKy.getText().equals(null) || tfChuKy.getText().equals("") ||
				tfGiatri.getText().equals(null) || tfGiatri.getText().equals("") )
		{
			JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if( Integer.parseInt(tfChuKy.getText()) <0 ||
				 Double.parseDouble(tfGiatri.getText()) <0 
				){
			
			JOptionPane.showMessageDialog(null, "Số lượng, giá mua và giá bán phải lớn hơn 0","Cảnh báo",0);
			return false;
		}
		return true;
	}
	

	
	

	

}
