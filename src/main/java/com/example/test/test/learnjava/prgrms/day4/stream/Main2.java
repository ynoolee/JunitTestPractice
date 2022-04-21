package com.example.test.test.learnjava.prgrms.day4.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

public class Main2 {

	public static void main(String[] args) {
		Random r = new Random();

		// 주사위를 100번 던져 6이 나올 확률을 구하시오
		var count = Stream.generate(() -> r.nextInt(6) + 1)
			.limit(100)
			.filter(n -> n == 6)
			.count();
		System.out.println(count);


		// 1~9 사이 값 중, 겹치지 않게 3개를 출력하라
		// distinct() : remove distinction
		// object type 의 경우 생성방법을 지정해줬어야했음 toArray 할 때
		// primitive 타입의 경우 toArray 로 바로 배열 생성해옴
		int[] arr = Stream.generate(() -> r.nextInt(9) + 1)
			.distinct()
			.limit(3)
			.mapToInt(i -> i)
			.toArray();
		System.out.println(Arrays.toString(arr));


		// 0 ~200 사이 값 중 랜덤값 5 개 뽑아 큰 순서대로 표시하시오
		Random rr = new Random();
		int[] arrr = Stream.generate(() -> r.nextInt(200))
			.limit(5)
			.sorted(Comparator.reverseOrder())
			.mapToInt(i -> i)
			.toArray();
		System.out.println(Arrays.toString(arrr));

	}

}
