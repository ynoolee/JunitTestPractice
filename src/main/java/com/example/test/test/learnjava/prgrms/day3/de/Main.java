package com.example.test.test.learnjava.prgrms.day3.de;

// 추상 메소드로만 이루어진 클래스 == 인터페이스
interface MyInterface {

	void method1(); // 추상 메소드

	// 구현 메소드이려면 default 키워드를 사용 해야함.
	default void sayHello() { // 구현된 메소드 -> Java 8 부터 인터페이스에서도 구현메소드를 가질 수 있게 확장함
		System.out.println("Hello World");
	}

	// Java 8 부터 interface 에 static 메소드 선언 가능하게 되었음
	static void sayHi() {
		System.out.println("HI");
	}
	// Java 9 에서는 private static method 까지 허용한다
	// field 는 public 이어야만 한다
	private static void sayHo(){
		System.out.println("HO");
	}

}

// sayHello 를 구현하지 않아도 어떠한 오류도 뜨지 않는다 (기본적인 기능이 디폴트 메소드로 정의되어있다)
public class Main implements MyInterface {

	public static void main(String[] args) {

	}

	@Override
	public void method1() {

	}
}
