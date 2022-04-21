package com.example.test.test.learnjava.prgrms.day3.func;

public class Main2 {

	public static void main(String[] args) {
		// 익명 클래스를 생헝한 것
		MyRunnable r1 = new MyRunnable() {
			@Override
			public void run() {
				System.out.println("Hello");
			}
		};
		r1.run();

		// 익명 메소드를 사용해 표현하는 방법 -> 람다 표현식
		MyRunnable r2 = () -> System.out.println("Hello");
		r2.run();

		// 즉 아래와 같은 것이 가능한 것
		MyRunnable r3 = () ->{
			MySupply s2 = () -> "Hello";
			System.out.println(s2.supply());
		};
		r3.run();

	}
}
