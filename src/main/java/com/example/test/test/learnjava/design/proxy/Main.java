package com.example.test.test.learnjava.design.proxy;

import java.lang.reflect.Proxy;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		Company company = new CompanyWithLazyLoadedEmployees("삼성_자회사");

		Company proxy =  main.getCompanyProxy(company);
		System.out.println(proxy.getEmployeeById(1L).getName());
	}

	private Company getCompanyProxy(Company company){
		return (Company) Proxy.newProxyInstance(
			company.getClass().getClassLoader(),
			company.getClass().getInterfaces(),
			new CompanyInvocationHandler(company)
		);
	}
}
