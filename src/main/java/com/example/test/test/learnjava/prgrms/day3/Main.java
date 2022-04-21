package com.example.test.test.learnjava.prgrms.day3;


interface MyRunnable{
	void myRun();
}
interface YourRunnable{
	void yourRun();
}

public class Main implements MyRunnable, YourRunnable{

	public static void main(String[] args) {
		Main m = new Main();
		m.myRun();
		m.yourRun();

		// 호출할 수 있는 메소드를 제한 할 수 있다.
		MyRunnable mr = new Main();
		mr.myRun();
//		mr.yourRun(); // Compile Error

		YourRunnable yr = new Main();
//		yr.myRun(); // Compile Error
		yr.yourRun();;
	}
	@Override
	public void myRun() {
	}
	@Override
	public void yourRun() {
	}
}
