package vn.media.client.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import vn.media.server.controller.DBConnector;
import vn.media.server.models.DiaNhac;
import vn.media.server.models.DiaPhim;
import vn.media.server.models.Sach;
import vn.media.server.view.TabbedProduct;
import vn.media.server.view.book.TableBookPanel;
import vn.media.server.view.movies.TableMoviesPanel;
import vn.media.server.view.music.TableMusicPanel;

public class ClientUI extends JFrame{
	private DBConnector db;
	
	
	private JPanel topPanel;
	private JPanel tablePanel;
	private JPanel funcPanel;
	
	private FuncClientPanel funcClientPanel;
	private TabbedProduct tabbedProduct;
	
	private TableBookPanel tableBookPanel;
	private TableMoviesPanel tableMoviesPanel;
	private TableMusicPanel tableMusicPanel;
	
	private SearchClientPanel searchClientPanel;
	
	private String username;
	private long coin;
	
	
	public ClientUI(String username,DBConnector db) {
		this.db =db;
		this.username = username;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setTitle("MediaOne (Client)");
		
		funcClientPanel = new FuncClientPanel();
		
		
		
		
		add(createTopPanel(),BorderLayout.NORTH);
		add(funcClientPanel,BorderLayout.SOUTH);
		add(createTablePanel(),BorderLayout.CENTER);
		
		
		initTable();
		
		setVisible(true);
	}
	
	private JPanel createTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		topPanel.setSize(990,30);
		
		String name = db.getCusName(username);
		coin = db.getCoinCus(username);
		
		JLabel label = new JLabel("Xin chào bạn,   "+name+" !");
		JLabel lbCoin = new JLabel("Coin : "+coin);
		
		label.setHorizontalAlignment(JLabel.LEFT);
		lbCoin.setHorizontalAlignment(JLabel.CENTER);
		
		topPanel.add(label, BorderLayout.CENTER);
		topPanel.add(lbCoin,BorderLayout.EAST);
		
		return topPanel;
	}

	
	private JPanel createTablePanel() {
		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0,10));
		tablePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		tabbedProduct = new TabbedProduct();
		tablePanel.add(tabbedProduct, BorderLayout.CENTER);
		
		searchClientPanel = new SearchClientPanel();
		tablePanel.add(searchClientPanel.getSearchBookPanel(),BorderLayout.SOUTH);
		
		return tablePanel;
	}

	
	private void initTable() {
		List<Sach> 	  l1 		= 	db.getAllBook();
		List<DiaPhim> l2	= 	db.getAllMovies();
		List<DiaNhac> l3	=	db.getAllMusic();
		
		this.tableBookPanel = tabbedProduct.getTableBookPanel();
		this.tableMoviesPanel = tabbedProduct.getTableMoviesPanel();
		this.tableMusicPanel = tabbedProduct.getTableMusicPanel();
		
		tableBookPanel.updateTableClient(l1);
		tableMoviesPanel.updateTableClient(l2);
		tableMusicPanel.updateTableClient(l3);
	}
	
	
	
	
	
	
	public SearchClientPanel getSearchClientPanel() {
		return searchClientPanel;
	}

	public void setSearchClientPanel(SearchClientPanel searchClientPanel) {
		this.searchClientPanel = searchClientPanel;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
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

	public FuncClientPanel getFuncClientPanel() {
		return funcClientPanel;
	}

	public void setFuncClientPanel(FuncClientPanel funcClientPanel) {
		this.funcClientPanel = funcClientPanel;
	}

	public TabbedProduct getTabbedProduct() {
		return tabbedProduct;
	}

	public void setTabbedProduct(TabbedProduct tabbedProduct) {
		this.tabbedProduct = tabbedProduct;
	}

	public String getUsername() {
		return username;
	}

	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TableBookPanel getTableBookPanel() {
		return tableBookPanel;
	}

	public void setTableBookPanel(TableBookPanel tableBookPanel) {
		this.tableBookPanel = tableBookPanel;
	}

	public TableMoviesPanel getTableMoviesPanel() {
		return tableMoviesPanel;
	}

	public void setTableMoviesPanel(TableMoviesPanel tableMoviesPanel) {
		this.tableMoviesPanel = tableMoviesPanel;
	}

	public TableMusicPanel getTableMusicPanel() {
		return tableMusicPanel;
	}

	public void setTableMusicPanel(TableMusicPanel tableMusicPanel) {
		this.tableMusicPanel = tableMusicPanel;
	}
	
	
}
