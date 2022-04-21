package com.example.test.test.learnjava.interfaceJava8.lazy;

interface Food{
	void bite();
	int getSatiety();
}

class Chicken implements Food {
	private final int satiety;

	public Chicken(int satiety) {
		this.satiety = satiety;
		System.out.println("MAKING CHICKEN");
	}

	public void bite(){
		System.out.println("바삭");
	}

	public int getSatiety() {
		return satiety;
	}
}

class Pork implements Food {
	private final int satiety;

	public Pork(int satiety) {
		this.satiety = satiety;
		System.out.println("MAKING PORK");
	}

	public void bite(){
		System.out.println("육즙 쫙");
	}

	public int getSatiety() {
		return satiety;
	}
}
