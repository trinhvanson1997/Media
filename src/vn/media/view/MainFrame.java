package vn.media.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vn.media.controller.DBConnector;
import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.HoaDon;
import vn.media.models.KhachHang;
import vn.media.models.NhanVien;
import vn.media.models.Sach;
import vn.media.view.bill.FuncBillPanel;
import vn.media.view.bill.TableBillPanel;
import vn.media.view.book.FuncBookPanel;
import vn.media.view.book.TableBookPanel;
import vn.media.view.customer.FuncCusPanel;
import vn.media.view.customer.TableCusPanel;
import vn.media.view.movies.FuncMoviesPanel;
import vn.media.view.music.FuncMusicPanel;
import vn.media.view.staff.FuncStaffPanel;
import vn.media.view.staff.TableStaffPanel;
import vn.media.view.wait.FuncWaitPanel;
import vn.media.view.wait.TableWaitPanel;

public class MainFrame extends JFrame {
	private TopInfoPanel topInfoPanel;
	private ChoicePanel choicePanel;

	private List<Sach> listBook;
	private List<DiaPhim> listMovie;
	private List<DiaNhac> listMusic;

	private List<NhanVien> listStaff;
	private List<KhachHang> listCus;

	private List<HoaDon> listBill;
	private List<HoaDon> listWait;
	
	private TableStaffPanel tableStaffPanel;
	private TableCusPanel tableCusPanel;
	private TableBillPanel tableBillPanel;
	private TableWaitPanel tableWaitPanel;
	
	private FuncCusPanel funcCusPanel;
	private FuncStaffPanel funcStaffPanel;
	private FuncBookPanel funcBookPanel;
	private FuncMoviesPanel funcMoviesPanel;
	private FuncMusicPanel funcMusicPanel;
	private FuncWaitPanel funcWaitPanel;
	


private FuncBillPanel funcBillPanel;
	private TabbedProduct tabbedProduct;

	private MenuBarView menuBarView;

	private JPanel mainPanel;
	private JPanel tablePanel;
	private JPanel funcPanel;
	private DBConnector db;
	public String id;
	public String username;
	
	private int pageStaff;
	private int pageCus;
	private int pageBill;
	private int pageWait;
	
	

	private int pageBook;
	private int pageMovies;
	private int pageMusic;
	
	
	
	
	
	public String tensach[] = {"Tiếng việt","Xác suất thống kê","Tiếng anh chuyên ngành","Giáo dục công dân","Công nghệ",
			"Tin đại cương","Giải tích 1","Giải tích 2","Giải tích 3","Hóa học","Pháp luật đại cương","Chính trị học","Đường lối cách mạng"
			,"Trí tuệ nhân tạo","Cơ sở dữ liệ","Toán cao cấp","AI","An toàn bảo mật thông tin"
			,"CSDL nâng cao","Học máy","Nhập môn CNPM","Lý 1","Tiếng anh chuyên ngành"};
	
	public String nxb[] = {"Thống kê","Lao động xã hội","Chính trị Quốc gia","Thế giới","Quân đội nhân dân","Y học","Văn Hoá thông tin",
			"Thông tấn","Khoa học và kỹ thuật","Y học và thể dục thể thao","Kim đồng","Bách khoa","Tuổi trẻ","Kinh tế"};
	
