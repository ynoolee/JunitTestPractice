package com.example.test.test.learnjava.interfaceJava8.lazy;

public class Person {
	private int satiety = 0;

	public void eat(Food food) {
		if (!areYouHungry()) {
			System.out.println("배터져버려서 못 먹게 되었음");
			return;
		}
		System.out.println("냠냠");
		satiety += food.getSatiety();
	}

	public boolean areYouHungry() {
		if (satiety > 50)
			return false;
		return true;
	}
}




