package model.vo;

public class User {
	String userName;
    String password;
    String chrName;
    String role;
   
      public User(String userName, String password, String chrName, String role) {
    	  this.userName = userName;
    	  this.password = password;
    	  this.chrName = chrName;
    	  this.role = role;
	}
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getChrName() {
		return chrName;
	}
	public void setChrName(String chrName) {
		this.chrName = chrName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
