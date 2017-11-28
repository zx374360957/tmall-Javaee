package com.tmall.beans;

public class User{
	private int id;
	private String name;
	private String password;
	
	public void setId(int id){
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	public String getAnonymousName() {
		if (name == null)
			return name;
		if (name.length() == 1)
			return "*";
		else if (name.length() == 2)
			return name.charAt(0) + "*";
		
		return name.charAt(0) + "**" + name.charAt(name.length() - 1);
	}
}