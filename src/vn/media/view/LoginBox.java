package vn.media.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.common.IOFile;
import vn.media.controller.ChangeTableController;
import vn.media.controller.DBConnector;
import vn.media.controller.bill.RefreshBillController;
import vn.media.controller.bill.SearchBillController;
import vn.media.controller.bill.SeeBillDetailController;
import vn.media.controller.bill.ShowAllBillController;
import vn.media.controller.bill.TableBillController;
import vn.media.controller.book.AddBookController;
import vn.media.controller.book.DeleteBookController;
import vn.media.controller.book.EditBookController;
import vn.media.controller.book.SearchBookController;
import vn.media.controller.book.ShowAllBookController;
import vn.media.controller.book.TableBookController;
import vn.media.controller.customer.EditPassCusController;
import vn.media.controller.customer.SearchCusController;
import vn.media.controller.customer.ShowAllCusController;
import vn.media.controller.customer.TableCusController;
import vn.media.controller.movies.AddMoviesController;
import vn.media.controller.movies.DeleteMoviesController;
import vn.media.controller.movies.EditMoviesController;
import vn.media.controller.movies.SearchMoviesController;
import vn.media.controller.movies.ShowAllMoviesController;
import vn.media.controller.movies.TableMoviesController;
import vn.media.controller.music.AddMusicController;
import vn.media.controller.music.DeleteMusicController;
import vn.media.controller.music.EditMusicController;
import vn.media.controller.music.SearchMusicController;
import vn.media.controller.music.ShowAllMusicController;
import vn.media.controller.music.TableMusicController;
import vn.media.controller.staff.AddStaffController;
import vn.media.controller.staff.DeleteStaffController;
import vn.media.controller.staff.EditPassStaffController;
import vn.media.controller.staff.SearchStaffController;
import vn.media.controller.staff.ShowAllStaffController;
import vn.media.controller.staff.TableStaffController;
import vn.media.controller.wait.HandlingWait;
import vn.media.controller.wait.RefreshWaitController;
import vn.media.controller.wait.SearchWaitController;
import vn.media.controller.wait.ShowAllWaitController;
import vn.media.models.MuaHang;

