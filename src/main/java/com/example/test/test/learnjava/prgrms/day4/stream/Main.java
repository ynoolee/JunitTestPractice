package com.example.test.test.learnjava.prgrms.day4.stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Arrays.asList("A","BC","ABD","ABCD","ABCDE")
			.stream()
			.map(s -> s.length())
			.filter(i -> i %2 ==1)
			.forEach(System.out::println);
		// 만들었던 MyCollection 과의 차이점 : Map 을 다 수행하고난 데이터덩어리가 나와서 그걸 MyCollection 에 전달해 한꺼번에 결과가 만들어졌. 그결과를 가지고 filter 적용해 또다른 새로운 MyCollection 덩어리 만들고..
		// 반면에 Stream 은 iterator 처럼 데이터를 한건한건 떨어트려서 처리하는데 map, filter, forEach 에 데이터가 하나하나떨어트려질 때 마다 ,한건한건처리하는데
		// 필요없으면 처리않고, 필요한 시점까지 뒤로밀었다가 lazy evaluation 등이 구현되어있어 효율적으로 동작함
		// 동작환경에 따라, 병렬적으로 동작하기도 함.


		// List 의 기능
		Stream<Integer> s = Arrays.asList(1, 2, 3).stream();

		//array 는 객체가 아니라 stream() 이라는 메소드가 있진 않은데
		// Arrays 라는 유틸즈에 stream 이라는 메소드가 있다.
		Arrays.stream(new int[]{1,2,3});

		// Primitive 는 제네릭타입으로 쓸 수 없음.
		// Integer 에 대한 스트림은 만들 수 있지만 int 에 대한 스트림은 만들수가없은
		// 그래서 얘는 Primitive type 을 위한 스트림으로 나오는 것
		IntStream stream = Arrays.stream(new int[]{1, 2, 3});
		// 이렇게 바꿀수는 있음
		IntStream intStream = Arrays.stream(new int[]{1, 2, 3}).map(i -> Integer.valueOf(i));
		Stream<Integer> boxed = Arrays.stream(new int[]{1, 2, 3}).boxed();
		// collect 의 인자로는 어떤 방식으로 collect 할 것인지

		Object[] objects = Arrays.stream(new int[]{1, 2, 3}).boxed().toArray();
		Integer[] integers = Arrays.stream(new int[]{1, 2, 3}).boxed().toArray(Integer[]::new); // 내가 원하는 타입으로 생성할 수 있는 생성자를 지정해주면된다


		// Stream 만드는 방법 : generate, iterate
		// supplier -> input 없고 결과값 나오는거지
		// 데이터의 연속이라 , 하나 만들어지고 끝이 아니라, 연속된 데이터가 없어질 때 까지 계속 만듦
		Stream.generate(() -> 1).forEach(System.out::println);

		Random r = new Random();
		Stream.generate(r::nextInt)
			.limit(10)
			.forEach(System.out::println);
		// seed 값 부터 시작해서, function 은 이전 값을 인자로 받아서 연속적으로 데이터생성
		Stream.iterate(0, (i) -> i+1)
			.forEach(System.out::println);

	}
}
