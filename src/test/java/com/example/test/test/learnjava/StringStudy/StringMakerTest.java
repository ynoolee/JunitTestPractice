package com.example.test.test.learnjava.StringStudy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

class StringMakerTest {

    private String operand = "ab";
    private StringMaker maker;

    @BeforeEach
    public void setup(){
        maker = new StringMaker(10); // maker 에서의 operand 를 덧붙이는 연산의 Loop 횟수를 전달
    }


    private long measure_time( Function<String, String> function){
        long start = System.nanoTime();
        function.apply(operand);
        return System.nanoTime()-start;
    }

    @Test
    public void 문자열생성_시간_비교(){

    }


    @Test
    public void concat_로생성한문자열_과_문자열리터럴할당한것은_값이_같더라도_서로다른객체를생성() {
        String str1 = "hello";
        String str2 = "hello world"; // 문자열 상수 풀에 생성되는 문자열 객체
        str1 = str1.concat(" world"); // 문자열 상수 풀이 아닌 힙 공간에 생성되는 문자열 객체

        // 값이 같기 때문에 String 의 경우에는 equals() 와 hashCode() 재정의를 통해 논리적으로는 동등하다
        Assertions.assertEquals(str1,str2);
        // 참조 값이 다르다
        Assertions.assertFalse(str1 == str2);
    }

}