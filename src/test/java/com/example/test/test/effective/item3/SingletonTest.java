package com.example.test.test.effective.item3;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingletonTest {

	@Test
	public void 싱글톤_으로작성한_클래스_리플렉션을사용하여_생성자에외부접근테스트(){
		Assertions.assertDoesNotThrow(() -> test(MySingleton.class));
	}

	@Test
	public void enum_으로_작성한_클래스_리플렉션을사용하여_생성자에외부접근테스트(){
		Assertions.assertThrows(NoSuchMethodException.class, () -> test(EnumTest.class));

	}

	public void test(Class<?> clazz) throws Exception{
			Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
			declaredConstructor.setAccessible(true);
			Object instance = declaredConstructor.newInstance();
	}
}
