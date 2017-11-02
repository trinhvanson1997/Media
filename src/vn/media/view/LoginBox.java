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
import vn.media.controller.book.AddBookController;
import vn.media.controller.book.DeleteBookController;
import vn.media.controller.book.EditBookController;
import vn.media.controller.book.SearchBookController;
import vn.media.controller.book.ShowAllBookController;
import vn.media.controller.customer.EditPassCusController;
import vn.media.controller.customer.SearchCusController;
import vn.media.controller.customer.ShowAllCusController;
import vn.media.controller.movies.AddMoviesController;
import vn.media.controller.movies.DeleteMoviesController;
import vn.media.controller.movies.EditMoviesController;
import vn.media.controller.movies.SearchMoviesController;
import vn.media.controller.movies.ShowAllMoviesController;
import vn.media.controller.music.AddMusicController;
import vn.media.controller.music.DeleteMusicController;
import vn.media.controller.music.EditMusicController;
import vn.media.controller.music.SearchMusicController;
import vn.media.controller.music.ShowAllMusicController;
import vn.media.controller.staff.AddStaffController;
import vn.media.controller.staff.DeleteStaffController;
import vn.media.controller.staff.EditPassStaffController;
import vn.media.controller.staff.SearchStaffController;
import vn.media.controller.staff.ShowAllStaffController;
import vn.media.models.MuaHang;

public class LoginBox extends JFrame implements ActionListener,KeyListener{
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JButton btnLogin,btnRegister,btnClose;
	private DBConnector db;
	private IOFile ioFile=new IOFile();
	
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
		panel.setLayout(new GridLayout(1, 3,10,10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		btnLogin    = createButton("Login"); 
		btnRegister = createButton("Register");
		btnClose    = createButton("Close");
		
		panel.add(btnLogin);
		panel.add(btnRegister);
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
			dispose();
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
		
			
			
			if(db.checkTypeAcc(username).equals("quanly")){
				dispose();
				System.out.println("you loggin as an administrator");
				
				MainFrame  mainFrame = new MainFrame(username,db);
				 
				 /*          FUNCTIONS OF STAFF        */
				 new ShowAllStaffController(mainFrame,db);
				 new AddStaffController(mainFrame, db);
				 new EditPassStaffController(mainFrame, db);
				 new DeleteStaffController(mainFrame, db);
				 new SearchStaffController(mainFrame, db);
				 
				 /*			CHANGE MAINPANEL     		*/
				new ChangeTableController(mainFrame,db);
				
				
				/*			FUNCTIONS OF CUSTOMER 		*/
				new ShowAllCusController(mainFrame, db);
				new EditPassCusController(mainFrame, db);
				new SearchCusController(mainFrame, db);
				
				
				/*			FUNCTIONS OF BOOKS			*/
				new ShowAllBookController(mainFrame, db);
				new AddBookController(mainFrame, db);
				new SearchBookController(mainFrame, db);
				new DeleteBookController(mainFrame, db);
				new EditBookController(mainFrame, db);
				
				
				/*			FUNCTIONS OF MOVIES			*/
				new ShowAllMoviesController(mainFrame, db);
				new AddMoviesController(mainFrame, db);
				new SearchMoviesController(mainFrame, db);
				new DeleteMoviesController(mainFrame, db);
				new EditMoviesController(mainFrame, db);
				
				/*			FUNCTIONS OF MUSIC			*/
				new ShowAllMusicController(mainFrame, db);
				new AddMusicController( mainFrame, db);
				new SearchMusicController( mainFrame, db);
				new DeleteMusicController(mainFrame, db);
				new EditMusicController(mainFrame, db);
				
				mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				       dispose();
				        ioFile.writeFile();
				    }
				});
				 
			}
			
			else if(db.checkTypeAcc(username).equals("nhanvien")) {
					System.out.println("you loggin as a staff");
				
				MainFrame  mainFrame = new MainFrame(username,db);
				 
				/*          FUNCTIONS OF STAFF        */
				 new ShowAllStaffController(mainFrame,db);
				 new AddStaffController(mainFrame, db);
				 new EditPassStaffController(mainFrame, db);
				 new DeleteStaffController(mainFrame, db);
				 new SearchStaffController(mainFrame, db);
				 
				 /*			CHANGE MAINPANEL     		*/
				new ChangeTableController(mainFrame,db);
				
				
				/*			FUNCTIONS OF CUSTOMER 		*/
				new ShowAllCusController(mainFrame, db);
				new EditPassCusController(mainFrame, db);
				new SearchCusController(mainFrame, db);
				
				
				/*			FUNCTIONS OF BOOKS			*/
				new ShowAllBookController(mainFrame, db);
				new AddBookController(mainFrame, db);
				new SearchBookController(mainFrame, db);
				new DeleteBookController(mainFrame, db);
				new EditBookController(mainFrame, db);
				
				
				/*			FUNCTIONS OF MOVIES			*/
				new ShowAllMoviesController(mainFrame, db);
				new AddMoviesController(mainFrame, db);
				new SearchMoviesController(mainFrame, db);
				new DeleteMoviesController(mainFrame, db);
				new EditMoviesController(mainFrame, db);
				
				/*			FUNCTIONS OF MUSIC			*/
				new ShowAllMusicController(mainFrame, db);
				new AddMusicController( mainFrame, db);
				new SearchMusicController( mainFrame, db);
				new DeleteMusicController(mainFrame, db);
				new EditMusicController(mainFrame, db);
				
				mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				       dispose();
				        ioFile.writeFile();
				    }
				});
				/*
				try {
					ServerSocket server = new ServerSocket(90);
					
					System.out.println("Server is ready!");
					
					Socket socket = server.accept();
					
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					if(ois != null) {
						try {
							List<MuaHang> listMH = (List<MuaHang>) ois.readObject();
							System.out.println("RECIEVED");
							for(MuaHang mh:listMH) {
							
							System.out.println("id hoa don: "+ mh.getIdHoaDon());
							System.out.println("id san pham: "+ mh.getIdSanPham());
							System.out.println("so luong: "+ mh.getSoLuong());
							System.out.println("don gia: "+mh.getDonGia());
							System.out.println();
							
							}
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					socket.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				 
			}
			
//			
//			else if(db.checkTypeAcc(username).equals("khachhang")) {
//				System.out.println("you loggin as a customer");
//				
//				ClientUI clientUI= new ClientUI(username,db);
//				new ChangeTableClient(clientUI,db);
//				new SearchBookClient(clientUI, db);
//				new SearchMovieClient(clientUI, db);
//				new SearchMusicClient(clientUI, db);
//				new ClickTableClient(clientUI, db);
//				new AddProductClient(clientUI, db);
//				new SeeCartController(clientUI, db);
//				
//				
//			}
			
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
	
}
