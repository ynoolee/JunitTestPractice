package com.example.test.test.learnjava.io;

import org.junit.jupiter.api.Test;

class IoTest {
  private String filePath = "C:\\Users\\owner\\Desktop\\thistest.txt";

  /**
   * 시간 성능만을 재보고 싶었다. 따라서 void 를 리턴한다고 가정 ( 실제로는 아니긴 함 ) 한 메소드들의 성능을 측정해보고 싶었다
   * 기존 처럼 Function 객체를 사용하려고 보니, 여기에는 적절하지 않다고 생각되었다.
   * parameter 는 같으나, return type이 달랐기 때문이다. Consumer를 사용하려고 하였으나 테스트하려는 메소드에서 ChckedException 을 throw 하고 있다는 것이 문제로 느껴졌다.
   * 때문에, 이에 대한 새로운 FunctionalInterface 를 정의하여 테스트를 진행하였다.
   * */
  private long take_time(CheckedExceptionThrowingConsumer<String, Exception> consumer){
    long start = System.nanoTime();
    try {
      consumer.accept(filePath);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return System.nanoTime() - start;
  }

  @Test
  public void fileReader_test(){
    System.out.println(take_time(
            arg -> BasicIOReadUtil.readCharStream(arg)
    ));
    System.out.println(take_time(
            arg -> BasicIOReadUtil.readCharStreamWithBuffer(arg)
    ));
    System.out.println(take_time(
            arg -> BasicIOReadUtil.readBufferedReader(arg)
    ));
  }
}