package com.example.test.test.learnjava.interfaceJava8;

import java.util.ArrayList;
import java.util.Comparator;

public class ListQuiz {

	public static void preCode() {

		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(100);
		numbers.add(20);
		numbers.add(44);
		numbers.add(3);

		System.out.println(numbers);

		Comparator<Integer> desc = (o1, o2) -> o2 - o1;

		numbers.sort(desc);

		System.out.println(numbers);
	}

	// Comparator desc 를 그대로 두고는, 이를 오름차순으로 변경가능한가?
	public static void main(String[] args) {

		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(100);
		numbers.add(20);
		numbers.add(44);
		numbers.add(3);

		System.out.println(numbers);

		Comparator<Integer> desc = (o1, o2) -> o2 - o1;
		// 이런식으로 기존의 Comparator 를 가지고, Comparator 의 정적메소드만 사용해서, 추가적인 기능을 사용할 수 있다.
		numbers.sort(desc.reversed()); // default method --> 인스턴스가 있어야지만 사용가능하다.

		System.out.println(numbers);
	}

}
