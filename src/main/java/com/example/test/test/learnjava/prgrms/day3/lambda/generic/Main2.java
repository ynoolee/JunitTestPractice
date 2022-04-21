package com.example.test.test.learnjava.prgrms.day3.lambda.generic;

import java.util.function.Consumer;
import java.util.function.Predicate;

// 구현부분을 외부에서 전달받도록 하는데에 유용하게 사용할 수가 있다


// 함수형 인터페이스를 또 어디서 활용할 수 있을까?
public class Main2 {

	public static void main(String[] args) {
		// 여기가 호스트임
		new Main2().loop(10, System.out::println);

		new Main2().filteredNumbers(30,
			i -> i % 2 == 0,
			i -> System.out.println(i)
		);
	}

	void filteredNumbers(int max, Predicate<Integer> p, Consumer<Integer> c) {
		for (int i = 0; i < max; i++) {
			// test 를 통과할 때만 accept 하도록 한다.
			if (p.test(i)) {
				c.accept(i);
			}
		}
	}

	// 수행해야하는 것을 전달 받아온다면 ?
	// 무엇을 할지를 host 에서 결정하도록 한다면 ?
	// 그럼 이것은 loop 를 도는 것에만 책임이 있는 것이고, 구체적으로 어떤일을 해야하는지 아는 것은 호스트가 된다.
	void loop(int n, MyConsumer<Integer> consumer) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			// 뭔가를 해야죠! i 를 주고 뭔가 해라
			consumer.consume(i);
		}
	}

}
