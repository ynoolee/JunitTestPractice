package com.example.test.test;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebTestConfig.class)
@RequiredArgsConstructor
public class SameApplicationContextTestA {
    final ApplicationContext applicationContext;

    @Test
    public void isSame(){
        System.out.println("SameApplicationContextTestA applicationContext = " + applicationContext);
    }
}
