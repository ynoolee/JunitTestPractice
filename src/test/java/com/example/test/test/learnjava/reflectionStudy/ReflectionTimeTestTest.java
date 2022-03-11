package com.example.test.test.learnjava.reflectionStudy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

class ReflectionTimeTestTest {

  private ReflectionTimeExample reflectionTimeExample;
  private Object emptyObj;
  private int LOOP_COUNT;

  @BeforeEach
  public void init(){
    reflectionTimeExample = new ReflectionTimeExample();
    emptyObj = new Object();
    LOOP_COUNT = 100;
  }

  public long measure_time(Function<Object,Boolean> myFunction){
    long start = System.nanoTime();
    for(int loop = 0; loop < LOOP_COUNT ; loop++){
      myFunction.apply(emptyObj);
    }
    return System.nanoTime() - start;
  }

  @Test
  public void compare_time(){
    long usingReflection = measure_time(
            arg -> reflectionTimeExample.checkClass_usingReflection(arg)
    );
    long usingInstanceOf = measure_time(
            arg -> reflectionTimeExample.checkClass_usingInstanceOf(arg)
    );
    System.out.printf("reflection 사용시 %d\n",usingReflection);
    System.out.printf("instanceof 사용시 %d",usingInstanceOf);
  }


}