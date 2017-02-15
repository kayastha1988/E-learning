package mysite.web.dto;

//User myObject = new User();

public class User {
	private String name;
	private String userName;
	private String password;
	private String role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}

}
/*
 * 
 * CREATE TABLE User ( name varchar(20) not null, userName VARCHAR(15) PRIMARY
 * KEY, password VARCHAR(20) not null, role varchar(15) not null );
 * 
 */