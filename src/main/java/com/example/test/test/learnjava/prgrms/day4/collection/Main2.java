package com.example.test.test.learnjava.prgrms.day4.collection;

import java.util.Arrays;

public class Main2 {

	public static void main(String[] args) {
		new MyCollection<User>(
			Arrays.asList(
				new User(15,"AAA"),
				new User(16, "BBB"),
				new User(17, "CCC"),
				new User(18, "DDD"),
				new User(20,"AAAdd")
			)
		)
			.filter(u -> u.getAge() >16)
			.map(User::getName)
			.forEach(System.out::println);

		// 위 코드에서의 문제점은 무엇일까??
		// 19 세 이상인 유저를 찾아내기 위해서 User 에서 "나이" 데이터를 꺼내서 판별하고있다
		// 캡슐화가 제대로 되지 않은 것
		// 객체 스스로 책임을 갖고 행동하도록 하자
		// 밖으로 어떤 데이터도 꺼내주지 않으면서 동작을 수행할 수 있도록 다음과 같이 변경할 수 있다
		new MyCollection<UserModel>(
			Arrays.asList(
				new UserModel(15,"AAA"),
				new UserModel(16, "BBB"),
				new UserModel(17, "CCC"),
				new UserModel(18, "DDD"),
				new UserModel(20,"AAAdd")
			)
		)
			.filter(u -> u.isOver19())
			.forEach(u -> System.out.println(u));
	}


}
