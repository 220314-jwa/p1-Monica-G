package com.revature.services;

//static import of the Assertions methods
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.data.EmployeeDAO;
import com.revature.data.RequestDAO;
import com.revature.data.StatusDAO;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.model.Employee;

import services.EmployeeService;
import services.EmployeeServiceImp;

public class EmployeeServiceTest {
	
	
	@Mock
	private RequestDAO requestDAO;
	@Mock
	private StatusDAO statusDAO;
	@Mock
	private EmployeeDAO employeeDAO;
	
	@InjectMocks
	private EmployeeService employeeService = new EmployeeServiceImp();
	
	private EmployeeService employee ;
	@Test
	public void logInSuccess() {
		//setup(arguments, expected results)
		String fName = "";
		String lName = "";
		
		//call the method
		Employee result = null;
		try {
			result = employee.logIn( fName, lName  );
		} catch (IncorrectCredentialsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assertion
		assertEquals(employee, result.getEmployeeId());
	}
			// TODO Auto-generated constructor stub
}
	

