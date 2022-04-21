package com.example.test.test.learnjava.prgrms.day4.collection;

public class User {
	// 초기값으로 뭔가를 가져야한다. 그렇다고 초기값으로 0살의 이름없는사람 만들었다
	// 그런데 우연히도 실제로 이런 유저가 존재할 수도 있다. ( 초기값..으로 하면 안된다는거지)
	// 임의의값으로 표현하는거로는 초기값 표현하기 좀 그렇다는것
	// 이럴 때는 EMPTY 라는 고정된 초기값을 만들어 놓을 수 있다.
	public static User EMPTY = new User(0,"");

	private int age;
	private String name;

	public User(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

}
