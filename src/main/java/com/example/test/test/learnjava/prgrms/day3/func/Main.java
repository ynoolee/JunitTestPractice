package com.example.test.test.learnjava.prgrms.day3.func;

// Hello World 라는 문자열을 출력하기 위해, 임시적으로 생성되는 인스턴스가 두 개 있게 됨
class SayHello implements MyRunnable{

	@Override
	public void run() {
//		System.out.println("SayHello.run");
		System.out.println(new Greeting().supply()); // 임시적 인스턴스생성
	}
}

class Greeting implements MySupply{
	@Override
	public String supply() {
		return "Hello World";
	}
}
public class Main {

	public static void main(String[] args) {
		new SayHello().run(); // 임시적 인스턴스 생성

		// 이제 이게 가능해짐
		// 임시로 클래스를 만들어줌 -> 익명 클래스를 생성함.
		new MySupply(){
			@Override
			public String supply() {
				return "Hello World";
			}
		}.supply();

		new MyRunnable() {
			@Override
			public void run() {
				MySupply s = new MySupply() {
					@Override
					public String supply() {
							return "Hello World";
					}
				};
				s.supply();
			}
		}.run();

	}
}
