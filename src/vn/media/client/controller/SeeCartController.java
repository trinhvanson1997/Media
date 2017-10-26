package vn.media.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.client.view.CartView;
import vn.media.client.view.ClientUI;
import vn.media.server.controller.DBConnector;
import vn.media.server.models.MuaHang;

public class SeeCartController {
	
	private JButton btnThanhToan;
	public List<MuaHang> listMH ;
	
	public SeeCartController(ClientUI clientUI,DBConnector db) {
		btnThanhToan = clientUI.getFuncClientPanel().getBtnThanhToan();
		this.listMH = clientUI.getListMH();
		
		
		btnThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listMH.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Giỏ hàng trống, vui lòng chọn hàng để xem", null, JOptionPane.WARNING_MESSAGE);
				}
				else {
					new CartView(clientUI,db);
				}
				
			}
		});
	}
}
