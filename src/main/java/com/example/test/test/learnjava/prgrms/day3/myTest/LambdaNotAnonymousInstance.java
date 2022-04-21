package com.example.test.test.learnjava.prgrms.day3.myTest;


import java.util.function.Predicate;

/*
lambda 는 익명 클래스의 인스턴스가 아님에도 불구하고
익명 클래스의 인스턴스 자리에 사용할 수 있다.

* **/
public class LambdaNotAnonymousInstance {

	class Student{
		int score;

		public int getScore() {
			return score;
		}
	}
	public static void main(String[] args) {
		Predicate<Student> greaterThan7 = (Student s) -> s.getScore() > 7;

		// OR
		Predicate<Student> greaterThan8 = new Predicate<Student>() {
			@Override
			public boolean test(Student s) {
				return s.getScore() > 7;
			}
		};
	}
}

