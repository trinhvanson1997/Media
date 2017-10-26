package vn.media.client.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.MuaHang;

public class CartView extends JDialog implements ActionListener{
	private ClientUI clientUI;
	private DBConnector db;
	private List<MuaHang> listMH;
	private JButton btnDatHang;
	private JButton btnXoaKhoiGio;
	private JButton btnThoat;
	private TableCart tableCart;
	private long coin;
	private String username;
	private JPanel topPanel;
	
	
	public CartView(ClientUI clientUI,DBConnector db) {
		this.clientUI = clientUI;
		this.db = db;
		this.listMH = clientUI.getListMH();
		this.coin = clientUI.getCoin();
		this.username =clientUI.getUsername();
		this.topPanel =clientUI.getTopPanel();
		
		
		tableCart = new TableCart();
		tableCart.updateTable(listMH);
		setSize(400, 400);
		setLayout(new BorderLayout(10,10));
		add(tableCart,BorderLayout.CENTER);
		add(createButtonPanel(),BorderLayout.SOUTH);
		
		setTitle("Giỏ hàng");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(1, 3,10,10));
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		btnDatHang = createButton("ĐẶT HÀNG");
		btnXoaKhoiGio = createButton("XÓA KHỎI GIỎ");
		btnThoat = createButton("THOÁT");
		
		p.add(btnDatHang); p.add(btnXoaKhoiGio); p.add(btnThoat);
		
		return p;
	}
	
	
	
	private JButton createButton(String name)
	{
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnThoat) {
			dispose();
		}
		
		if(e.getSource() == btnXoaKhoiGio) {
			int index = tableCart.getTable().getSelectedRow();
			
			if(index >=0) {
				listMH.remove(index);
				
				tableCart.updateTable(listMH);
			}
			else if(listMH.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không có sản phẩm nào để xóa ");
			}
			else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm nào để xóa ");
			}
		}
		
		if(e.getSource() == btnDatHang) {
		action();
	
		}
	}
	
	public void action() {
		
		
		long tienTamTinh = 0;
		for(MuaHang m: listMH) {
			tienTamTinh += m.getDonGia()*m.getSoLuong();
		}
		
		
		///////////////////////////////////////////////////
		//tienTamTinh += tienTamTinh*0.1;
		
		if(coin < tienTamTinh) {
			JOptionPane.showMessageDialog(null, "Bạn không đủ tiền để thực hiện giao dịch", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Bạn đã đặt hàng thành công");
			coin = coin -tienTamTinh;
			db.updateCoin(username, coin);
	
			
			
			topPanel.remove(topPanel.getComponent(1));
			
			JLabel lbCoin = new JLabel("Coin : "+coin);
			lbCoin.setHorizontalAlignment(JLabel.CENTER);
			topPanel.add(lbCoin,BorderLayout.EAST);
			
			topPanel.validate();
			topPanel.repaint();
		
			
		}
	}

}
