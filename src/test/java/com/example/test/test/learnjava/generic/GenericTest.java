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
}
