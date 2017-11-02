package vn.media.serialization;

import java.io.Serializable;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	
	private String username;
	public String getTitle() {
		return title;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Account(String title, String username, String password) {
		super();
		this.title = title;
		this.username = username;
		this.password = password;
	}
	private String password;
	
	
}
