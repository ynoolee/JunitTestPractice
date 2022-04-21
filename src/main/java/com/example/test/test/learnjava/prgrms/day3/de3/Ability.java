package com.example.test.test.learnjava.prgrms.day3.de3;

interface Flyable{
	default void fly(){
		System.out.println("Flyable.fly");
	}
}

interface Swimmable {
	default void swim(){
		System.out.println("Swimmable.swim");
	}
}

interface Walkable {
	default void walk(){
		System.out.println("Walkable.walk");
	}

}

interface Ability {

}
