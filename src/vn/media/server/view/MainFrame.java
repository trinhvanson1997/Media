package vn.media.server.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vn.media.server.controller.DBConnector;
import vn.media.server.view.book.FuncBookPanel;
import vn.media.server.view.customer.FuncCusPanel;
import vn.media.server.view.customer.TableCusPanel;
import vn.media.server.view.movies.FuncMoviesPanel;
import vn.media.server.view.music.FuncMusicPanel;
import vn.media.server.view.staff.FuncStaffPanel;
import vn.media.server.view.staff.TableStaffPanel;

public class MainFrame extends JFrame{
	private TopInfoPanel topInfoPanel;
	private ChoicePanel  choicePanel;
	
	private TableStaffPanel   tableStaffPanel;
	private TableCusPanel tableCusPanel;
	
	
	private FuncCusPanel funcCusPanel;
	private FuncStaffPanel    funcStaffPanel;
	private FuncBookPanel funcBookPanel;
	private FuncMoviesPanel funcMoviesPanel;
	private FuncMusicPanel funcMusicPanel;
	
	private TabbedProduct tabbedProduct;
	
	private MenuBarView menuBarView;
	
	private JPanel mainPanel;
	private JPanel tablePanel;
	private JPanel funcPanel;
	private DBConnector  db;
	
	public MainFrame(String username,DBConnector db) {
		this.db=db;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setTitle("MediaOne (Server)");
		setResizable(false);
		/* 			NORTH MAINFRAME			*/
		topInfoPanel 	= new TopInfoPanel(username, db);
		
		/*			WEST MAINFRAME			*/
		choicePanel  = new ChoicePanel();
		
		/*			PANELS OF STAFFS			*/
		tableStaffPanel   = new TableStaffPanel();
		funcStaffPanel    = new FuncStaffPanel();
		
		/*			PANELS OF CUSTOMERS		*/
		tableCusPanel	= new TableCusPanel();
		funcCusPanel 	= new FuncCusPanel();
		
		/*			PANELS OF PRODUCTS		*/
		tabbedProduct	 = new TabbedProduct();
		
		/*			PANELS OF BOOKS		*/
		
		funcBookPanel 	 = new FuncBookPanel();
		
		
		/*			PANELS OF MOVIES		*/
	
		funcMoviesPanel 	= new FuncMoviesPanel();
		
		
		/*			PANELS OF MUSICS		*/
		
		funcMusicPanel 		= new FuncMusicPanel();
		
		
		/*			MENU PANEL				*/
		menuBarView = new MenuBarView();
		
		setJMenuBar(menuBarView);
		
		add(topInfoPanel,BorderLayout.NORTH);
		add(choicePanel,BorderLayout.WEST);
		add(createMainPanel(),BorderLayout.CENTER);
		
		
		
		
		setVisible(true);
	}
	
	private JPanel createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(20,20));
		
		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(tabbedProduct, BorderLayout.CENTER);
		
		funcPanel = new JPanel();
		funcPanel.setLayout(new BorderLayout());
		funcPanel.add(funcBookPanel, BorderLayout.CENTER);
		
		mainPanel.add(tablePanel,BorderLayout.CENTER);
		mainPanel.add(funcPanel,BorderLayout.SOUTH);
		
		return mainPanel;
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



	public FuncBookPanel getFuncBookPanel() {
		return funcBookPanel;
	}

	public void setFuncBookPanel(FuncBookPanel funcBookPanel) {
		this.funcBookPanel = funcBookPanel;
	}

	public FuncMoviesPanel getFuncMoviesPanel() {
		return funcMoviesPanel;
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
	
	

}
