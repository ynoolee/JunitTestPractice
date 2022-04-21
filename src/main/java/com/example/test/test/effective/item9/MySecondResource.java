package com.example.test.test.effective.item9;

public class MySecondResource implements AutoCloseable{
	public void doSomething() {
		System.out.println("Do something");
		throw new FirstError();
	}

	@Override
	public void close() {
		System.out.println("Close My Resource");
		throw new SecondError();
	}
}
