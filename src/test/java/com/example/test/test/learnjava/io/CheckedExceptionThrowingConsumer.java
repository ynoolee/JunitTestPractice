package com.example.test.test.learnjava.io;

@FunctionalInterface
public interface CheckedExceptionThrowingConsumer<T,E extends Exception> {
  void accept(T t) throws E;
}
