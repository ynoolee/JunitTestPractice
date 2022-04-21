package com.example.test.test.learnjava.prgrms.day3.func;

// 함수형 인터페이스임을 명시적으로 하기 위해 어노테이션을 달아주기도함 (optional)
@FunctionalInterface
public interface MyRunnable {
	void run(); // 추상메소드가 하나밖에 없는 메소드 == 함수형 인터페이스
}

// 얘는 함수형 인터페이스인가 ? -> Yes. 추상메소드는 하나밖에 없기 때문 ( 즉, default 메소드가 있던 static 메소드가 있던 이건 상관없다)
interface MyMap {
	void map();
	default void sayHello() {
		System.out.println("MyMap.sayHello");
	}

	static void sayBye() {
		System.out.println("MyMap.sayBye");
	}
}

// 얘는 함수형 인터페이스가 될 수 없다.
interface MyRunnable2{
	void run();
	void run2();
}