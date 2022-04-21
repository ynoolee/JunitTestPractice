package com.example.test.test.learnjava.prgrms.day3.de2;

public class Main {

	public static void main(String[] args) {
		new Service().method1();
	}
}

// 이 서비스에서는 method1 만 사용할 거임
// 그럼에도 method2 를 구현하지 않으면 오류가 떠서 -> 쓸모없는 구현 또는 아무것도 적어주지않음
class Service extends Object implements MyInterface{
	@Override
	public void method1() {
		System.out.println("Hello World");
	}

//	@Override
//	public void method2(){
//		// nothing
//	}
}