package com.example.test.test.learnjava.design.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

/**
 * 말도안되는 시나리오지만
 * 요청되어온 id 를 갖는 employee 가 없다면
 * 랜덤한 네임을 가진 employee 정보를 생성 및 map 에 저장한 후
 * 원본객체의 메소드를 호출해준다
 * ( reflection 을 사용하여 private 필드에 접근하였음 )
 * */
public class CompanyInvocationHandler implements InvocationHandler {
	private final Company proxied;

	public CompanyInvocationHandler(Company proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// private field 에 접근
		Field employees = proxied.getClass().getDeclaredField("employees");
		if (employees != null) {
			employees.setAccessible(true);
			HashMap<Long, Employee> refMap = (HashMap<Long, Employee>) employees.get(proxied);
			// 인자로 전달된 id 값
			Long id = (Long)args[0];
			// 이미 존재한다면
			if(refMap.containsKey(id));
			else refMap.put(id, new Employee(randomName()));
		}
		return method.invoke(proxied, args);
	}

	private String randomName(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
