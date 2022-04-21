package com.example.test.test.learnjava.prgrms.day3.de2;

public interface MyInterface {


	void method1();
	default void method2(){
		System.out.println("MyInterface.method2");
	}
}
