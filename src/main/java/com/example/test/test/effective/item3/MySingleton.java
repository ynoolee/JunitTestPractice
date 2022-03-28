package com.example.test.test.effective.item3;

public class MySingleton {

	public static MySingleton instance = new MySingleton(); // Eager Initialization 에 해당

	private MySingleton(){}

}
