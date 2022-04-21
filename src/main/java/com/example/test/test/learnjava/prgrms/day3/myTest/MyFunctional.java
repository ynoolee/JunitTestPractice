package com.example.test.test.learnjava.prgrms.day3.myTest;

@FunctionalInterface
public interface MyFunctional {

	abstract void print();

	// 함수형 인터페이스는 오직 하나의 추상메소드만을 갖는 인터페이스를 말함.
	// Object class 에 있는 메소드를 추상 메소드로 추가해놓은 것은 횟수에 포함되지 않음
	@Override
	abstract String toString();

	// 디폴트메소드, 정적 메소드는 당연히 횟수에 포함되지 않고.
	default void printHello(){
		System.out.println("hello");
	}

}
