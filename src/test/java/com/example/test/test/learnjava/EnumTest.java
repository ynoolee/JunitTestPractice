package com.example.test.test.learnjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

enum MyEnum{
	HI,HELLO
}

public class EnumTest {
	@Test
	public void 같은요청에_같은인스턴스를_반환한다(){
		MyEnum e1 = MyEnum.HI;
		MyEnum e2 = MyEnum.HI;
		Assertions.assertTrue(e1 == e2);
	}
}
