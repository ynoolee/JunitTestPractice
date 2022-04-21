package com.example.test.test.learnjava.design.proxy;

import java.util.HashMap;
import java.util.Map;

public class CompanyWithLazyLoadedEmployees implements Company{
	private String companyName;
	private Map<Long, Employee> employees = new HashMap<>();

	public CompanyWithLazyLoadedEmployees(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employees.get(id);
	}
}
