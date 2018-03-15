package com.thomsonreuters.cpl.cuasapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thomsonreuters.cpl.cuasapi.beans.Employee;

@RestController
public class TestController {

	@RequestMapping(value = "testhello",method = RequestMethod.GET, produces = "application/json")
	public List<Employee> testmethod1() {
		
		Employee emp1 = new Employee(100,"Santosh");
		Employee emp2 = new Employee(200,"Kumar");
		
		List<Employee> empList = null;
		empList = new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);
		
		return empList;
	}
	
}
