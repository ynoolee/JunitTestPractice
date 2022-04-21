

package com.example.test.test.learnjava.prgrms.day3.lambda.generic;


import com.example.test.test.learnjava.prgrms.day3.func.MyRunnable;

public class Main {

	public static void main(String[] args) {

		MySupplier<String> s = () -> "Hello World";
		MyMapper<String, Integer> m = arg -> arg.length(); // Generic 에는 Primitive 타입은 사용불가
		MyMapper<Integer, Integer> m2 = i -> i * i;
		MyConsumer<Integer> c = arg -> System.out.println(arg);

		MyRunnable r = () ->
			c.consume(
				m2.map(
					m.map(
						s.supply()
					)
				)
			);
		r.run();


	}

}
