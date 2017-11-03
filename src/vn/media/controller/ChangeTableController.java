package vn.media.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vn.media.common.IOFile;
import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.HoaDon;
import vn.media.models.KhachHang;
import vn.media.models.NhanVien;
import vn.media.models.Sach;
import vn.media.view.ChoicePanel;
import vn.media.view.LoginBox;
import vn.media.view.MainFrame;
import vn.media.view.ManagerInfoView;
import vn.media.view.TabbedProduct;
import vn.media.view.bill.FuncBillPanel;
import vn.media.view.bill.TableBillPanel;
import vn.media.view.book.FuncBookPanel;
import vn.media.view.book.TableBookPanel;
import vn.media.view.customer.FuncCusPanel;
import vn.media.view.customer.TableCusPanel;
import vn.media.view.movies.FuncMoviesPanel;
import vn.media.view.movies.TableMoviesPanel;
import vn.media.view.music.FuncMusicPanel;
import vn.media.view.music.TableMusicPanel;
import vn.media.view.staff.EditStaffView;
import vn.media.view.staff.FuncStaffPanel;
import vn.media.view.staff.TableStaffPanel;

public class ChangeTableController {
	private ChoicePanel choicePanel;
	
	private FuncStaffPanel 	funcStaffPanel;
	private FuncCusPanel 	funcCusPanel;
	private FuncBookPanel 	funcBookPanel;
	private FuncMoviesPanel funcMoviesPanel;
	private FuncMusicPanel 	funcMusicPanel;
	private FuncBillPanel 	funcBillPanel;
	
	private TableStaffPanel 	tableStaffPanel;
	private TableCusPanel 		tableCusPanel;
	private TabbedProduct 		tabbedProduct;
	private TableBookPanel 		tableBookPanel;
	private TableMoviesPanel 	tableMoviesPanel;
	private TableMusicPanel 	tableMusicPanel;
	private TableBillPanel 		tableBillPanel;
	
	private JButton btnStaff;
	private JButton btnCus;
	private JButton btnProduct;
	private JButton btnLogOut;
	private JButton btnInfo;
	private JButton btnBill;
	
	private IOFile ioFile = new IOFile();
	private String type;
	private String username;
	
