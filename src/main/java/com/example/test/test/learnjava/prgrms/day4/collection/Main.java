package com.example.test.test.learnjava.prgrms.day4.collection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		new MyCollection<>(Arrays.asList(1,2,3,4,5))
			.forEach(System.out::println);

		// Generic 타입이었기에 인자에 따라 MyCollection 의 T 타입이 결정되겠지
		new MyCollection<>(Arrays.asList("A","B","C","D"))
			.forEach(System.out::println);

		// ====== Method chaining ========

		// 다른 타입으로 매핑
		new MyCollection<>(
			Arrays.asList("A", "BB", "CDE", "DEEE"))
			.map(String::length)
				.forEach(System.out::println);

		// 짝수만 출력되도록 한다
		new MyCollection<>(
			Arrays.asList("A", "BB", "CDE", "DEEE"))
			.map(String::length)
			.filter(i -> i %2 == 0)
			.forEach(System.out::println);

		// 사이즈를 리턴한다
		int s = new MyCollection<>(
			Arrays.asList("A", "BB", "CDE", "DEEE"))
			.map(String::length)
			.filter(i -> i %2 == 0)
			.size();
		System.out.println(s);
	}
}
