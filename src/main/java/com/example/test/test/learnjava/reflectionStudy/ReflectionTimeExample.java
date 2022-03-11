package com.example.test.test.learnjava.reflectionStudy;

import java.math.BigDecimal;

public class ReflectionTimeExample {

  public boolean checkClass_usingReflection(Object src){
    if(src.getClass().getName().equals("java.math.BigDecimal")){
      return true;
    }
    return false;
  }
  public boolean checkClass_usingInstanceOf(Object src){
    if(src instanceof BigDecimal){
      return true;
    }
    return false;
  }

}
