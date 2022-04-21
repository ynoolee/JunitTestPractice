package com.example.test.test.effective.item8;

public class SampleResource implements AutoCloseable{

	@Override
	public void close(){
		System.out.println("Close");
	}

	public void hello() {
		System.out.println("hello");
	}

	@Override
	public void finalize(){
		System.out.println("finalize");
	}
}
