package com.example.test.test.effective.item8;

public class BrokenAccount extends Account{
	public BrokenAccount(String name) {
		super(name);
	}

	@Override
	protected void finalize() throws Throwable {
		this.transfer(1000000, "ynoolee");
	}
}
