package com.example.test.test.effective.item1;

public class Character {

	private char value;

	private String color;

	private String fontFamily; // 거의 변하지 않는 것

	private int fontSize; // 거의 변하지 않는 것

	public Character(char value, String color, String fontFamily, int fontSize) {
		this.value = value;
		this.color = color;
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
	}
}