	public String[] tacgia = {"Jack ma","Hoàng Thúy Long","Nguyễn Chung Chính","Đặng Đức Trạch","Đặng Văn Ngữ","Nguyễn Đình Hường","Phạm Mạnh Hùng","Vũ Quang Bích","Đỗ Nguyên Phương","Nam Cao","Hamminton",
			"Phùng Đắc Cam","Nguyễn Kim Giao","Tạ Văn Bĩnh","Lê Đăng Hà","Nguyễn Hữu Tâm","Lê Minh Triết","Vũ Bình Minh","Bùi Văn A"};
	
	
	public MainFrame(String username, DBConnector db) {
		this.db = db;
		this.username =username;
		
		NhanVien nv = db.getStaff(username);
		this.id = nv.getId();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setTitle("MediaOne (Server)");

		setResizable(true);
		/* NORTH MAINFRAME */
		topInfoPanel = new TopInfoPanel(username, db);

		/* WEST MAINFRAME */
		choicePanel = new ChoicePanel();

		/* PANELS OF STAFFS */
		tableStaffPanel = new TableStaffPanel();
		funcStaffPanel = new FuncStaffPanel();

		/* PANELS OF CUSTOMERS */
		tableCusPanel = new TableCusPanel();
		funcCusPanel = new FuncCusPanel();

		/* PANELS OF PRODUCTS */
		tabbedProduct = new TabbedProduct();

		/* PANELS OF BOOKS */

		funcBookPanel = new FuncBookPanel();

		/* PANELS OF MOVIES */

		funcMoviesPanel = new FuncMoviesPanel();

		/* PANELS OF MUSICS */

		funcMusicPanel = new FuncMusicPanel();
		funcBillPanel = new FuncBillPanel();
		tableBillPanel = new TableBillPanel();
		
		
		funcWaitPanel = new FuncWaitPanel();
		tableWaitPanel = new TableWaitPanel();
		/* MENU PANEL */
		menuBarView = new MenuBarView();

		//setJMenuBar(menuBarView);

		
		pageStaff = tableStaffPanel.getCurrentPage();
		pageCus   = tableCusPanel.getCurrentPage();
		pageBook  = tabbedProduct.getTableBookPanel().getCurrentPage();
		pageMovies = tabbedProduct.getTableMoviesPanel().getCurrentPage();
		pageMusic = tabbedProduct.getTableMusicPanel().getCurrentPage();
		
		
		
		add(topInfoPanel, BorderLayout.NORTH);
		add(choicePanel, BorderLayout.WEST);
		add(createMainPanel(), BorderLayout.CENTER);

		listBook = db.getAllBook(0);
		pageBook =0;
		
		listMovie = db.getAllMovies(0);
		pageMovies =0;
		
		listMusic = db.getAllMusic(0);
		pageMusic =0;
		
		listStaff = db.getAllStaff(0);
		pageStaff = 0;
		
		
		listCus = db.getAllCus(0);
		pageCus =0;
		
		listBill = db.getAllBill(0);
		pageBill =0;
		
		listWait = db.getAllWait(0);
		pageWait = 0;

		
		initTableProduct();
		initTableStaff();
		initTableCus();
		initRefresh();
		initTableBill();
		initTableWait();
		setVisible(true);
		
	}

	public void initTableStaff() {
		tableStaffPanel.updateTable(listStaff);

	}

	public void initTableCus() {
		tableCusPanel.updateTable(listCus);
	}