	public ChangeTableController(MainFrame mainFrame,DBConnector db) {
		btnStaff 	= mainFrame.getChoicePanel().getBtnNhanVien();
		btnCus 		= mainFrame.getChoicePanel().getBtnKhachHang();
		btnProduct 	= mainFrame.getChoicePanel().getBtnSanPham();
		btnLogOut = mainFrame.getChoicePanel().getBtnDangXuat();
		btnInfo =mainFrame.getChoicePanel().getBtnThongTin();
		btnBill = mainFrame.getChoicePanel().getBtnThongKe();
		
		type = mainFrame.getTopInfoPanel().getType();
		username =mainFrame.getTopInfoPanel().getUsername();
		
		
		funcStaffPanel 	= mainFrame.getFuncStaffPanel();
		funcCusPanel 	= mainFrame.getFuncCusPanel();
		funcBookPanel 	= mainFrame.getFuncBookPanel();
		funcMoviesPanel = mainFrame.getFuncMoviesPanel();
		funcMusicPanel 	= mainFrame.getFuncMusicPanel();
		funcBillPanel 	= mainFrame.getFuncBillPanel();
		
		tableBillPanel 	= mainFrame.getTableBillPanel();
		tableStaffPanel = mainFrame.getTableStaffPanel();
		tableCusPanel 	= mainFrame.getTableCusPanel();
		tabbedProduct 	= mainFrame.getTabbedProduct();
		
		tableBookPanel 		= mainFrame.getTabbedProduct().getTableBookPanel();
		tableMoviesPanel 	= mainFrame.getTabbedProduct().getTableMoviesPanel();
		tableMusicPanel 	= mainFrame.getTabbedProduct().getTableMusicPanel();
		
		JPanel funcPanel 	= mainFrame.getFuncPanel();
		JPanel tablePanel 	= mainFrame.getTablePanel();
		
		btnStaff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(type.equals("quanly")) {
					tablePanel.remove(tablePanel.getComponent(0));
					tablePanel.add(tableStaffPanel, BorderLayout.CENTER);
					tablePanel.revalidate();
					tablePanel.repaint();
					
					funcPanel.remove(funcPanel.getComponent(0));
					funcPanel.add(funcStaffPanel, BorderLayout.CENTER);
					funcPanel.revalidate();
					funcPanel.repaint();
					
			
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập mục này", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnCus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablePanel.remove(tablePanel.getComponent(0));
				tablePanel.add(tableCusPanel, BorderLayout.CENTER);
				tablePanel.revalidate();
				tablePanel.repaint();
				
				funcPanel.remove(funcPanel.getComponent(0));
				funcPanel.add(funcCusPanel, BorderLayout.CENTER);
				funcPanel.revalidate();
				funcPanel.repaint();
				
			
			}
		});
		
		btnProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				
				tablePanel.remove(tablePanel.getComponent(0));
				tablePanel.add(tabbedProduct, BorderLayout.CENTER);
				tablePanel.revalidate();
				tablePanel.repaint();
				
				if(tabbedProduct.getSelectedIndex() == 0) {
					funcPanel.remove(funcPanel.getComponent(0));
					funcPanel.add(funcBookPanel, BorderLayout.CENTER);
					funcPanel.revalidate();
					funcPanel.repaint();
				}
				else if(tabbedProduct.getSelectedIndex() == 1) {
	            	
					
	            	funcPanel.remove(funcPanel.getComponent(0));
					funcPanel.add(funcMoviesPanel, BorderLayout.CENTER);
					funcPanel.revalidate();
					funcPanel.repaint();
					
					
	            }
	            else if(tabbedProduct.getSelectedIndex() == 2) {
	            	funcPanel.remove(funcPanel.getComponent(0));
					funcPanel.add(funcMusicPanel, BorderLayout.CENTER);
					funcPanel.revalidate();
					funcPanel.repaint();
					
	            }
			
				
				
				
			}
		});
		
		tabbedProduct.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            if(tabbedProduct.getSelectedIndex() == 0) {
	        		
					
				
	            	funcPanel.remove(funcPanel.getComponent(0));
					funcPanel.add(funcBookPanel, BorderLayout.CENTER);
					funcPanel.revalidate();
					funcPanel.repaint();
	            }
	            else if(tabbedProduct.getSelectedIndex() == 1) {
	            	
	            
					
	            	funcPanel.remove(funcPanel.getComponent(0));
					funcPanel.add(funcMoviesPanel, BorderLayout.CENTER);
					funcPanel.revalidate();
					funcPanel.repaint();
					
					
	            }
	            else if(tabbedProduct.getSelectedIndex() == 2) {
	            	funcPanel.remove(funcPanel.getComponent(0));
					funcPanel.add(funcMusicPanel, BorderLayout.CENTER);
					funcPanel.revalidate();
					funcPanel.repaint();
					
				
	            }
	        }
	    });
		
		btnBill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tablePanel.remove(tablePanel.getComponent(0));
				tablePanel.add(tableBillPanel, BorderLayout.CENTER);
				tablePanel.revalidate();
				tablePanel.repaint();
				
				funcPanel.remove(funcPanel.getComponent(0));
				funcPanel.add(funcBillPanel, BorderLayout.CENTER);
				funcPanel.revalidate();
				funcPanel.repaint();
				
				
			}
		});
		
		
		
		
		btnLogOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int click =JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn đăng xuất?", "Thông báo", JOptionPane.YES_NO_OPTION);
				if(click==JOptionPane.YES_OPTION) {
					
					ioFile.writeFile();
					
					mainFrame.dispose();
					new LoginBox(db);
				}
				else if(click == JOptionPane.NO_OPTION) {
					return;
				}
				
				
			}
		});
		
		
		btnInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(type.equals("quanly")) {
					new ManagerInfoView();
				}
				else {
					
					NhanVien nv = db.getStaff(username);
					new EditStaffView(db,nv);
				}
				
			}
		});
	}
	
	
}