public class LoginBox extends JFrame implements ActionListener,KeyListener{
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JButton btnLogin,btnRegister,btnClose;
	private DBConnector db;
	private IOFile ioFile=new IOFile();
	private 	MainFrame  mainFrame ;
	public LoginBox(DBConnector db) {
		this.db=db;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10,10));
		setTitle("Login Box");
		
		add(createLabelPanel(),BorderLayout.WEST);
		add(createTextFieldPanel(),BorderLayout.CENTER);
		add(createButtonPanel(),BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2,10,10));
		panel.setBorder(new EmptyBorder(10, 20, 20, 20));
		
		btnLogin    = createButton("Login"); 
		//btnRegister = createButton("Register");
		btnClose    = createButton("Close");
		
		panel.add(btnLogin);
		//panel.add(btnRegister);
		panel.add(btnClose);
		
		return panel;
	}

	private JPanel createTextFieldPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1,10,10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		tfUsername = new JTextField(15); 
		tfPassword = new JPasswordField(15);
		
		
		tfUsername.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					tfPassword.requestFocus();
				}
			}
			
		});
		
		tfPassword.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
			
		});
		
		panel.add(tfUsername);
		panel.add(tfPassword);
		return panel;
	}

	private JPanel createLabelPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1,10,10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		panel.add(new JLabel("Username"));
		panel.add(new JLabel("password"));
		return panel;
	}
	
	private JButton createButton(String name){
		JButton button = new JButton(name);
		button.addActionListener(this);
		button.addKeyListener(this);
		return button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose){
			System.exit(0);
		}
		
		
		
		if(e.getSource() == btnLogin){
			action();
			
		}
		
		if(e.getSource() == btnRegister){
		dispose();
			RegisterBox register = new RegisterBox(db);
		}
		
	}
	
	private void action() {
		String username = tfUsername.getText().trim();
		String password = tfPassword.getText().trim();
		
		if(db.checkAccLogin(username,password)==true){
			dispose();
		
			
			String typeAccount = db.checkTypeAcc(username);
			if(typeAccount.equals("quanly")){
				dispose();
				System.out.println("you loggin as an administrator");
				
				  mainFrame = new MainFrame(username,db);
				 
				 /*          FUNCTIONS OF STAFF        */
				 new ShowAllStaffController(mainFrame,db);
				 new AddStaffController(mainFrame, db);
				 new EditPassStaffController(mainFrame, db);
				 new DeleteStaffController(mainFrame, db);
				 new SearchStaffController(mainFrame, db);
				 new TableStaffController(mainFrame, db);
				 
				 /*			CHANGE MAINPANEL     		*/
				new ChangeTableController(mainFrame,db);
				
				
				/*			FUNCTIONS OF CUSTOMER 		*/
				new ShowAllCusController(mainFrame, db);
				new EditPassCusController(mainFrame, db);
				new SearchCusController(mainFrame, db);
				new TableCusController(mainFrame, db);
				
				/*			FUNCTIONS OF BOOKS			*/
				new ShowAllBookController(mainFrame, db);
				new AddBookController(mainFrame, db);
				new SearchBookController(mainFrame, db);
				new DeleteBookController(mainFrame, db);
				new EditBookController(mainFrame, db);
				new TableBookController(mainFrame, db);
				
				/*			FUNCTIONS OF MOVIES			*/
				new ShowAllMoviesController(mainFrame, db);
				new AddMoviesController(mainFrame, db);
				new SearchMoviesController(mainFrame, db);
				new DeleteMoviesController(mainFrame, db);
				new EditMoviesController(mainFrame, db);
				new TableMoviesController(mainFrame, db);
				
				/*			FUNCTIONS OF MUSIC			*/
				new ShowAllMusicController(mainFrame, db);
				new AddMusicController( mainFrame, db);
				new SearchMusicController( mainFrame, db);
				new DeleteMusicController(mainFrame, db);
				new EditMusicController(mainFrame, db);
				new TableMusicController(mainFrame, db);
				
				/* 			FUNCTIONS OF BILL			*/
				new SearchBillController(mainFrame, db);
				new ShowAllBillController(mainFrame, db);
				new RefreshBillController(mainFrame, db);
				new SeeBillDetailController(mainFrame, db);
				new TableBillController(mainFrame, db);
				
				new SearchWaitController(mainFrame, db);
				new ShowAllWaitController(mainFrame, db);
				new RefreshWaitController(mainFrame, db);
				new HandlingWait(mainFrame, db);
				
				mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				       dispose();
				        ioFile.writeFile();
				    }
				});
				 
			}
			
			else if(typeAccount.equals("nhanvien")) {
					System.out.println("you loggin as a staff");
				
				  mainFrame = new MainFrame(username,db);
		
				 /*			CHANGE MAINPANEL     		*/
				new ChangeTableController(mainFrame,db);
				
				
				/*			FUNCTIONS OF CUSTOMER 		*/
				new ShowAllCusController(mainFrame, db);
				new EditPassCusController(mainFrame, db);
				new SearchCusController(mainFrame, db);
				new TableCusController(mainFrame, db);
				
				/*			FUNCTIONS OF BOOKS			*/
				new ShowAllBookController(mainFrame, db);
				new AddBookController(mainFrame, db);
				new SearchBookController(mainFrame, db);
				new DeleteBookController(mainFrame, db);
				new EditBookController(mainFrame, db);
				new TableBookController(mainFrame, db);
				
				/*			FUNCTIONS OF MOVIES			*/
				new ShowAllMoviesController(mainFrame, db);
				new AddMoviesController(mainFrame, db);
				new SearchMoviesController(mainFrame, db);
				new DeleteMoviesController(mainFrame, db);
				new EditMoviesController(mainFrame, db);
				new TableMoviesController(mainFrame, db);
				
				/*			FUNCTIONS OF MUSIC			*/
				new ShowAllMusicController(mainFrame, db);
				new AddMusicController( mainFrame, db);
				new SearchMusicController( mainFrame, db);
				new DeleteMusicController(mainFrame, db);
				new EditMusicController(mainFrame, db);
				new TableMusicController(mainFrame, db);
				
				/*			FUNCTIONS OF BILL			*/
				new SearchBillController(mainFrame, db);
				new ShowAllBillController(mainFrame, db);
				new RefreshBillController(mainFrame, db);
				new SeeBillDetailController(mainFrame, db);
				new TableBillController(mainFrame, db);
				
				/*			FUNCTIONS OF WAIT			*/
				new SearchWaitController(mainFrame, db);
				new ShowAllWaitController(mainFrame, db);
				new RefreshWaitController(mainFrame, db);
				new HandlingWait(mainFrame, db);
				mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				       dispose();
				        ioFile.writeFile();
				    }
				});
			}
			
		}
		
	
		else{
			JOptionPane.showMessageDialog(null, "Thông tin tài khoản hoặc mật khẩu không chinh xác"
					,"Thông báo",JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
}
