package com.example.test.test.learnjava.generic;

import com.example.test.test.learnjava.generic.Bus;
import com.example.test.test.learnjava.generic.Car;
import com.example.test.test.learnjava.generic.GenericUse;
import com.example.test.test.learnjava.generic.MyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GenericTest {


    @Test
    @DisplayName(" MyCollection<T extends Car> 인스턴스를 받아, T 타입의 인스턴스를 추출하여 Car interface 메소드를 정상적으로 사용가능한지 테스트")
    public void genericTypedMethod(){
        String carName = "MY BUS";
        Car bus = new Bus(carName,100);
        MyCollection<Car> collection = new MyCollection<>();
        Assertions.assertEquals(carName, GenericUse.getGenericCollection(collection,bus));
    }

    @Test
    @DisplayName("일련의 T타입 변수들을 받아, max값을 린턴하는지 테스트")
    public void testGetMax(){
        // String class 의 compareTo() 메소드를 보니 -> byte 값을 비교하고 있었음 -> 영문자라면 ascii 코드값을 비교하려나? ->
        Assertions.assertEquals(GenericUse.getMax(1,2,3),3);
        Assertions.assertEquals(GenericUse.getMax(3,1,2),3);
        Assertions.assertEquals(GenericUse.getMax("a","c","b"),"c");

    }
}
