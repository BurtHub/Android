package com.huida.databasedemo.bean;

public class Contact {

	private String id;
	private String name;
	private String number;
	public Contact(String id, String name, String number) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
