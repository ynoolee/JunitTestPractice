package com.example.test.test.learnjava.prgrms.day3.lambda.generic;

// 소비하는애 - 어떤 것을 받아들이고 아무것도 리턴하지 않음
public interface MyConsumer<T> {
	void consume(T arg);
}