	private JPanel createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(20, 20));

		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(tabbedProduct, BorderLayout.CENTER);

		funcPanel = new JPanel();
		funcPanel.setLayout(new BorderLayout());
		funcPanel.add(funcBookPanel, BorderLayout.CENTER);

		mainPanel.add(tablePanel, BorderLayout.CENTER);
		mainPanel.add(funcPanel, BorderLayout.SOUTH);

		return mainPanel;
	}

	public void initTableProduct() {
		tabbedProduct.getTableBookPanel().updateTable(listBook);
		tabbedProduct.getTableMoviesPanel().updateTable(listMovie);
		tabbedProduct.getTableMusicPanel().updateTable(listMusic);

	}

	public void initTableBill() {
		tableBillPanel.updateTable(listBill);
	}
	
	public void initTableWait() {
		tableWaitPanel.updateTable(listWait);
	}
	public void initRefresh() {
		funcStaffPanel.getBtnRefresh().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<NhanVien> list = db.getAllStaff(pageStaff);
				tableStaffPanel.updateTable(list);
				listStaff = list;

			}
		});

		funcCusPanel.getBtnRefresh().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<KhachHang> list = db.getAllCus(pageCus);
				tableCusPanel.updateTable(list);
				listCus = list;
			}
		});

		funcBookPanel.getBtnRefresh().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Sach> list = db.getAllBook(pageBook);
				tabbedProduct.getTableBookPanel().updateTable(list);
				listBook = list;
			}
		});

		funcMoviesPanel.getBtnRefresh().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<DiaPhim> list = db.getAllMovies(pageMovies);
				tabbedProduct.getTableMoviesPanel().updateTable(list);
				listMovie = list;

			}
		});
		
		funcMusicPanel.getBtnRefresh().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			List<DiaNhac> list = db.getAllMusic(pageMusic);
			tabbedProduct.getTableMusicPanel().updateTable(list);
			listMusic = list;
			}
		});
	}

	public TableBillPanel getTableBillPanel() {
		return tableBillPanel;
	}

	public void setTableBillPanel(TableBillPanel tableBillPanel) {
		this.tableBillPanel = tableBillPanel;
	}

	public MenuBarView getMenuBarView() {
		return menuBarView;
	}

	public void setMenuBarView(MenuBarView menuBarView) {
		this.menuBarView = menuBarView;
	}

	public void setFuncMoviesPanel(FuncMoviesPanel funcMoviesPanel) {
		this.funcMoviesPanel = funcMoviesPanel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public JPanel getFuncPanel() {
		return funcPanel;
	}

	public void setFuncPanel(JPanel funcPanel) {
		this.funcPanel = funcPanel;
	}

	public DBConnector getDb() {
		return db;
	}

	public void setDb(DBConnector db) {
		this.db = db;
	}

	public TableCusPanel getTableCusPanel() {
		return tableCusPanel;
	}

	public void setTableCusPanel(TableCusPanel tableCusPanel) {
		this.tableCusPanel = tableCusPanel;
	}

	public FuncCusPanel getFuncCusPanel() {
		return funcCusPanel;
	}

	public void setFuncCusPanel(FuncCusPanel funcCusPanel) {
		this.funcCusPanel = funcCusPanel;
	}

	public TopInfoPanel getTopInfoPanel() {
		return topInfoPanel;
	}

	public void setTopInfoPanel(TopInfoPanel topInfoPanel) {
		this.topInfoPanel = topInfoPanel;
	}
	public TableWaitPanel getTableWaitPanel() {
		return tableWaitPanel;
	}

	public void setTableWaitPanel(TableWaitPanel tableWaitPanel) {
		this.tableWaitPanel = tableWaitPanel;
	}

	public FuncWaitPanel getFuncWaitPanel() {
		return funcWaitPanel;
	}

	public void setFuncWaitPanel(FuncWaitPanel funcWaitPanel) {
		this.funcWaitPanel = funcWaitPanel;
	}
	public TabbedProduct getTabbedProduct() {
		return tabbedProduct;
	}

	public void setTabbedProduct(TabbedProduct tabbedProduct) {
		this.tabbedProduct = tabbedProduct;
	}

	public ChoicePanel getChoicePanel() {
		return choicePanel;
	}

	public void setChoicePanel(ChoicePanel choicePanel) {
		this.choicePanel = choicePanel;
	}

	public FuncStaffPanel getFuncStaffPanel() {
		return funcStaffPanel;
	}

	public void setFuncStaffPanel(FuncStaffPanel funcStaffPanel) {
		this.funcStaffPanel = funcStaffPanel;
	}

	public TableStaffPanel getTableStaffPanel() {
		return tableStaffPanel;
	}

	public void setTableStaffPanel(TableStaffPanel tableStaffPanel) {
		this.tableStaffPanel = tableStaffPanel;
	}

	public int getPageStaff() {
		return pageStaff;
	}

	public void setPageStaff(int pageStaff) {
		this.pageStaff = pageStaff;
	}

	public int getPageCus() {
		return pageCus;
	}

	public void setPageCus(int pageCus) {
		this.pageCus = pageCus;
	}

	public int getPageBill() {
		return pageBill;
	}

	public void setPageBill(int pageBill) {
		this.pageBill = pageBill;
	}

	public FuncBookPanel getFuncBookPanel() {
		return funcBookPanel;
	}

	public void setFuncBookPanel(FuncBookPanel funcBookPanel) {
		this.funcBookPanel = funcBookPanel;
	}

	public FuncMoviesPanel getFuncMoviesPanel() {
		return funcMoviesPanel;
	}

	public FuncBillPanel getFuncBillPanel() {
		return funcBillPanel;
	}

	public void setFuncBillPanel(FuncBillPanel funcBillPanel) {
		this.funcBillPanel = funcBillPanel;
	}

	public void setFuncmoviesPanel(FuncMoviesPanel funcMoviesPanel) {
		this.funcMoviesPanel = funcMoviesPanel;
	}

	public FuncMusicPanel getFuncMusicPanel() {
		return funcMusicPanel;
	}

	public void setFuncMusicPanel(FuncMusicPanel funcMusicPanel) {
		this.funcMusicPanel = funcMusicPanel;
	}

	public List<Sach> getListBook() {
		return listBook;
	}

	public void setListBook(List<Sach> listBook) {
		this.listBook = listBook;
	}

	public List<DiaPhim> getListMovie() {
		return listMovie;
	}

	public int getPageBook() {
		return pageBook;
	}

	public void setPageBook(int pageBook) {
		this.pageBook = pageBook;
	}
	public List<HoaDon> getListWait() {
		return listWait;
	}

	public void setListWait(List<HoaDon> listWait) {
		this.listWait = listWait;
	}

	public int getPageWait() {
		return pageWait;
	}

	public void setPageWait(int pageWait) {
		this.pageWait = pageWait;
	}
	public int getPageMovies() {
		return pageMovies;
	}

	public void setPageMovies(int pageMovies) {
		this.pageMovies = pageMovies;
	}

	public int getPageMusic() {
		return pageMusic;
	}

	public void setPageMusic(int pageMusic) {
		this.pageMusic = pageMusic;
	}

	public void setListMovie(List<DiaPhim> listMovie) {
		this.listMovie = listMovie;
	}

	public List<DiaNhac> getListMusic() {
		return listMusic;
	}

	public void setListMusic(List<DiaNhac> listMusic) {
		this.listMusic = listMusic;
	}

	public List<NhanVien> getListStaff() {
		return listStaff;
	}

	public void setListStaff(List<NhanVien> listStaff) {
		this.listStaff = listStaff;
	}

	public List<KhachHang> getListCus() {
		return listCus;
	}

	public void setListCus(List<KhachHang> listCus) {
		this.listCus = listCus;
	}

	public List<HoaDon> getListBill() {
		return listBill;
	}

	public void setListBill(List<HoaDon> listBill) {
		this.listBill = listBill;
	}

}
