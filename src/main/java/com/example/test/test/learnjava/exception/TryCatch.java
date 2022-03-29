package com.example.test.test.learnjava.exception;


public class TryCatch {

	public void normalRun(){
		System.out.println("TryCatch.normalRun");
		try {
			System.out.println("Try");        // 1
			throw new Exception();            // 2
		} catch (Exception e) {
			System.out.println("Catch");    // 3
		}
		System.out.println("OUTSIDE");	// 4
	}

	public void whenReturnInCatch() {
		System.out.println("TryCatch.whenReturnInCatch");
		try {
			System.out.println("Try");        // 1
			throw new Exception();            // 2
		} catch (Exception e) {
			System.out.println("Catch");    // 3
			return;                            // 5
		} finally {
			System.out.println("FINALLY");    // 4
		}
	}

	public void whenReturnInTry() {
		System.out.println("TryCatch.whenReturnInTry");
		try {
			Thread.sleep(10);				// 1
			return;								// 3
		} catch (InterruptedException e) {
			System.out.println("CATCH");
		} finally {
			System.out.println("FINALLY");		// 2
		}
	}


	public static void main(String[] args) {
		TryCatch tryCatch = new TryCatch();
		tryCatch.whenReturnInTry();
		tryCatch.normalRun();
	}

}
