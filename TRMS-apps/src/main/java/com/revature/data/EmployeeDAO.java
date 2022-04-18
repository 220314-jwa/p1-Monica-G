package com.revature.data;

import com.revature.model.Employee;

public interface EmployeeDAO extends GenericDAO<Employee> {
	//set the generic's type here since its Inherited, set it to employee so 
	//a class that implements this interface will have the types as employee
	
	
	//public List<Employee> getByFirstName(String firstName);
	//public List<Employee> getByLastName(String lasName);
}

