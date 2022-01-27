package com.example.test.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * 테스트 1 : JUnit은 테스트 메소드를 수행할 때 마다 새로운 오브젝트를 만든다
 * 테스트 2: 스프링 test context 는 한 개만 만들어져 모든 테스트에서 공유된다.
 *
 * */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebTestConfig.class)
public class JunitTest {
    @Autowired
    ApplicationContext context; // Spring test context 가 매 번 주입해 주는 application context -> 이것이 매 테스트 메소드에서 항상 같은지 확인

    static Set<JunitTest> testObjects = new HashSet<>();
    static ApplicationContext contextObject = null;

    @Test
    public void test1(){
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        // 첫 번째 테스트라면 contextObject 는 null 일테니
        // 첫 번째 테스트가 아니라면, 이미 이전 테스트에서 contextObject 에 당시에 주입받은 context 를 세팅
        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;

    }
    @Test
    public void test2(){
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }
    @Test
    public void test3(){
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    //




}
