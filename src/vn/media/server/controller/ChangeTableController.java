package vn.media.server.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vn.media.server.common.IOFile;
import vn.media.server.models.DiaNhac;
import vn.media.server.models.DiaPhim;
import vn.media.server.models.KhachHang;
import vn.media.server.models.NhanVien;
import vn.media.server.models.Sach;
import vn.media.server.view.ChoicePanel;
import vn.media.server.view.LoginBox;
import vn.media.server.view.MainFrame;
import vn.media.server.view.ManagerInfoView;
import vn.media.server.view.TabbedProduct;
import vn.media.server.view.book.FuncBookPanel;
import vn.media.server.view.book.TableBookPanel;
import vn.media.server.view.customer.FuncCusPanel;
import vn.media.server.view.customer.TableCusPanel;
import vn.media.server.view.movies.FuncMoviesPanel;
import vn.media.server.view.movies.TableMoviesPanel;
import vn.media.server.view.music.FuncMusicPanel;
import vn.media.server.view.music.TableMusicPanel;
import vn.media.server.view.staff.EditStaffView;
import vn.media.server.view.staff.FuncStaffPanel;
import vn.media.server.view.staff.TableStaffPanel;

public class ChangeTableController {
	private ChoicePanel choicePanel;
	
	private FuncStaffPanel 	funcStaffPanel;
	private FuncCusPanel 	funcCusPanel;
	private FuncBookPanel 	funcBookPanel;
	private FuncMoviesPanel funcMoviesPanel;
	private FuncMusicPanel 	funcMusicPanel;
	
	
	private TableStaffPanel 	tableStaffPanel;
	private TableCusPanel 		tableCusPanel;
	private TabbedProduct 		tabbedProduct;
	private TableBookPanel 		tableBookPanel;
	private TableMoviesPanel 	tableMoviesPanel;
	private TableMusicPanel 	tableMusicPanel;
	
	
	private JButton btnStaff;
	private JButton btnCus;
	private JButton btnProduct;
	private JButton btnLogOut;
	private JButton btnInfo;
	
	
	private IOFile ioFile = new IOFile();
	private String type;
	private String username;
	
	public ChangeTableController(MainFrame mainFrame,DBConnector db) {
		btnStaff 	= mainFrame.getChoicePanel().getBtnNhanVien();
		btnCus 		= mainFrame.getChoicePanel().getBtnKhachHang();
		btnProduct 	= mainFrame.getChoicePanel().getBtnSanPham();
		btnLogOut = mainFrame.getChoicePanel().getBtnDangXuat();
		btnInfo =mainFrame.getChoicePanel().getBtnThongTin();
		
		type = mainFrame.getTopInfoPanel().getType();
		username =mainFrame.getTopInfoPanel().getUsername();
		
		
		funcStaffPanel 	= mainFrame.getFuncStaffPanel();
		funcCusPanel 	= mainFrame.getFuncCusPanel();
		funcBookPanel 	= mainFrame.getFuncBookPanel();
		funcMoviesPanel = mainFrame.getFuncMoviesPanel();
		funcMusicPanel 	= mainFrame.getFuncMusicPanel();
		
		
		
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
					
					List<NhanVien > list = db.getAllStaff();
					tableStaffPanel.updateTable(list);
					
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
				
				List<KhachHang > list = db.getAllCus();
				tableCusPanel.updateTable(list);
			}
		});
		
		btnProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				List<Sach > list = db.getAllBook();
				tableBookPanel.updateTable(list);
				
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
	            	
	            	List<DiaPhim > list1 = db.getAllMovies();
					tableMoviesPanel.updateTable(list1);
					
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
					
					List<DiaNhac > list2 = db.getAllMusic();
					tableMusicPanel.updateTable(list2);
	            }
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
