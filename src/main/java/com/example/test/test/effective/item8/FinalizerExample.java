package com.example.test.test.effective.item8;

public class FinalizerExample {
	// 예측이 불가능
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Clean up");
	}

	public void hello() {
		System.out.println("hello");
	}
}
