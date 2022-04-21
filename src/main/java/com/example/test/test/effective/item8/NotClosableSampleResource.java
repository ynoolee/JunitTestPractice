package com.example.test.test.effective.item8;

public class NotClosableSampleResource {

	public void hello(){
		System.out.println("hello!");
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize");
	}
}
