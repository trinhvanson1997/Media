package vn.media.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vn.media.common.IOFile;
import vn.media.models.NhanVien;
import vn.media.models.Sach;
import vn.media.models.SanPham;
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
import vn.media.view.wait.FuncWaitPanel;
import vn.media.view.wait.TableWaitPanel;

public class ChangeTableController {
	private ChoicePanel choicePanel;
	
	private FuncStaffPanel 	funcStaffPanel;
	private FuncCusPanel 	funcCusPanel;
	private FuncBookPanel 	funcBookPanel;
	private FuncMoviesPanel funcMoviesPanel;
	private FuncMusicPanel 	funcMusicPanel;
	private FuncBillPanel 	funcBillPanel;
	private FuncWaitPanel 	funcWaitPanel;
	
	private TableStaffPanel 	tableStaffPanel;
	private TableCusPanel 		tableCusPanel;
	private TabbedProduct 		tabbedProduct;
	private TableBookPanel 		tableBookPanel;
	private TableMoviesPanel 	tableMoviesPanel;
	private TableMusicPanel 	tableMusicPanel;
	private TableBillPanel 		tableBillPanel;
	private TableWaitPanel 		tableWaitPanel;
	
	private JButton btnStaff;
	private JButton btnCus;
	private JButton btnProduct;
	private JButton btnLogOut;
	private JButton btnInfo;
	private JButton btnBill;
	private JButton btnWait;
	
	private IOFile ioFile = new IOFile();
	private String type;
	private String username;
	
	
	public String tensach[] = {"Tiếng việt","Xác suất thống kê","Tiếng anh chuyên ngành","Giáo dục công dân","Công nghệ",
			"Tin đại cương","Giải tích 1","Giải tích 2","Giải tích 3","Hóa học","Pháp luật đại cương","Chính trị học","Đường lối cách mạng"
			,"Trí tuệ nhân tạo","Cơ sở dữ liệ","Toán cao cấp","AI","An toàn bảo mật thông tin"
			,"CSDL nâng cao","Học máy","Nhập môn CNPM","Lý 1","Tiếng anh chuyên ngành"};
	
	public String nxb[] = {"Thống kê","Lao động xã hội","Chính trị Quốc gia","Thế giới","Quân đội nhân dân","Y học","Văn Hoá thông tin",
			"Thông tấn","Khoa học và kỹ thuật","Y học và thể dục thể thao","Kim đồng","Bách khoa","Tuổi trẻ","Kinh tế"};
	
	public String tacgia[] = {"Jack ma","Hoàng Thúy Long","Nguyễn Chung Chính","Đặng Đức Trạch","Đặng Văn Ngữ","Nguyễn Đình Hường","Phạm Mạnh Hùng","Vũ Quang Bích","Đỗ Nguyên Phương","Nam Cao","Hamminton",
			"Phùng Đắc Cam","Nguyễn Kim Giao","Tạ Văn Bĩnh","Lê Đăng Hà","Nguyễn Hữu Tâm","Lê Minh Triết","Vũ Bình Minh","Bùi Văn A"};
	
	
	public ChangeTableController(MainFrame mainFrame,DBConnector db) {
		btnStaff 	= mainFrame.getChoicePanel().getBtnNhanVien();
		btnCus 		= mainFrame.getChoicePanel().getBtnKhachHang();
		btnProduct 	= mainFrame.getChoicePanel().getBtnSanPham();
		btnLogOut = mainFrame.getChoicePanel().getBtnDangXuat();
		btnInfo =mainFrame.getChoicePanel().getBtnThongTin();
		btnBill = mainFrame.getChoicePanel().getBtnHoaDon();
		btnWait	= mainFrame.getChoicePanel().getBtnDonHang();
		
		type = mainFrame.getTopInfoPanel().getType();
		username =mainFrame.getTopInfoPanel().getUsername();
		
		
		funcStaffPanel 	= mainFrame.getFuncStaffPanel();
		funcCusPanel 	= mainFrame.getFuncCusPanel();
		funcBookPanel 	= mainFrame.getFuncBookPanel();
		funcMoviesPanel = mainFrame.getFuncMoviesPanel();
		funcMusicPanel 	= mainFrame.getFuncMusicPanel();
		funcBillPanel 	= mainFrame.getFuncBillPanel();
		funcWaitPanel 	= mainFrame.getFuncWaitPanel();
		
		tableBillPanel 	= mainFrame.getTableBillPanel();
		tableStaffPanel = mainFrame.getTableStaffPanel();
		tableCusPanel 	= mainFrame.getTableCusPanel();
		tabbedProduct 	= mainFrame.getTabbedProduct();
		tableWaitPanel 	= mainFrame.getTableWaitPanel();
		
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
		btnWait.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				tablePanel.remove(tablePanel.getComponent(0));
				tablePanel.add(tableWaitPanel, BorderLayout.CENTER);
				tablePanel.revalidate();
				tablePanel.repaint();
				
				funcPanel.remove(funcPanel.getComponent(0));
				funcPanel.add(funcWaitPanel, BorderLayout.CENTER);
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
//				Random r = new Random();
//				int i=0,index;
//				SanPham sp = new SanPham();
//				while(i<1000) {
//					String id = "SA"+sp.indexOfBook;
//					
//					index = r.nextInt(tensach.length);
//					String tenSP = tensach[index];
//					
//					int soLuongTonKho = 50 + r.nextInt(100);
//					long giaMua = (r.nextInt(50)+10)*1000;
//					long giaBan = (r.nextInt(2000)+60)*1000;
//					
//					Timestamp ngayNhapHangCuoi = new Timestamp(new Date().getTime());
//					index = r.nextInt(nxb.length);
//					String nhaXB = nxb[index];
//					index = r.nextInt(tacgia.length);
//					String tacGia = tacgia[index];
//					List<String> list = new ArrayList<>();
//					list.add(tacGia);
//					Sach sach = new Sach(id, tenSP, "SA", soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, nhaXB, list);
//					db.addBook(sach);
//					sp.indexOfBook++;
//					i++;
//				}
//				
				
				
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
