package com.example.test.test.effective.item8;

public class Account {
	private String name;

	public Account(String name) {
		this.name = name;
		if(this.name.equals("Putin")){
			throw new IllegalArgumentException("푸틴은 안돼!");
		}
	}
	public void transfer(int amount, String to){
		System.out.printf("transfer %d from %s to %s.", amount, this.name, to);
	}
}
