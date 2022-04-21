package com.example.test.test.effective.item1.flyweight;

public class Font {

	private String fontFamily; // 거의 변하지 않는 것

	private int fontSize; // 거의 변하지 않는 것

	public Font(String fontFamily, int fontSize) {
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
	}
}
