package com.example.test.test;

import com.example.test.test.config.AppConfig;
import com.example.test.test.config.HelloBean;
import com.example.test.test.config.SameAppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *  * 테스트 2: 스프링 test context 는 한 개만 만들어져 모든 테스트에서 공유된다.
 * */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class SpringTestContext {
    @Autowired
    ApplicationContext context; // Spring test context 가 매 번 주입해 주는 application context -> 이것이 매 테스트 메소드에서 항상 같은지 확인

    @Autowired
    HelloBean helloBean;

    static ApplicationContext contextObject = null;



    @Test
    public void test1(){
        // 첫 번째 테스트라면 contextObject 는 null 일테니
        // 첫 번째 테스트가 아니라면, 이미 이전 테스트에서 contextObject 에 당시에 주입받은 context 를 세팅
        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    public void test2(){
        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    public void test3(){
        Assertions.assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    public void autoWiredBeanIsSameAsOneFromApplicationContext(){
        Assertions.assertEquals(helloBean,context.getBean(HelloBean.class));
    }

}
