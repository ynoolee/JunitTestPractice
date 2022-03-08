package com.example.test.test.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class IntStreamTestV1 {

  @Test
  public void 항상_0인값이_포함된_배열에서_첫번째로_0인값을_갖는_인덱스찾기(){
    int[] g = new int[]{1,34,4,2,0,1};
    findRoot(g).ifPresent(idx -> Assertions.assertEquals(4,idx));
  }

  public OptionalInt findRoot(int[] g){
    return IntStream.range(0, g.length)
            .filter(i -> g[i] == 0)
            .findAny();
  }
}
