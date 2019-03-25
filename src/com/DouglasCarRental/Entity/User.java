package com.DouglasCarRental.Entity;

public class User {

	private int id;
	private String name;
	private String lastname;
	private String birthday;	
	private String email;
	private String password;
	
	public User(){
		
	}
	
	
	public User(int id, String name, String lastname, String birthday, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.birthday = birthday;
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password) {

		this.email = email;
		this.password = password;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	


//
//	public User() {
//
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

}
