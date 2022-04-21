package com.example.test.test.effective.item8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

	private String putin;
	private String nonPutin;

	@BeforeEach
	public void setUp() {
		putin = "Putin";
		nonPutin = "nonPutin";
	}

	@Test
	void normal_execution() {
		Account account = new Account(putin);
		account.transfer(100, "ynoolee");
	}

	@Test
	void putin_try() throws InterruptedException {
		Account account = null;
		try {
			// account = new Account(putin);
			account = new BrokenAccount(putin);
		} catch (Exception e) {
			System.out.println("Putin is not allowed");
		}
		System.gc();
		Thread.sleep(3000L);
	}
}