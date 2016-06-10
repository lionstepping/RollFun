package com.onepunch.rollfun.entity;

public class FunnyFood {
	private String type;
	private String name;

	
	public FunnyFood(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}
	
	public FunnyFood() {
		super();	
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
