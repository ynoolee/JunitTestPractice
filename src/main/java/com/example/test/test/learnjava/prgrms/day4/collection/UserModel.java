package com.example.test.test.learnjava.prgrms.day4.collection;

public class UserModel {

	private int age;
	private String name;

	public UserModel(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public boolean isOver19(){
		return age >= 19;
	}

	@Override
	public String toString() {
		return "UserModel{" +
			"age=" + age +
			", name='" + name + '\'' +
			'}';
	}
}
