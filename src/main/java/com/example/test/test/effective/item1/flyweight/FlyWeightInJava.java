package com.example.test.test.effective.item1.flyweight;

public class FlyWeightInJava {
	public static void main(String[] args) {
		FontFactory fontFactory = new FontFactory();
		// 아래의 Character 들을 생성하는데 사용한 Font 객체는 모두 동일한 객체
		Character c1 = new Character('h', "white", fontFactory.getFont("namu:12"));
		Character c2 = new Character('e', "white", fontFactory.getFont("namu:12"));
		Character c3 = new Character('l', "white", fontFactory.getFont("namu:12"));
	}
}
