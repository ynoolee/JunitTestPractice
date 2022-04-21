package com.example.test.test.learnjava.prgrms.day3.lambda;

import com.example.test.test.learnjava.prgrms.day3.func.MyRunnable;
import com.example.test.test.learnjava.prgrms.day3.func.MySupply;

public class Main {

	public static void main(String[] args) {
		MySupplier s = () -> "Hello World";

		MyMapper m = String::length; //str -> str.length();

		// i 에 대한 변경없이 바로 사용하는 경우라면 이런식으로 메소드 레퍼런스를 사용가능
		MyConsumer c = System.out::println; // i -> System.out.println(i);

		// i 에 대한 변경이 있으면 메소드 레퍼런스 사용불가
		MyConsumer c2 = i -> System.out.println(i*10);

		MyRunnable r = () -> c.consume(m.map(s.supply()));

		r.run();
	}

}
