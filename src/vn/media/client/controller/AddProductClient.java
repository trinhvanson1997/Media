package vn.media.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vn.media.client.view.ClientUI;
import vn.media.server.controller.DBConnector;
import vn.media.server.models.MuaHang;
import vn.media.server.models.SanPham;

public class AddProductClient {
	private JButton btnThemVaoGIo;
	
	private JTextField tfIDSanPham,tfDonGia,tfSoLuong,tfTienChoSP;
	private JTextField tfTamTinh,tfThueVAT,tfTongTien;
	
	private SanPham sp ;
	private List<MuaHang> listMH;
	
	public AddProductClient(ClientUI clientUI,DBConnector db) {
		btnThemVaoGIo 	= clientUI.getFuncClientPanel().getBtnThemVaoGio();
		
		tfIDSanPham 	= clientUI.getFuncClientPanel().getTfIDSanPham();
		tfDonGia 		= clientUI.getFuncClientPanel().getTfDonGia();
		tfSoLuong 		= clientUI.getFuncClientPanel().getTfSoLuong();
		tfTienChoSP 	= clientUI.getFuncClientPanel().getTfTienChoSP();
		
		tfTamTinh 		= clientUI.getFuncClientPanel().getTfTamTinh();
		tfThueVAT		= clientUI.getFuncClientPanel().getTfThueVAT();
		tfTongTien		= clientUI.getFuncClientPanel().getTfTongTien();
		
		
		this.listMH = clientUI.getListMH();
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		
		btnThemVaoGIo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfIDSanPham.getText().equals(null) || tfIDSanPham.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm !", null, JOptionPane.WARNING_MESSAGE);
				}
				
				else if(tfSoLuong.getText().equals(null) || tfSoLuong.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Số lượng không dược bỏ trống !", null, JOptionPane.WARNING_MESSAGE);
				}
				else if(Integer.parseInt(tfSoLuong.getText())==0) {
					JOptionPane.showMessageDialog(null, "Số lượng không dược bằng 0 !", null, JOptionPane.WARNING_MESSAGE);
				}
				else {
					for(MuaHang mh:listMH) {
						if(mh.getIdSanPham().equals(tfIDSanPham.getText())) {
							JOptionPane.showMessageDialog(null, "Sản phẩm này đã ở trong giỏ", null, JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					if(Integer.parseInt(tfSoLuong.getText()) > db.getSoLuongTonKho(tfIDSanPham.getText())) {
						JOptionPane.showMessageDialog(null, "Số lượng đặt mua vượt quá số lượng trong kho !", null, JOptionPane.WARNING_MESSAGE);
					}
					else {
						String idHoaDon = "HD"+sp.indexOfBill;
						long dongia = Long.parseLong(convert(tfDonGia.getText()));
						
						MuaHang mh = new MuaHang(idHoaDon,tfIDSanPham.getText(),Integer.parseInt(tfSoLuong.getText()),dongia);
						
						listMH.add(mh);
						
						long tienTamTinh = 0;
						for(MuaHang m: listMH) {
							tienTamTinh += m.getDonGia()*m.getSoLuong();
						}
						
						tfTamTinh.setText(format.format(tienTamTinh));
						tfThueVAT.setText("10%");
						//tfTongTien.setText(String.valueOf(tienTamTinh + tienTamTinh*0.1));
						
						tfTongTien.setText(String.valueOf(tienTamTinh));
						
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						
					
					}
					
				}
			}
		});
	}
	
	public String convert(String s) {
		String temp="";
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == '.' || s.charAt(i) == ' ' || s.charAt(i)== 'đ') {
				continue;
			}
			else {
				temp += s.charAt(i);
			}
		}
		return temp;
	}
}
