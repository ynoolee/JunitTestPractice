package com.example.test.test;

import com.example.test.test.config.SameAppConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SameAppConfig.class)
@RequiredArgsConstructor
public class SameApplicationContextTestA {
    final ApplicationContext applicationContext;

    @Test
    public void isSame(){
        // SameApplicationContextTestB 에서 출력되는 참조값과 동일 ( 테스트가 같은 프로세스에서 실행 될 때)
        System.out.println("SameApplicationContextTestA applicationContext = " + applicationContext);
    }
}
