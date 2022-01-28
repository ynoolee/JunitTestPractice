package com.example.test.test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 테스트 1 : JUnit은 테스트 메소드를 수행할 때 마다 새로운 오브젝트를 만든다
 * */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SameAppConfig.class)
public class JunitTest {

    static Set<JunitTest> testObjects = new HashSet<>();

    @Test
    public void test1(){
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }
    @Test
    public void test2(){
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }
    @Test
    public void test3(){
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }
}
