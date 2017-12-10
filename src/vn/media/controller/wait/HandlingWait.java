package vn.media.controller.wait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.controller.DBConnector;
import vn.media.models.HoaDon;
import vn.media.models.MuaHang;
import vn.media.view.MainFrame;
import vn.media.view.wait.TableWaitPanel;
import vn.media.view.wait.WaitDetailView;

public class HandlingWait {
	private JButton btnXuLy;
	private TableWaitPanel tableWaitPanel;

	public HandlingWait(MainFrame mainFrame, DBConnector db) {
		btnXuLy = mainFrame.getFuncWaitPanel().getBtnThongTinChiTiet();
		tableWaitPanel = mainFrame.getTableWaitPanel();

		btnXuLy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = tableWaitPanel.getTable().getSelectedRow();
				boolean flag = true;

				if (index >= 0) {
					String idhoadon = (String) tableWaitPanel.getTable().getModel().getValueAt(index, 0);
					HoaDon hd = db.getBill(idhoadon);
					WaitDetailView waitDetailView = new WaitDetailView(idhoadon, hd);
					waitDetailView.getBtnXuly().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							boolean flag = true;
							List<MuaHang> list = hd.getMuaHang();
							for (int i = 0; i < list.size(); i++) {
									if (list.get(i).getSoLuong() > db.getSoLuongTonKho(list.get(i).getIdSanPham())) {
											flag = false;
									}
								
							}
							
							if(flag) {
								db.handlingWait(mainFrame.id,idhoadon);

								for(MuaHang dh:list) {
									String idsanpham = dh.getIdSanPham();
									int soluong = db.getSoLuongTonKho(idsanpham) - dh.getSoLuong();
									
									db.updateNumberProduct(idsanpham,soluong);
								}
								
								mainFrame.getFuncWaitPanel().getBtnRefresh().doClick();
								
								JOptionPane.showMessageDialog(null, "Xử lý đơn hàng thành công !");
								waitDetailView.dispose();
								
								mainFrame.getFuncBillPanel().getBtnRefresh().doClick();
							}
							else {JOptionPane.showMessageDialog(null, "Xử lý đơn hàng thất bại do hết hàng !");
							waitDetailView.dispose();
							
							}
							
						}
					});
						
				
				} else
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn đơn hàng nào !");

			}
		});
}}
